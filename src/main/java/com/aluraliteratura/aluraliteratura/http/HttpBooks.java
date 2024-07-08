package com.aluraliteratura.aluraliteratura.http;

import com.aluraliteratura.aluraliteratura.data.BookData;
import com.aluraliteratura.aluraliteratura.data.ResponseBook;
import com.aluraliteratura.aluraliteratura.utils.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class HttpBooks extends HttpGutendex {
    private final HttpDatos http = new HttpDatos();
    private final Converter converter = new Converter();
    private final String url;
    public HttpBooks() {
        url = urlBase + "books/";
    }


    public Optional<BookData> getBook(String name) {
        name = name.trim().replace(' ', '+');
        String json = http.obtenerDatos(url + "?search=" + name);
        ResponseBook responseBook = converter.convert(json, ResponseBook.class);
        return  responseBook.results().stream().findFirst();
    }
}
