package com.service.rentacar.domain.image.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.IMAGE_NOT_FOUND, status = HttpStatus.NOT_FOUND, messageKey = "image.error.not-found")
public class ImageNotFoundException extends RuntimeException
{

    public ImageNotFoundException(String fileName)
    {
        super("Image does not exist: " + fileName);
    }
}
