package com.aluraliteratura.aluraliteratura.dto;

import com.aluraliteratura.aluraliteratura.enums.Languages;
import com.aluraliteratura.aluraliteratura.model.Author;

import java.util.List;
import java.util.Set;

public record BookDTO(
        Long id,
        String title,
        Set<Languages> languages,
        List<Author> authors)
{}
