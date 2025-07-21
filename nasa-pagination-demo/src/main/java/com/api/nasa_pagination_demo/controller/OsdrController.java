package com.api.nasa_pagination_demo.controller;

import com.api.nasa_pagination_demo.model.OsdrRequestParams;
import com.api.nasa_pagination_demo.dto.StudyFileDto;
import com.api.nasa_pagination_demo.service.OsdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nasa/osdr")
public class OsdrController {

    private final OsdrService osdrService;

    @Autowired
    public OsdrController(OsdrService osdrService) {
        this.osdrService = osdrService;
    }

    @GetMapping("/files")
    public ResponseEntity<List<StudyFileDto>> getFilteredStudyFiles(@ModelAttribute OsdrRequestParams params) {
        try {
            List<StudyFileDto> filteredFiles = osdrService.fetchFilteredFiles(
                    params.getStudyIds(),
                    params.getPage(),      // ✅ Ahora se pasa el parámetro page
                    params.getSize(),
                    params.isAllFiles()
            );

            return ResponseEntity.ok(filteredFiles);
        } catch (Exception e) {
            System.err.println("Error retrieving files: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}