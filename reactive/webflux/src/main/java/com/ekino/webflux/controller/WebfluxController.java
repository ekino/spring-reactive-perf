package com.ekino.webflux.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static java.time.Duration.*;

@RequestMapping("/http")
@RestController
public class WebfluxController {

    @GetMapping("/simple")
    public Mono<String> getToto() {
        Mono<String> get1 = get();
        Mono<String> get2 = get();
        return get1
                .zipWith(get2, (a, b) -> a + b);
    }

    @GetMapping("/delay")
    public Mono<String> delay(@RequestParam(defaultValue = "0") int minDelay,
                              @RequestParam(defaultValue = "1") int maxDelay) {
        Mono<String> get1 = getWithDelay(minDelay, maxDelay);
        Mono<String> get2 = get();
        return get1
                .zipWith(get2, (a, b) -> a + b);
    }

    @GetMapping("/delay-both")
    public Mono<String> delayboth(@RequestParam(defaultValue = "0") int minDelay,
                                  @RequestParam(defaultValue = "1") int maxDelay) {
        Mono<String> get1 = getWithDelay(minDelay, maxDelay);
        Mono<String> get2 = getWithDelay(minDelay, maxDelay);
        return get1
                .zipWith(get2, (a, b) -> a + b);
    }

    private static Mono<String> getWithDelay(int minDelay, int maxDelay) {
        return get()
                .delayElement(ofMillis(ThreadLocalRandom.current().nextInt(minDelay, maxDelay)));
    }

    private static Mono<String> get() {
        return Mono.just("toto");
    }
}
