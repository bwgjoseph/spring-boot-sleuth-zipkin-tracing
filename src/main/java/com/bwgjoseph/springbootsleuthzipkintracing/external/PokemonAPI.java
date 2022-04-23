package com.bwgjoseph.springbootsleuthzipkintracing.external;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonAPI {
    private final RestTemplate restTemplate;

    public PokemonAPI(RestTemplateBuilder builder) {
        this.restTemplate = builder.rootUri("https://pokeapi.co/api/v2/pokemon/").build();
    }

    public Pokemon getPokemon(String pokemon) {
        return this.restTemplate.exchange("/{pokemon}", HttpMethod.GET, null, Pokemon.class, pokemon).getBody();
    }
}
