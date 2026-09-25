package com.devshowcase.api.controller;

import com.devshowcase.api.dto.project.ProjectRequest;
import com.devshowcase.api.dto.project.ProjectResponse;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @Valid @RequestBody ProjectRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAll() {

        return ResponseEntity.ok(
                projectService.findAll()
        );
    }
}