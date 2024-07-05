package com.aluraliteratura.aluraliteratura.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (
    @JsonAlias("id") long id,
    @JsonAlias("title") String title,
    @JsonAlias("languages") List<String> languages,
    @JsonAlias("authors") List<AuthorData> authors
){

}
