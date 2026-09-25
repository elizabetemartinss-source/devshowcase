package com.devshowcase.api.service;

import com.devshowcase.api.dto.technology.TechnologyRequest;
import com.devshowcase.api.dto.technology.TechnologyResponse;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyResponse create(TechnologyRequest request) {

        if (technologyRepository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException(
                    "Tecnologia já cadastrada"
            );
        }

        Technology technology = Technology.builder()
                .name(request.name())
                .build();

        technology = technologyRepository.save(technology);

        return toResponse(technology);
    }

    public List<TechnologyResponse> findAll() {

        return technologyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TechnologyResponse toResponse(Technology technology) {

        return new TechnologyResponse(
                technology.getId(),
                technology.getName()
        );
    }
}