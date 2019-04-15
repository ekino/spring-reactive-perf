package com.ekino.mongo.controller;

import java.util.Optional;
import java.util.Random;

import com.ekino.mongo.model.PersonDoc;
import com.ekino.mongo.repository.PersonDocRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/mongo/persons")
@RestController
@RequiredArgsConstructor
public class MongoController {

    private static final Random RANDOM = new Random();

    private final PersonDocRepository personDocRepository;

    @GetMapping("/{id}")
    public Mono<PersonDoc> getPerson(@PathVariable("id") String id) {
        return personDocRepository.findById(id);
    }

    @GetMapping
    public Flux<PersonDoc> getPersons(@RequestParam("age") Optional<Integer> age) {
        if (age.isPresent()) {
            return personDocRepository.findByAge(age.get());
        }
        return personDocRepository.findAll();
    }

    @PostMapping
    public Mono<PersonDoc> createPerson() {
        return personDocRepository.insert(PersonDoc.builder()
                .age(RANDOM.nextInt(100))
                .lastname("toto")
                .firstname("toto")
                .build());
    }
}
