package com.service.rentacar.domain.profile;

import com.service.rentacar.domain.image.ImageService;
import com.service.rentacar.domain.image.model.ImageKind;
import com.service.rentacar.domain.profile.dto.UserProfile;
import com.service.rentacar.domain.profile.model.UserProfileEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProfileImagePopulator
{
    private final ImageService imageService;

    public void populate(UserProfile.UserProfileBuilder userProfileBuilder, String imageName)
    {
        userProfileBuilder.iconUrl(imageService.getImageUri(ImageKind.USER, imageName).getUri().toString());
    }

    public void populate(UserProfileEntity.UserProfileEntityBuilder userProfileEntityBuilder, String iconUrl)
    {
        userProfileEntityBuilder.iconName(imageService.retrieveImageName(iconUrl));
    }
}
