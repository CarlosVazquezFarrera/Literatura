package com.aluraliteratura.aluraliteratura.service;

import com.aluraliteratura.aluraliteratura.dto.AuthorDTO;
import com.aluraliteratura.aluraliteratura.model.Author;
import com.aluraliteratura.aluraliteratura.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private IAuthorRepository authorRepository;

    public List<AuthorDTO> listrarAutoresRegistrados() {
        return authorRepository.findAll().stream().map(Author::toDTO).toList();
    }

}
