package com.service.rentacar.domain.user.converter;

import com.service.rentacar.domain.user.dto.UserCredentials;
import com.service.rentacar.domain.user.model.UserCredentialsEntity;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsConverter
{

    public UserCredentials convertToDto(UserCredentialsEntity user) {
        if (user == null) {
            return null;
        }

        return UserCredentials.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public UserCredentialsEntity convertToUser(UserCredentials userCredentials) {
        if (userCredentials == null) {
            return null;
        }

        return UserCredentialsEntity.builder()
                .id(userCredentials.getId())
                .username(userCredentials.getUsername())
                .password(userCredentials.getPassword())
                .email(userCredentials.getEmail())
                .build();
    }
}
