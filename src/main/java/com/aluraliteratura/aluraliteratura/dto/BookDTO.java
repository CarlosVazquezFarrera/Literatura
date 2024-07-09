package com.aluraliteratura.aluraliteratura.dto;

import com.aluraliteratura.aluraliteratura.enums.Languages;
import com.aluraliteratura.aluraliteratura.model.Author;

public record BookDTO(
        Long id,
        String title,
        Languages language,
        Author author,
        int downloadCount)
{
    @Override
    public String toString() {
        return """
              -----------------LIBRO--------------------'
              Titulo: %s 
              Autor(s): %s
              Idioma: %s
              Numero de descargas: %s
              ------------------------------------------
              """.formatted(title(), author().getName(), language(), downloadCount());
    }
}
