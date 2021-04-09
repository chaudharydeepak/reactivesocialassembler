package com.social.techblog.assembler.repository;

import com.social.techblog.assembler.entity.Person;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {
}
