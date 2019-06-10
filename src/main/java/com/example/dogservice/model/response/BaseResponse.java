package com.example.dogservice.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    int statusCode;
    String message;
}
