package com.service.rentacar.application.security.mfa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mfa_recovery_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MfaRecoveryCodeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mfa_recovery_codes_generator")
    @SequenceGenerator(name = "mfa_recovery_codes_generator", sequenceName = "mfa_recovery_codes_seq", allocationSize = 6)
    @Column(name = "id")
    private Long id;

    @Column(name = "credentials_id", unique = true, nullable = false)
    private Long credentialsId;

    @Column(name = "recovery_code")
    private String recoveryCode;
}
