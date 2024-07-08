package com.aluraliteratura.aluraliteratura.model;


import com.aluraliteratura.aluraliteratura.data.BookData;
import com.aluraliteratura.aluraliteratura.enums.Languages;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    public Book(BookData bookData) {
        this.title = bookData.title();
        this.languages = new HashSet<>();
        for (String lang : bookData.languages()) {
            try{
                this.languages.add(Languages.fromAbreviaturaToLanguage(lang));
            }
            catch (Exception ignored) {
            }

        }

    }

    public Book() {
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Languages> languages;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private List<Author> authors;

    public void addAuthor(Author author){
        if(this.authors == null){
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }
}
