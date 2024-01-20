package com.service.rentacar.repository;

import com.service.rentacar.application.security.InvalidJwtTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InvalidJwtTokenRepository extends JpaRepository<InvalidJwtTokenEntity, Integer>
{
    @Modifying
    @Query(value = "DELETE FROM InvalidJwtTokenEntity invalidJwtTokenEntity WHERE invalidJwtTokenEntity.expirationDateTime < NOW()")
    void deleteAllByExpirationDateTimeBeforeNow();
}
