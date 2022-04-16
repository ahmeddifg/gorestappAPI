package com.gorestapp.gorest.integration.decoder;

import com.gorestapp.gorest.exceptions.exceptionTypes.BackendIsDownException;
import feign.Response;

public class ErrorDecoder implements feign.codec.ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return new BackendIsDownException();
    }
}
