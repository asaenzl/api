package com.api.nasa_pagination_demo.controller;

import com.api.nasa_pagination_demo.dto.Item;
import com.api.nasa_pagination_demo.service.PaginationDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class PaginationDemoController {

    @Autowired
    private PaginationDemoService paginationDemoService;

    @GetMapping("/noPagination")
    public List<Item> getMoPagination() {
        try {
            System.out.println("[NO_PAG] Iniciando...");
            return paginationDemoService.getItemList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/offset")
    public List<Item> getOffsetPagination(@RequestParam int page, @RequestParam int size) {
        try {
            System.out.println("[OFFSET] Iniciando...");
            return paginationDemoService.getItemList(page, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/cursor")
    public List<Item> getCursorPagination(@RequestParam(required = false) String after,
                                          @RequestParam int size) {
        try {
            System.out.println("[CURSOR] Iniciando...");
            return paginationDemoService.getItemList(after, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/keyset")
    public List<Item> getKeysetPagination(@RequestParam(required = false) Integer afterId,
                                          @RequestParam int size) {
        try {
            System.out.println("[KEYSET] Iniciando...");
            return paginationDemoService.getItemList(afterId, size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
