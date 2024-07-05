package com.aluraliteratura.aluraliteratura.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseBook (
    @JsonAlias("count") int count,
    @JsonAlias("next") String next,
    @JsonAlias("previos")  String previos,
    @JsonAlias("results") List<BookData> results
    ){}
