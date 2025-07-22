package com.api.nasa_pagination_demo.service;

import com.api.nasa_pagination_demo.dto.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaginationDemoService {

    private List<Item> dataset = new ArrayList<>();

    private void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("fake_large_dataset.json");
        dataset = mapper.readValue(is, new TypeReference<List<Item>>() {});
        System.out.println("[DATA] Cargando " + dataset.size() + " registros.");
    }

    public List<Item> getItemList() throws IOException {
        if(dataset.isEmpty()) loadData();

        System.out.println("[NO_PAG] Registros devueltos " + dataset.size());
        return dataset;
    }

    public List<Item> getItemList(int page, int size) throws IOException {
        if(dataset.isEmpty()) loadData();

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, dataset.size());
        if (fromIndex >= dataset.size()) {
            System.out.println("[OFFSET] Página fuera de rango: page=" + page + ", size=" + size);
            return Collections.emptyList();
        }

        List<Item> result = dataset.subList(fromIndex, toIndex);
        System.out.println("[OFFSET] page=" + page + ", size=" + size + " -> Registros devueltos: " + result.size());

        return result;
    }

    public List<Item> getItemList(String after, int size) throws IOException {
        if(dataset.isEmpty()) loadData();

        System.out.println("[CURSOR] after=" + after + ", size=" + size);

        List<Item> result = dataset.stream()
                .filter(i -> after == null || i.getCreatedAt().compareTo(after) > 0)
                .sorted(Comparator.comparing(Item::getCreatedAt))
                .limit(size)
                .collect(Collectors.toList());

        System.out.println("[CURSOR] Registros devueltos: " + result.size());

        return result;
    }

    public List<Item> getItemList(Integer afterId, int size) throws IOException {
        if(dataset.isEmpty()) loadData();

        System.out.println("[KEYSET] afterId=" + afterId + ", size=" + size);

        List<Item> result = dataset.stream()
                .filter(i -> afterId == null || i.getId() > afterId)
                .sorted(Comparator.comparing(Item::getId))
                .limit(size)
                .collect(Collectors.toList());

        System.out.println("[KEYSET] Registros devueltos: " + result.size());

        return result;
    }
}
