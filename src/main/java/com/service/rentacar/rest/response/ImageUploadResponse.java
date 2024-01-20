package com.service.rentacar.rest.response;

import lombok.Value;

@Value(staticConstructor = "of")
public class ImageUploadResponse
{
    String url;
}
