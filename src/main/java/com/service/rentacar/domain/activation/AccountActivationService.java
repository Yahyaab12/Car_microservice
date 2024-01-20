package com.service.rentacar.domain.activation;

import com.service.rentacar.application.security.AccessTokenGenerator;
import com.service.rentacar.domain.activation.converter.ActivationTokenConverter;
import com.service.rentacar.domain.activation.dto.ActivationTokenDto;
import com.service.rentacar.domain.activation.exception.ActivationTokenAlreadyUsedException;
import com.service.rentacar.domain.activation.exception.ActivationTokenExpiredException;
import com.service.rentacar.domain.activation.exception.ActivationTokenNotFoundException;
import com.service.rentacar.domain.activation.model.ActivationTokenEntity;
import com.service.rentacar.repository.ActivationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AccountActivationService
{
    private final AccessTokenGenerator accessTokenGenerator;
    private final ActivationTokenRepository activationTokenRepository;
    private final ActivationTokenConverter activationTokenConverter;

    @Value("${rentacar.security.account.activation-token.expiration-time}")
    private Duration activationTokenExpirationTime;

    @Transactional
    public ActivationTokenEntity invalidateOldActivationTokensAndGenerateNew(long credentialsId)
    {
        this.activationTokenRepository.invalidateOldActivationTokens(credentialsId);

        ActivationTokenEntity activationTokenEntity = new ActivationTokenEntity();
        activationTokenEntity.setCredentialsId(credentialsId);
        activationTokenEntity.setExpirationDate(ZonedDateTime.now().plus(activationTokenExpirationTime));
        activationTokenEntity.setToken(this.accessTokenGenerator.generate());
        activationTokenEntity.setUsed(false);
        this.activationTokenRepository.save(activationTokenEntity);

        return activationTokenEntity;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ActivationTokenDto getActivationToken(String token)
    {
        return this.activationTokenRepository.findByToken(token).map(this.activationTokenConverter::toDto)
                .orElseThrow(ActivationTokenNotFoundException::new);
    }

    @Transactional
    public void activateToken(ActivationTokenDto activationTokenDto)
    {
        if (activationTokenDto.isUsed())
            throw new ActivationTokenAlreadyUsedException();
        if (activationTokenDto.getExpirationDate().isBefore(ZonedDateTime.now()))
            throw new ActivationTokenExpiredException();

        ActivationTokenEntity activationTokenEntity = this.activationTokenRepository.findById(activationTokenDto.getId())
                .orElseThrow(ActivationTokenNotFoundException::new);
        activationTokenEntity.setUsed(true);
        this.activationTokenRepository.save(activationTokenEntity);
    }
}
