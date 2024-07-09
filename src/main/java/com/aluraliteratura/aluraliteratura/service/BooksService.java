package com.aluraliteratura.aluraliteratura.service;

import com.aluraliteratura.aluraliteratura.data.AuthorData;
import com.aluraliteratura.aluraliteratura.data.BookData;
import com.aluraliteratura.aluraliteratura.dto.BookDTO;
import com.aluraliteratura.aluraliteratura.enums.Languages;
import com.aluraliteratura.aluraliteratura.http.HttpBooks;
import com.aluraliteratura.aluraliteratura.model.Author;
import com.aluraliteratura.aluraliteratura.model.Book;
import com.aluraliteratura.aluraliteratura.repository.IAuthorRepository;
import com.aluraliteratura.aluraliteratura.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final HttpBooks httpBooks = new HttpBooks();
    @Autowired
    private IBookRepository repository;
    @Autowired
    private IAuthorRepository authorRepository;

    public Optional<BookDTO> getBook(String nombre) {
        nombre = nombre.trim();

        Optional<Book> bookRepository = repository.findByTitleSimilar(nombre);

        if (bookRepository.isPresent()) {
            return Optional.of((bookRepository.get().toDTO()));
        }
        nombre = nombre.replace(' ', '+');
        Optional<BookData> bookData = httpBooks.getBook(nombre);

        if(bookData.isEmpty()) {
            System.out.println("No se ha encontrado el libro");
            return Optional.empty();
        }
        return  Optional.of(saveBook(bookData.get()).toDTO());
    }

    private Book saveBook(BookData bookData) {
        Book book = new Book(bookData);
        AuthorData autorData =  bookData.authors().getFirst();
        Optional<Author> autorRegistrado = authorRepository.findByNameContainsIgnoreCase(autorData.name());

        if (autorRegistrado.isPresent()){
            book.setAuthors(autorRegistrado.get());
        }
        else {
            Author autorNuevo = authorRepository.save(new Author(autorData));
            book.setAuthors(autorNuevo);
        }
        return repository.save(book);
    }

    public List<BookDTO> mostrarLibrosGuardados() {
        return repository.findAll().stream().map(Book::toDTO).toList();
    }

    public List<BookDTO> mostrarLibrosPorCategoria(Languages language) {
        return repository.findByLanguage(language).stream().map(Book::toDTO).toList();
    }
}
