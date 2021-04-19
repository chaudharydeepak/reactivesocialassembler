package com.social.techblog.assembler.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

//    CREATE TABLE person
//        (id INT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
//    name VARCHAR(255),
//    age INT);

@Data
//@AllArgsConstructor
public class Person {
    @Id
    private long id;
    private String name;
    private int age;
}