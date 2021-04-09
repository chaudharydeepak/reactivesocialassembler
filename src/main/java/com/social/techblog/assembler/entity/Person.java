package com.social.techblog.assembler.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

//    CREATE TABLE person
//        (id INT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
//    name VARCHAR(255),
//    age INT);

@Data
@AllArgsConstructor
public class Person {
    @Id
    private final long id;
    private final String name;
    private final int age;
}