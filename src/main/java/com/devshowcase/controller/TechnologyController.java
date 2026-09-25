package com.devshowcase.api.controller;

import com.devshowcase.api.dto.technology.TechnologyRequest;
import com.devshowcase.api.dto.technology.TechnologyResponse;
import com.devshowcase.api.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyService technologyService;

    @PostMapping
    public ResponseEntity<TechnologyResponse> create(
            @Valid @RequestBody TechnologyRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(technologyService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponse>> findAll() {

        return ResponseEntity.ok(
                technologyService.findAll()
        );
    }
}