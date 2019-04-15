package com.ekino.r2dbc.controller;

import java.util.Optional;
import java.util.Random;

import com.ekino.r2dbc.model.Person;
import com.ekino.r2dbc.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/r2dbc/persons")
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class R2dbcController {

    private static final Random RANDOM = new Random();

    private final PersonRepository personRepository;

    @GetMapping("/{id}")
    public Mono<Person> getPerson(@PathVariable("id") Long id) {
        return personRepository.findById(id);
    }

    @GetMapping
    public Flux<Person> getPersons(@RequestParam("age") Optional<Integer> age) {
        if (age.isPresent()) {
            return personRepository.findByAge(age.get());
        }
        return personRepository.findAll();
    }

    @PostMapping
    @Transactional
    public Mono<Person> createPerson() {
        return personRepository.save(Person.builder()
                .age(RANDOM.nextInt(100))
                .lastname("toto")
                .firstname("toto")
                .build());
    }
}
