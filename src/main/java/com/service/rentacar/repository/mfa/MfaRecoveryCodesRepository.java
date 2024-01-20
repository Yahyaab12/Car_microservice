package com.service.rentacar.repository.mfa;

import com.service.rentacar.application.security.mfa.model.MfaRecoveryCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MfaRecoveryCodesRepository extends JpaRepository<MfaRecoveryCodeEntity, Long>
{

}
