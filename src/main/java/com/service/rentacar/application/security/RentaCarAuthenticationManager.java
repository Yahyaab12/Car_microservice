package com.service.rentacar.application.security;

import com.service.rentacar.application.config.security.jwt.JwtService;
import com.service.rentacar.application.exception.BadCredentialsException;
import com.service.rentacar.application.exception.MfaRequiredException;
import com.service.rentacar.application.security.dto.AuthResult;
import com.service.rentacar.application.security.dto.MfaActivationResult;
import com.service.rentacar.application.security.mfa.MfaAuthenticationService;
import com.service.rentacar.application.security.mfa.MfaType;
import com.service.rentacar.application.security.mfa.dto.UserMfaSettings;
import com.service.rentacar.rest.request.MfaAuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentaCarAuthenticationManager
{
    private final RentaCarUserDetailsService rentaCarUserDetailsService;
    private final MfaAuthenticationService mfaAuthenticationService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public MfaActivationResult activateMfa(AuthenticatedUser authenticatedUser, String code)
    {
        return mfaAuthenticationService.activate(authenticatedUser.getId(), code);
    }

    public AuthResult authenticate(UserCredentials userCredentials)
    {
        AuthenticatedUser authenticatedUser = rentaCarUserDetailsService.loadUserByUsername(userCredentials.getLogin());
        try
        {
            AuthResult authResult = authenticateByPassword(authenticatedUser, userCredentials.getPassword());
            JwtToken jwtToken = createJwtAndSetAuth(authResult, userCredentials.isRememberMe());
            return AuthResult.builder()
                    .jwt(jwtToken.getJwt())
                    .authorities(jwtToken.getAuthorities())
                    .username(jwtToken.getUsername())
                    .status(AuthResult.Status.AUTHENTICATED)
                    .authentication(authResult.getAuthentication())
                    .build();
        }
        catch (MfaRequiredException exception)
        {
            return authResultWithMfaChallenge(authenticatedUser);
        }
        catch (Exception exception)
        {
            return AuthResult.badCredentials();
        }
    }

    public JwtToken authenticate(MfaAuthRequest mfaAuthRequest)
    {
        AuthResult authResult = mfaAuthenticationService.authenticate(mfaAuthRequest);

        //TODO: Handle RememberMe
        return createJwtAndSetAuth(authResult, true);
    }

    public UserMfaSettings getUserMfaSettings(AuthenticatedUser authenticatedUser)
    {
        return mfaAuthenticationService.getUserMfaSettings(authenticatedUser.getId()).orElse(null);
    }


    private JwtToken createJwtAndSetAuth(AuthResult authResult, boolean rememberMe)
    {
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authResult.getAuthentication().getPrincipal();
        Set<String> authorities = authenticatedUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return JwtToken.of(jwtService.createJwt(authenticatedUser.getUsername(), rememberMe, authorities),
                authenticatedUser.getUsername(),
                authorities);
    }

    private AuthResult authenticateByPassword(AuthenticatedUser authenticatedUser, String password)
    {
        if (!passwordEncoder.matches(password, authenticatedUser.getPassword()))
        {
            throw new BadCredentialsException();
        }

        if (hasMfaEnabled(authenticatedUser))
        {
            throw new MfaRequiredException();
        }

        return AuthResult.authenticated(authenticatedUser);
    }

    private AuthResult authResultWithMfaChallenge(AuthenticatedUser authenticatedUser)
    {
        String mfaChallenge = this.mfaAuthenticationService.generateMfaChallenge(authenticatedUser);
        return AuthResult.requiresMfa(authenticatedUser, mfaChallenge);
    }

    private boolean hasMfaEnabled(AuthenticatedUser authenticatedUser)
    {
        return mfaAuthenticationService.getUserMfaSettings(authenticatedUser.getId())
                .map(UserMfaSettings::isVerified)
                .orElse(false);
    }

    public String prepareMfaActivation(AuthenticatedUser authenticatedUser, MfaType mfaType)
    {
        return this.mfaAuthenticationService.prepareMfaActivation(authenticatedUser, mfaType);
    }

    public void deleteMfa(AuthenticatedUser authenticatedUser)
    {
        this.mfaAuthenticationService.deleteMfaForUser(authenticatedUser.getId());
    }

    public Set<String> getAvailableMfaTypes()
    {
        return this.mfaAuthenticationService.getAvailableAuthTypes();
    }
}
