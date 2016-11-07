package com.kivilcimeray.member.validator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by anıl on 07.11.2016.
 */

public class ValidationResult {

    private boolean success;
    private List<ErrorCode> errorCodes = new ArrayList<>();

    @JsonCreator
    public ValidationResult(
            @JsonProperty("success") boolean success,
            @JsonProperty("error-codes") List<ErrorCode> errorCodes
    ) {
        this.success = success;
        this.errorCodes = errorCodes == null ? new ArrayList<ErrorCode>() : errorCodes;
    }

    public boolean isSuccess() {
        return success;
    }

    @JsonIgnore
    public boolean isFailure() {
        return !success;
    }

    public List<ErrorCode> getErrorCodes() {
        return unmodifiableList(errorCodes);
    }

    public boolean hasError(ErrorCode error) {
        return errorCodes.contains(error);
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "success=" + success +
                ", errorCodes=" + errorCodes +
                '}';
    }
}