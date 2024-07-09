package com.aluraliteratura.aluraliteratura.model;

import com.aluraliteratura.aluraliteratura.data.AuthorData;
import com.aluraliteratura.aluraliteratura.dto.AuthorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    int birthYear;
    int deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Long getId() {
        return id;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(e -> e.setAuthors(this));
        this.books = books;
    }

    public AuthorDTO toDTO() {
        return new AuthorDTO(getId(), getName(), getBirthYear(), getDeathYear(), getBooks().stream().map(Book::toDTO).toList());
    }


}
