package com.devshowcase.api.dto.project;

import java.util.List;

public record ProjectResponse(

        Long id,
        String title,
        String description,
        String repositoryUrl,
        String demoUrl,
        Long profileId,
        List<Long> technologyIds
) {
}