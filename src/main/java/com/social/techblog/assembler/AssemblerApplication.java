package com.social.techblog.assembler;

import com.social.techblog.assembler.entity.Person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.hint.TypeHints;

@SpringBootApplication
@TypeHints({
        @TypeHint(types = Person.class) // need this to satisfy native.
})
public class AssemblerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssemblerApplication.class, args);
    }
}
