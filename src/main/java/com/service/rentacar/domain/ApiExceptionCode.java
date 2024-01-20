package com.service.rentacar.domain;

public enum ApiExceptionCode
{
    IMAGE_NOT_FOUND,
    COULD_NOT_SAVE_IMAGE,
    COULD_NOT_DELETE_IMAGE,
    BAD_CREDENTIALS,
    USER_LOCKED,
    USER_NOT_ACTIVATED,
    USER_NOT_EXIST,
    ACCESS_DENIED,
    PROFILE_NOT_FOUND,
    ACTIVATION_TOKEN_USED,
    ACTIVATION_TOKEN_EXPIRED,
    ACTIVATION_TOKEN_NOT_FOUND,
    USER_EXISTS,
    MFA_CHALLENGE_EXPIRED,
    MFA_ALREADY_ACTIVATED,
    COULD_NOT_GENERATE_QR_CODE,
    RESERVATION_NOT_FOUND,
    RESERVATION_VEHICLE_NOT_AVAILABLE

}