package com.devshowcase.api.controller;

import com.devshowcase.api.dto.profile.ProfileRequest;
import com.devshowcase.api.dto.profile.ProfileResponse;
import com.devshowcase.api.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponse> create(
            @Valid @RequestBody ProfileRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                profileService.findById(id)
        );
    }
}