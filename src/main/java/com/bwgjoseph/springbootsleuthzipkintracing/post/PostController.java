package com.bwgjoseph.springbootsleuthzipkintracing.post;

import java.time.LocalDateTime;

import com.bwgjoseph.springbootsleuthzipkintracing.external.Pokemon;
import com.bwgjoseph.springbootsleuthzipkintracing.external.PokemonAPI;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PokemonAPI pokemonAPI;
    private final PostMapper postMapper;
    private final RandomService randomService;
    private final RabbitTemplate rabbitTemplate;

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

    @PostMapping
    public Post post(@RequestBody Post post) {
        this.postMapper.create(post);
        this.randomService.getRandomInt();
        this.rabbitTemplate.convertAndSend("sleuth-queue", post);

        return this.postMapper.get(post.getId());
    }

    @RabbitListener(queues = "sleuth-queue")
    public void listen(Post post) {
        log.info(post.toString());
    }
}
