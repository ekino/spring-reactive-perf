package com.ekino.mvc.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/future")
@RestController
public class MvcFutureController {

    @GetMapping("/simple")
    public String simpleRequest() {
        CompletableFuture<String> toto1 = CompletableFuture.supplyAsync(MvcFutureController::get);
        CompletableFuture<String> toto2 = CompletableFuture.supplyAsync(MvcFutureController::get);
        return join(toto1, toto2);
    }

    @GetMapping("/delay")
    public String delayRequest(@RequestParam(defaultValue = "0") int minDelay,
                               @RequestParam(defaultValue = "1") int maxDelay) {
        CompletableFuture<String> toto1 = CompletableFuture.supplyAsync(MvcFutureController::get);
        CompletableFuture<String> toto2 = CompletableFuture.supplyAsync(() -> getWithDelay(minDelay, maxDelay));
        return join(toto1, toto2);
    }

    @GetMapping("/delay-both")
    public String delayBothRequest(@RequestParam(defaultValue = "0") int minDelay,
                                   @RequestParam(defaultValue = "1") int maxDelay) {
        CompletableFuture<String> toto1 = CompletableFuture.supplyAsync(() -> getWithDelay(minDelay, maxDelay));
        CompletableFuture<String> toto2 = CompletableFuture.supplyAsync(() -> getWithDelay(minDelay, maxDelay));
        return join(toto1, toto2);
    }

    private static String getWithDelay(int minDelay, int maxDelay) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(minDelay, maxDelay));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return get();
    }

    private static String get() {
        return "toto";
    }

    private static String join(CompletableFuture<String> toto1, CompletableFuture<String> toto2) {
        return Stream.of(toto1, toto2)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
    }
}
