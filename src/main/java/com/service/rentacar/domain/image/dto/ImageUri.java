package com.service.rentacar.domain.image.dto;

import lombok.Value;

import java.net.URI;

@Value(staticConstructor = "of")
public class ImageUri
{
    URI uri;
}
