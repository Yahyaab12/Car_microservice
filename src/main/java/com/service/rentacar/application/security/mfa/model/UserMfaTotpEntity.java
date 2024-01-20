package com.service.rentacar.application.security.mfa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_mfa_totp")
@Data
public class UserMfaTotpEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "credentialsId", unique = true, nullable = false)
    private Long credentialsId;

    @Column(name = "secret", nullable = false)
    private String secret;
}
