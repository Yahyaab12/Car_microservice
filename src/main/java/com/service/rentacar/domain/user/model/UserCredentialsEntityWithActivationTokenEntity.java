package com.service.rentacar.domain.user.model;

import com.service.rentacar.domain.activation.model.ActivationTokenEntity;
import lombok.Data;

@Data
public class UserCredentialsEntityWithActivationTokenEntity
{
    UserCredentialsEntity userCredentials;
    ActivationTokenEntity activationTokenEntity;
}
