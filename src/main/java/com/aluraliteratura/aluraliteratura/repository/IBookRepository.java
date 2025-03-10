package com.aluraliteratura.aluraliteratura.repository;

import com.aluraliteratura.aluraliteratura.enums.Languages;
import com.aluraliteratura.aluraliteratura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE %:nombre%")
    Optional<Book> findByTitleSimilar(String nombre);
    List<Book> findByLanguage(Languages language);
}
