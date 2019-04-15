package com.ekino.mvc.controller;

import java.util.Optional;
import java.util.Random;

import com.ekino.mvc.model.Person;
import com.ekino.mvc.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DbController {

    private static final Random RANDOM = new Random();

    private final PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Long id) throws Exception {
        return personRepository.findById(id).orElseThrow(Exception::new);
    }

    @GetMapping
    public Iterable<Person> getPersons(@RequestParam("age") Optional<Integer> age) {
        if (age.isPresent()) {
            return personRepository.findAllByAge(age.get());
        }
        return personRepository.findAll();
    }

    @PostMapping
    @Transactional
    public void createPerson() {
        personRepository.save(Person.builder()
                .age(RANDOM.nextInt(100))
                .lastname("toto")
                .firstname("toto")
                .build());
    }
}
