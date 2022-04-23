package com.bwgjoseph.springbootsleuthzipkintracing.external;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pokemon {
    private Long id;
    private String name;
    private int baseExperience;
}
