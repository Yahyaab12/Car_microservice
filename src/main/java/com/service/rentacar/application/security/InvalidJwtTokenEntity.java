package com.service.rentacar.application.security;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "invalid_jwt_token")
public class InvalidJwtTokenEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "jwt", unique = true, nullable = false)
    private String jwt;

    @Column(name = "invalidated_date_time", nullable = false)
    private ZonedDateTime invalidatedDateTime;

    @Column(name = "expiration_date_time", nullable = false)
    private ZonedDateTime expirationDateTime;
}
