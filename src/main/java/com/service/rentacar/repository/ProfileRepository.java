package com.service.rentacar.repository;

import com.service.rentacar.domain.profile.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    UserProfileEntity findByCredentialsId(Long credentialsId);
}
