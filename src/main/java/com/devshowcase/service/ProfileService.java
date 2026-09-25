package com.devshowcase.api.service;

import com.devshowcase.api.dto.profile.ProfileRequest;
import com.devshowcase.api.dto.profile.ProfileResponse;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponse create(ProfileRequest request) {

        if (profileRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        Profile profile = Profile.builder()
                .name(request.name())
                .email(request.email())
                .bio(request.bio())
                .githubUrl(request.githubUrl())
                .linkedinUrl(request.linkedinUrl())
                .build();

        profile = profileRepository.save(profile);

        return toResponse(profile);
    }

    public ProfileResponse findById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Perfil não encontrado"));

        return toResponse(profile);
    }

    private ProfileResponse toResponse(Profile profile) {

        return new ProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getBio(),
                profile.getGithubUrl(),
                profile.getLinkedinUrl()
        );
    }
}