package com.aluraliteratura.aluraliteratura.service;

import com.aluraliteratura.aluraliteratura.data.AuthorData;
import com.aluraliteratura.aluraliteratura.data.BookData;
import com.aluraliteratura.aluraliteratura.http.HttpBooks;
import com.aluraliteratura.aluraliteratura.model.Author;
import com.aluraliteratura.aluraliteratura.model.Book;
import com.aluraliteratura.aluraliteratura.repository.IAuthorRepository;
import com.aluraliteratura.aluraliteratura.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class BooksService {

    private final HttpBooks httpBooks = new HttpBooks();
    @Autowired
    private IBookRepository repository;
    @Autowired
    private IAuthorRepository authorRepository;

    public void getBook(String nombre) {
        nombre = nombre.trim();

        Optional<Book> bookRepository = repository.findByTitleSimilar(nombre);

        if (bookRepository.isPresent()) {
            return;
        }
        nombre = nombre.replace(' ', '+');
        Optional<BookData> bookData = httpBooks.getBook(nombre);

        if(bookData.isEmpty()) {
            System.out.println("No se ha encontrado el libro");
            return;
        }
        saveBook(bookData.get());
    }

    private Book saveBook(BookData bookData) {
        Book book = new Book(bookData);
        for (AuthorData authorData : bookData.authors()) {
            Optional<Author> author = authorRepository.findByNameContainsIgnoreCase(authorData.name());
            if(author.isPresent()) {
                book.addAuthor(author.get());
            }
            else {
                book.addAuthor(new Author(authorData));
            }
        }
        return repository.save(book);
    }
}
