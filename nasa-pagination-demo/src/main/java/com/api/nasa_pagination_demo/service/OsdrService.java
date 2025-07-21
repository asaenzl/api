package com.api.nasa_pagination_demo.service;

import com.api.nasa_pagination_demo.dto.StudyFileDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OsdrService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<StudyFileDto> fetchFilteredFiles(String studyIds, int page, int size, boolean allFiles) throws Exception {
        String apiUrl = String.format(
                "https://osdr.nasa.gov/osdr/data/osd/files/%s?page=%d&size=%d&all_files=%s",
                studyIds,
                page,          // ✅ ahora se usa el parámetro de página correctamente
                size,          // ✅ limitado a un máximo de 25 según OSDR API
                allFiles
        );

        String json = restTemplate.getForObject(apiUrl, String.class);
        JsonNode root = mapper.readTree(json);
        JsonNode studySection = root.path("studies");
        List<StudyFileDto> allFilesList = new ArrayList<>();

        // Puede haber múltiples estudios en una solicitud combinada
        for (String id : studyIds.split(",")) {
            String trimmedId = id.trim();
            JsonNode studyFiles = studySection.path("OSD-" + trimmedId).path("study_files");

            for (JsonNode fileNode : studyFiles) {
                StudyFileDto file = mapper.treeToValue(fileNode, StudyFileDto.class);
                allFilesList.add(file);
            }
        }

        return allFilesList;
    }
}