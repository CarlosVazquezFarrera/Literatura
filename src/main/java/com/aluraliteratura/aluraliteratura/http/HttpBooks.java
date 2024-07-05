package com.aluraliteratura.aluraliteratura.http;

import com.aluraliteratura.aluraliteratura.data.ResponseBook;
import com.aluraliteratura.aluraliteratura.utils.Converter;



public class HttpBooks extends HttpGutendex {
    private final HttpDatos http = new HttpDatos();
    private final Converter converter = new Converter();
    private final String url;
    public HttpBooks() {
        url = urlBase + "books/";
    }


    public ResponseBook getBook(String name) {
        name = name.trim().replace(' ', '+');
        String json = http.obtenerDatos(url + "?search=" + name);
        return converter.convert(json, ResponseBook.class);
    }
}
