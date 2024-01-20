package com.service.rentacar.domain.user;

import com.service.rentacar.application.lang.RequestLocaleExtractor;
import com.service.rentacar.application.security.mfa.MfaAuthenticationService;
import com.service.rentacar.application.security.exception.AccessDeniedException;
import com.service.rentacar.domain.activation.AccountActivationService;
import com.service.rentacar.domain.activation.AccountActivationTokenRequester;
import com.service.rentacar.domain.activation.dto.ActivationTokenDto;
import com.service.rentacar.domain.activation.dto.ActivationTokenParams;
import com.service.rentacar.domain.profile.ProfileService;
import com.service.rentacar.domain.profile.dto.CreateProfileParameters;
import com.service.rentacar.domain.user.converter.UserCredentialsConverter;
import com.service.rentacar.domain.user.dto.UserCredentials;
import com.service.rentacar.domain.user.exception.UsernameOrEmailAlreadyUsedException;
import com.service.rentacar.domain.user.model.UserCredentialsEntity;
import com.service.rentacar.domain.user.dto.UserRegistration;
import com.service.rentacar.repository.UserCredentialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final AccountActivationService accountActivationService;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserCredentialsConverter userCredentialsConverter;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final AccountActivationTokenRequester accountActivationTokenRequester;
    private final RequestLocaleExtractor requestLocaleExtractor;

    @Transactional
    public void register(UserRegistration userRegistration)
    {
        UserCredentialsEntity existingUserCreds = this.userCredentialsRepository.findByUsernameOrEmail(userRegistration.getUsername(), userRegistration.getEmail());
        if (existingUserCreds != null)
            throw new UsernameOrEmailAlreadyUsedException();

        UserCredentialsEntity userCredentialsEntity = UserCredentialsEntity.builder()
                .username(userRegistration.getUsername())
                .email(userRegistration.getEmail())
                .password(passwordEncoder.encode(userRegistration.getPassword()))
                .locked(false)
                .activated(false)
                .build();
        userCredentialsEntity = this.userCredentialsRepository.save(userCredentialsEntity);

        accountActivationTokenRequester.requestActivationToken(userCredentialsEntity.getId(),
                userCredentialsEntity.getEmail(),
                requestLocaleExtractor.getPreferredLangCode()
        );
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public UserCredentials findByUsername(String username)
    {
        return userCredentialsConverter.convertToDto(userCredentialsRepository.findByUsername(username));
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public UserCredentials findById(Long id)
    {
        return userCredentialsRepository.findById(id)
                .map(this.userCredentialsConverter::convertToDto)
                .orElse(null);
    }

    @Transactional
    public void activateAccount(ActivationTokenParams activationTokenParams)
    {
        String token = activationTokenParams.getToken();
        ActivationTokenDto activationTokenDto = this.accountActivationService.getActivationToken(token);
        this.accountActivationService.activateToken(activationTokenDto);

        UserCredentialsEntity credentials = userCredentialsRepository.findById(activationTokenDto.getCredentialsId())
                .orElse(null);
        if (credentials != null)
        {
            credentials.setActivated(true);
            userCredentialsRepository.save(credentials);
            this.profileService.createNewProfile(CreateProfileParameters.builder()
                    .credentialsId(credentials.getId())
                    .email(credentials.getEmail())
                    .build());
        }
    }

    public void resendActivationEmail(com.service.rentacar.application.security.UserCredentials.UsernameOrEmail login)
    {
        UserCredentialsEntity userCredentialsEntity = this.userCredentialsRepository.findByUsernameOrEmail(login.getLogin(), login.getLogin());
        if (userCredentialsEntity == null)
        {
            throw new UsernameNotFoundException("User does not exist!");
        }
        else if (userCredentialsEntity.isActivated())
        {
            // Better not to inform the client about activated account.
            throw new AccessDeniedException();
        }

        accountActivationTokenRequester.requestActivationToken(userCredentialsEntity.getId(),
                userCredentialsEntity.getEmail(),
                requestLocaleExtractor.getPreferredLangCode()
        );
    }
}
