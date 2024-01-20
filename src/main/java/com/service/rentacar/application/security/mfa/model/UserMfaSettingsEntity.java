package com.service.rentacar.application.security.mfa.model;

import com.service.rentacar.application.security.mfa.MfaType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "user_mfa_settings")
@Data
public class UserMfaSettingsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "credentials_id", unique = true, nullable = false)
    private Long credentialsId;

    @Column(name = "mfa_type", unique = false, nullable = false, columnDefinition = "INTEGER")
    @Enumerated(value = EnumType.STRING)
    private MfaType mfaType;

    @Column(name = "verified", unique = false, nullable = false, columnDefinition = "BOOLEAN default 0")
    private boolean verified;

    @Column(name = "verified_date_time", nullable = true)
    private ZonedDateTime verifiedDate;
}
