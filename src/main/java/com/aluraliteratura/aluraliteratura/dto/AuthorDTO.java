package com.aluraliteratura.aluraliteratura.dto;

import java.util.List;

public record AuthorDTO (
    Long id,
    String name,
    int birthYear,
    int deathYear,
    List<BookDTO> books
)
{
    @Override
    public String toString() {
        return "";
    }
}
