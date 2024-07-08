package com.aluraliteratura.aluraliteratura;

import com.aluraliteratura.aluraliteratura.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Principal {
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private BooksService booksService;

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
            }


        } while (opcion != 0);

        System.out.println("Fin");
    }


    private void buscarLibros() {
        System.out.println("Nombre del libro: ");
        String name = scanner.nextLine();
        booksService.getBook(name);
    }
}
