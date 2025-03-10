package com.aluraliteratura.aluraliteratura;

import com.aluraliteratura.aluraliteratura.enums.Languages;
import com.aluraliteratura.aluraliteratura.service.AuthorService;
import com.aluraliteratura.aluraliteratura.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Principal {
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private BooksService booksService;
    @Autowired
    private AuthorService authorService;

    public void mostrarMenu() {
        String menu = """
                 1 - Buscar libro por titulo 
                 2 - Listar libros registrados
                 3 - Listar autores registrados
                 4 - Listar autores vivos en un determinado tiempo
                 5 - Listar libros por idioma
                 0 - Salir
                """;
        int opcion = 0;

        do {
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    buscarLibros();
                    break;
                case 2:
                    mostrarLibrosGuardados();
                    break;
                case 3:
                    mostrarAutoresGuardados();
                    break;
                case 4:
                    mostrarAutoresPorFechaDeNacimiento();
                    break;
                case 5:
                    mostrarLibrosPorCategoria();
                    break;
            }


        } while (opcion != 0);

        System.out.println("Fin");
    }

    private void mostrarAutoresPorFechaDeNacimiento() {
        System.out.println("Año de nacimiento: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        authorService.listrarAutoresPorFechaDeNacimiento(year).forEach(System.out::println);

    }

    private void buscarLibros() {
        System.out.println("Nombre del libro: ");
        String name = scanner.nextLine();
        var book = booksService.getBook(name);
        if(book.isEmpty()) return;
        System.out.println(book.get());
    }

    private void  mostrarAutoresGuardados() {
        authorService.listrarAutoresRegistrados().forEach(System.out::println);
    }

    private void mostrarLibrosGuardados() {
        booksService.mostrarLibrosGuardados().forEach(System.out::println);
    }

    private void mostrarLibrosPorCategoria() {
        for (Languages language: Languages.values()){
            System.out.println(language);
        }
        System.out.println("Selecciona un idioma");
        String abreviatura = scanner.nextLine();
        Languages language = Languages.fromAbreviaturaToLanguage(abreviatura);
        booksService.mostrarLibrosPorCategoria(language).forEach(System.out::println);
    }


}
