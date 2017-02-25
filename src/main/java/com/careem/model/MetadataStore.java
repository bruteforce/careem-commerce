package com.careem.model;

import java.util.Map;

import com.careem.exception.APIConnectionException;
import com.careem.exception.APIException;
import com.careem.exception.AuthenticationException;
import com.careem.exception.InvalidRequestException;

/**
 * Common interface for Shippo objects that can store metadata.
 */
public interface MetadataStore<T> {
    Map<String, String> getMetadata();

    void setMetadata(Map<String, String> metadata);

    MetadataStore<T> update(Map<String, Object> params) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException;

    MetadataStore<T> update(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException;
}
