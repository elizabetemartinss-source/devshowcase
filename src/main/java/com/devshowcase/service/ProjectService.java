package com.devshowcase.api.service;

import com.devshowcase.api.dto.project.ProjectRequest;
import com.devshowcase.api.dto.project.ProjectResponse;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectResponse create(ProjectRequest request) {

        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() ->
                        new RuntimeException("Perfil não encontrado"));

        List<Technology> technologies = new ArrayList<>();

        if (request.technologyIds() != null) {

            technologies = technologyRepository
                    .findAllById(request.technologyIds());

            if (technologies.size() != request.technologyIds().size()) {
                throw new RuntimeException(
                        "Uma ou mais tecnologias não foram encontradas"
                );
            }
        }

        Project project = Project.builder()
                .title(request.title())
                .description(request.description())
                .repositoryUrl(request.repositoryUrl())
                .demoUrl(request.demoUrl())
                .profile(profile)
                .technologies(technologies)
                .build();

        project = projectRepository.save(project);

        return toResponse(project);
    }

    public List<ProjectResponse> findAll() {

        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProjectResponse toResponse(Project project) {

        List<Long> technologyIds = project.getTechnologies()
                .stream()
                .map(Technology::getId)
                .toList();

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getRepositoryUrl(),
                project.getDemoUrl(),
                project.getProfile().getId(),
                technologyIds
        );
    }
}