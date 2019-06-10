package com.example.dogservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertDogRequest {

    String name;
    String color;
    int age;
}
