package com.service.rentacar.rest;

import com.service.rentacar.application.security.AuthenticationFacade;
import com.service.rentacar.application.security.RentaCarAuthenticationManager;
import com.service.rentacar.application.security.mfa.MfaType;
import com.service.rentacar.application.security.mfa.dto.UserMfaSettings;
import com.service.rentacar.domain.profile.ProfileService;
import com.service.rentacar.domain.profile.dto.UserProfile;
import com.service.rentacar.rest.request.MfaActivationRequest;
import com.service.rentacar.rest.response.MfaActivationResponse;
import com.service.rentacar.rest.response.MfaAvailableTypesResponse;
import com.service.rentacar.rest.response.MfaSettingsResponse;
import com.service.rentacar.rest.response.MfaTotpQrDataUriResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@RequestMapping("/api/v1/profiles")
@RestController
public class ProfilesRestController
{
    private final ProfileService profileService;
    private final AuthenticationFacade authenticationFacade;
    private final RentaCarAuthenticationManager authenticationManager;

    @PatchMapping(value = "/{profileId}")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<?> saveProfile(@PathVariable("profileId") long profileId,
                                         @RequestPart(value = "image", required = false) MultipartFile image,
                                         @RequestPart(value = "profile") UserProfile userProfile)
    {
        userProfile.setId(profileId);

        if (image != null)
        {
            profileService.saveProfileWithImage(userProfile, image);
        }
        else
        {
            profileService.saveProfile(userProfile);
        }

        return getProfile(profileId);
    }

    @GetMapping("/{profileId}")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<UserProfile> getProfile(@PathVariable("profileId") long profileId)
    {
        return ResponseEntity.ok(profileService.getProfileById(profileId));
    }

    @GetMapping("/{profileId}/settings/mfa/activation")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<MfaTotpQrDataUriResponse> generateQrCode(@PathVariable("profileId") long profileId,
                                                                   @RequestParam(value = "type") MfaType mfaType)
    {
        //TODO: Handle mfaType...
        return ResponseEntity.ok(MfaTotpQrDataUriResponse.of(authenticationManager.prepareMfaActivation(authenticationFacade.getCurrentUser(), mfaType)));
    }

    @GetMapping("/{profileId}/settings/mfa/available-types")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<MfaAvailableTypesResponse> getAvailableMfaTypes(@PathVariable("profileId") long profileId)
    {
        return ResponseEntity.ok(MfaAvailableTypesResponse.of(authenticationManager.getAvailableMfaTypes()));
    }

    @DeleteMapping("/{profileId}/settings/mfa")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<?> deleteMfa(@PathVariable("profileId") long profileId)
    {
        authenticationManager.deleteMfa(authenticationFacade.getCurrentUser());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{profileId}/settings/mfa/activation")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<MfaActivationResponse> activateMfa(@PathVariable("profileId") long profileId,
                                                             @RequestBody MfaActivationRequest mfaActivationRequest)
    {
        return ResponseEntity.ok(MfaActivationResponse.of(
                authenticationManager.activateMfa(authenticationFacade.getCurrentUser(), mfaActivationRequest.getCode()))
        );
    }

    @GetMapping("/{profileId}/mfa-settings")
    @PreAuthorize("@securityManager.canEditProfile(authentication, #profileId)")
    public ResponseEntity<MfaSettingsResponse> getUserMfaSettings(@PathVariable("profileId") long profileId)
    {
        UserMfaSettings userMfaSettings = authenticationManager.getUserMfaSettings(authenticationFacade.getCurrentUser());
        if (userMfaSettings != null) {
            return ResponseEntity.ok(MfaSettingsResponse.of(userMfaSettings.getMfaType(), userMfaSettings.isVerified(), userMfaSettings.getVerifiedDate()
                    .toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        }
        return ResponseEntity.ok(null);
    }
}
