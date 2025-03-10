package com.aluraliteratura.aluraliteratura.repository;

import com.aluraliteratura.aluraliteratura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameContainsIgnoreCase(String nombre);

    List<Author> findByBirthYearGreaterThanEqual(int year);
}
