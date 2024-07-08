package com.aluraliteratura.aluraliteratura.model;

import com.aluraliteratura.aluraliteratura.data.AuthorData;
import com.aluraliteratura.aluraliteratura.dto.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    int birthYear;
    int deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(AuthorData a) {
        this.name = a.name();
        this.birthYear = a.birthYear();
        try {
            this.deathYear = a.deathYear();
        }
        catch (NumberFormatException ex) {
            this.deathYear = 0;
        }
    }

    public Author() {
    }
}
