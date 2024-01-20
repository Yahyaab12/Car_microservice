package com.service.rentacar.repository.mfa;

import com.service.rentacar.application.security.mfa.model.UserMfaSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMfaSettingsRepository extends JpaRepository<UserMfaSettingsEntity, Long>
{
    Optional<UserMfaSettingsEntity> findByCredentialsId(long credentialsId);

    @Modifying
    @Query("DELETE from UserMfaSettingsEntity entity WHERE entity.credentialsId = :credentialsId")
    void deleteByCredentialsId(@Param("credentialsId") long credentialsId);
}
