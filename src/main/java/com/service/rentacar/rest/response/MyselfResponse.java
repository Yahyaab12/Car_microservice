package com.service.rentacar.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.rentacar.domain.profile.dto.UserProfile;
import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class MyselfResponse
{
    @JsonProperty("userProfile")
    UserProfile userProfile;

    @JsonProperty("authorities")
    Set<String> authorities;
}
