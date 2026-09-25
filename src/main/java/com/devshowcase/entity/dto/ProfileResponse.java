package com.devshowcase.api.dto.profile;

public record ProfileResponse(
        Long id,
        String name,
        String email,
        String bio,
        String githubUrl,
        String linkedinUrl
) {
}