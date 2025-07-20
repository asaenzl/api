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

    public List<StudyFileDto> fetchFilteredFiles(String studyIds, int size, Double cursorDateCreated, boolean allFiles)
            throws Exception {
        String apiUrl = String.format(
                "https://osdr.nasa.gov/osdr/data/osd/files/%s?page=0&size=200&all_files=%s",
                studyIds,
                allFiles
        );

        String json = restTemplate.getForObject(apiUrl, String.class);
        JsonNode root = mapper.readTree(json);
        JsonNode studyFiles = root.path("studies").path("OSD-" + studyIds).path("study_files");

        List<StudyFileDto> allFilesList = new ArrayList<>();
        for (JsonNode fileNode : studyFiles) {
            StudyFileDto file = mapper.treeToValue(fileNode, StudyFileDto.class);
            allFilesList.add(file);
        }

        return allFilesList.stream()
                .filter(file -> cursorDateCreated == null || file.getDateCreated() > cursorDateCreated)
                .limit(size)
                .collect(Collectors.toList());
    }
}