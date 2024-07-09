package com.aluraliteratura.aluraliteratura.model;


import com.aluraliteratura.aluraliteratura.data.BookData;
import com.aluraliteratura.aluraliteratura.dto.BookDTO;
import com.aluraliteratura.aluraliteratura.enums.Languages;
import jakarta.persistence.*;



@Entity
@Table(name = "books")
public class Book {
    public Book(BookData bookData) {
        this.title = bookData.title();
        this.downloadCount = bookData.downloadCount();
        if (!bookData.languages().isEmpty()) {
            this.language = Languages.fromAbreviaturaToLanguage(bookData.languages().getFirst());
        }

    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;
    @Column(unique = true)
    private String title;
    @Enumerated(EnumType.STRING)
    private Languages language;

    @ManyToOne
    private Author author;

    private int downloadCount;

    public Author getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Languages getLanguage() {
        return language;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setAuthors(Author author) {
        this.author = author;
    }

    public BookDTO toDTO() {
        return new BookDTO(getId(), getTitle(), getLanguage(), getAuthor(), getDownloadCount());
    }
}
