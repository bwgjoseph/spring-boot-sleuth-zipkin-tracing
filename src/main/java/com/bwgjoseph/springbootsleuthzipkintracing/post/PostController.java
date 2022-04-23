package com.bwgjoseph.springbootsleuthzipkintracing.post;

import java.time.LocalDateTime;

import com.bwgjoseph.springbootsleuthzipkintracing.external.Pokemon;
import com.bwgjoseph.springbootsleuthzipkintracing.external.PokemonAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PokemonAPI pokemonAPI;

    @GetMapping
    public Post get() {
        Pokemon ditto = this.pokemonAPI.getPokemon("ditto");
        Pokemon clefairy = this.pokemonAPI.getPokemon("clefairy");

        return Post.builder()
            .id(1)
            .title(ditto.getName())
            .body(clefairy.getName())
            .createdAt(LocalDateTime.now())
            .createdBy("por")
            .updatedAt(LocalDateTime.now())
            .updatedBy("por")
            .build();
    }
}
