package com.bwgjoseph.springbootsleuthzipkintracing.post;

import java.util.Random;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

@Service
public class RandomService {
    @NewSpan
    public int getRandomInt() {
        return new Random().nextInt();
    }
}
