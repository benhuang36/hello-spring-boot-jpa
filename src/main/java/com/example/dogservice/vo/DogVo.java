package com.example.dogservice.vo;

import com.example.dogservice.model.request.InsertDogRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "dog")
public class DogVo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @Column(length = 100)
    String name;

    @Column(length = 50)
    String color;

    @Column
    int age;

    public void from(InsertDogRequest insertRequest) {
        name = insertRequest.getName();
        color = insertRequest.getColor();
        age = insertRequest.getAge();
    }
}
