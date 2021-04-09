package com.social.techblog.assembler.service;

import com.social.techblog.assembler.entity.Person;
import com.social.techblog.assembler.repository.PersonRepository;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    //@Autowired
    //R2dbcEntityTemplate r2dbcEntityTemplate;
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> insertPerson(Person person) {
        //return r2dbcEntityTemplate.insert(person);
        return personRepository.save(person);
    }

    public Flux<Person> getPersons() {
        return personRepository.findAll();
    }

}
