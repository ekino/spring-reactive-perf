package com.ekino.mvc.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/http")
@RestController
public class MvcController {

    @GetMapping("/simple")
    public String simpleRequest() {
        String toto1 = get();
        String toto2 = get();
        return toto1 + toto2;
    }

    @GetMapping("/delay")
    public String delayRequest(@RequestParam(defaultValue = "0") int minDelay,
                               @RequestParam(defaultValue = "1") int maxDelay) throws InterruptedException {
        String toto1 = get();
        String toto2 = getWithDelay(minDelay, maxDelay);
        return toto1 + toto2;
    }

    @GetMapping("/delay-both")
    public String delayBothRequest(@RequestParam(defaultValue = "0") int minDelay,
                                   @RequestParam(defaultValue = "1") int maxDelay) throws InterruptedException {
        String toto1 = getWithDelay(minDelay, maxDelay);
        String toto2 = getWithDelay(minDelay, maxDelay);
        return toto1 + toto2;
    }

    private static String getWithDelay(int minDelay, int maxDelay) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(minDelay, maxDelay));
        return get();
    }

    private static String get() {
        return "toto";
    }
}
