package com.example.dogservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
public class InsertDogResponse extends BaseResponse {

    long id;

    @Builder
    public InsertDogResponse(long id, int statusCode, String message) {
        super(statusCode, message);
        this.id = id;
    }
}
