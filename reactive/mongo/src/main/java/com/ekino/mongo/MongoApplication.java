package com.ekino.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ekino.mongo.model.PersonDoc;
import com.ekino.mongo.repository.PersonDocRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class MongoApplication {

    private static final Random RANDOM = new Random();

    private final PersonDocRepository personDocRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        List<PersonDoc> persons = new ArrayList<>(2000);
        for (int i = 0; i <= 2000; i++) {
            persons.add(PersonDoc.builder().age(RANDOM.nextInt(70)).firstname("toto").lastname("toto").build());
        }
        personDocRepository.insert(persons).subscribe();
    }
}
