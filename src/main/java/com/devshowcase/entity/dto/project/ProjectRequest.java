package com.devshowcase.api.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProjectRequest(

        @NotBlank(message = "Título é obrigatório")
        String title,

        @NotBlank(message = "Descrição é obrigatória")
        String description,

        @NotBlank(message = "URL do repositório é obrigatória")
        String repositoryUrl,

        String demoUrl,

        @NotNull(message = "profileId é obrigatório")
        Long profileId,

        List<Long> technologyIds
) {
}