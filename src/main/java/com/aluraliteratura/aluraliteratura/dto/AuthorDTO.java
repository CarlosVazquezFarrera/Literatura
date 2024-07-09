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
        List<String> booksName = books().stream().map(BookDTO::title).toList();
        String libros = String.join(" | ", booksName);
        return """
              -----------------AUTOR--------------------'
              Autor: %s 
              Fecha de nacimiento: %s
              Fecha de fallecimiento: %s
              Libros [ %s ]
              ------------------------------------------
              """.formatted(name(), birthYear(), deathYear() > 0? deathYear(): "N/A", libros);
    }
}
