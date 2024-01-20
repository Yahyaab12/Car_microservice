package com.service.rentacar.domain.profile;

import com.service.rentacar.application.security.AuthenticationFacade;
import com.service.rentacar.application.security.exception.AccessDeniedException;
import com.service.rentacar.domain.image.ImageService;
import com.service.rentacar.domain.image.model.ImageKind;
import com.service.rentacar.domain.profile.converter.ProfileConverter;
import com.service.rentacar.domain.profile.dto.CreateProfileParameters;
import com.service.rentacar.domain.profile.dto.UserProfile;
import com.service.rentacar.domain.profile.exception.ProfileNotFoundException;
import com.service.rentacar.domain.profile.model.UserProfileEntity;
import com.service.rentacar.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileConverter profileConverter;
    private final AuthenticationFacade authenticationFacade;
    private final ImageService imageService;

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<UserProfile> getProfiles() {
        return profileRepository.findAll().stream()
                .map(profileConverter::toProfileDto)
                .toList();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public UserProfile getProfileById(Long id) {
        return profileRepository.findById(id)
                .map(profileConverter::toProfileDto)
                .orElseThrow(ProfileNotFoundException::new);
    }

    @Transactional
    public void createNewProfile(CreateProfileParameters createProfileParameters)
    {
        this.profileRepository.save(UserProfileEntity.builder()
                        .credentialsId(createProfileParameters.getCredentialsId())
                        .email(createProfileParameters.getEmail())
                .build());
    }

    @Transactional
    public void saveProfile(UserProfile userProfileDto)
    {
        if (!canCurrentUserEditUserProfile(userProfileDto))
        {
            throw new AccessDeniedException();
        }

        UserProfileEntity userProfile = this.profileConverter.toProfile(userProfileDto);
        userProfile.setCredentialsId(this.authenticationFacade.getCurrentUser().getId());
        this.profileRepository.save(userProfile);
    }

    private boolean canCurrentUserEditUserProfile(UserProfile userProfile)
    {
        return this.authenticationFacade.getCurrentUser().getProfileId().equals(userProfile.getId());
    }

    @Transactional
    public void saveProfileWithImage(UserProfile userProfile, MultipartFile image)
    {
        String oldImageName = imageService.retrieveImageName(userProfile.getIconUrl());
        userProfile.setIconUrl(imageService.saveImage(image, ImageKind.USER).getUri().toString());
        saveProfile(userProfile);
        imageService.deleteImage(ImageKind.USER, oldImageName);
    }
}
