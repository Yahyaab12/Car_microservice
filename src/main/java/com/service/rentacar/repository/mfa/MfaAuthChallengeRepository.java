package com.service.rentacar.repository.mfa;

import com.service.rentacar.application.security.mfa.model.MfaAuthChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MfaAuthChallengeRepository extends JpaRepository<MfaAuthChallengeEntity, String>
{

}
