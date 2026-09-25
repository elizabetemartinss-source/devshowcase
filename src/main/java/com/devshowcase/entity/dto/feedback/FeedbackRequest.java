package com.devshowcase.api.dto.feedback;

import jakarta.validation.constraints.*;

public record FeedbackRequest(

        @NotBlank(message = "Autor é obrigatório")
        String author,

        @NotBlank(message = "Comentário é obrigatório")
        String comment,

        @NotNull(message = "Nota é obrigatória")
        @Min(value = 1, message = "A nota mínima é 1")
        @Max(value = 5, message = "A nota máxima é 5")
        Integer rating,

        @NotNull(message = "projectId é obrigatório")
        Long projectId
) {
}