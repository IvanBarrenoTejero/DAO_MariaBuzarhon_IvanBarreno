package example;

import dao.*;
import model.Prestamo;
import model.Usuario;
import service.BibliotecaService;
import service.PrestamoService;
import service.UsuarioService;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        LibroDAO libroDAO = new LibroDAOImpl();
        LibroAutorDAO libroAutorDAO = new LibroAutorDAOImpl();
        AutorDAO autorDAO = new AutorDAOImpl();

        BibliotecaService servicioLibros = new BibliotecaService(libroDAO, libroAutorDAO, autorDAO);
        BibliotecaService servicioAutores = new BibliotecaService(libroDAO, libroAutorDAO, autorDAO);

        PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
        PrestamoService servicioPrestamos = new PrestamoService(prestamoDAO);

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        UsuarioService servicioUsuarios = new UsuarioService(usuarioDAO);

        int opcion;

        do {
            System.out.println("\n===== ¿QUÉ MENÚ QUIERES ESCOGER? =====");
            System.out.println("1. Menú Libros");
            System.out.println("2. Menú Usuarios");
            System.out.println("3. Menú Préstamos");
            System.out.println("4. Menú Autores");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero("");

            switch (opcion) {
                case 1 -> menuLibros(servicioLibros);
                case 2 -> menuUsuarios(servicioUsuarios);
                case 3 -> menuPrestamos(servicioPrestamos);
                case 4 -> menuAutores(servicioAutores);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void menuLibros(BibliotecaService servicio) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ LIBROS =====");
            System.out.println("1. Añadir libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Modificar título");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Asociar autor a libro");
            System.out.println("6. Listar autores de un libro");
            System.out.println("7. Listar libros de un autor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerEntero("");

            switch (opcion) {
                case 1 -> {
                    String titulo = leerString("Título: ");
                    String isbn = leerString("ISBN: ");
                    servicio.addLibro(new model.Libro(titulo, isbn));
                }
                case 2 -> {
                    var libros = servicio.listarLibros();
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros disponibles.");

                    } else {
                        for (var libro : libros) {
                            System.out.print("ID=" + libro.getId() +
                                    ", Título='" + libro.getTitulo() + "'" +
                                    ", ISBN='" + libro.getIsbn() + "'");
                            var autores = servicio.obtenerAutoresPorLibro(libro.getId());
                            if (!autores.isEmpty()) {
                                System.out.print(", Autores: ");
                                for (int i = 0; i < autores.size(); i++) {
                                    System.out.print(autores.get(i).getNombre());
                                    if (i < autores.size() - 1) System.out.print(", ");
                                }
                            }
                            System.out.println();
                        }
                    }
                }
                case 3 -> {
                    int id = leerEntero("ID del libro: ");
                    String nuevo = leerString("Nuevo título: ");
                    servicio.cambiarTitulo(id, nuevo);
                }
                case 4 -> {
                    int id = leerEntero("ID del libro a eliminar: ");
                    servicio.eliminarLibro(id);
                }
                case 5 -> {
                    int libroId = leerEntero("ID del libro: ");
                    int autorId = leerEntero("ID del autor: ");
                    servicio.agregarAutorAlLibro(libroId, autorId);
                    System.out.println("Autor asociado al libro correctamente.");
                }
                case 6 -> {
                    int libroId = leerEntero("ID del libro: ");
                    var autores = servicio.obtenerAutoresPorLibro(libroId);
                    if (autores.isEmpty()) System.out.println("No hay autores asociados.");
                    else autores.forEach(System.out::println);
                }
                case 7 -> {
                    int autorId = leerEntero("ID del autor: ");
                    var libros = servicio.obtenerLibrosPorAutor(autorId);
                    if (libros.isEmpty()) System.out.println("No hay libros asociados.");
                    else libros.forEach(System.out::println);
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }
    private static void menuAutores(BibliotecaService servicio) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ AUTORES =====");
            System.out.println("1. Añadir autor");
            System.out.println("2. Listar autores");
            System.out.println("3. Eliminar autor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerEntero("");

            switch(opcion) {
                case 1 -> {
                    String nombre = leerString("Nombre del autor: ");
                    servicio.addAutor(new model.Autor(0, nombre));
                }
                case 2 -> {
                    var autores = servicio.listarAutores();
                    if (autores.isEmpty()) System.out.println("No hay autores disponibles.");
                    else autores.forEach(System.out::println);
                }
                case 3 -> {
                    int id = leerEntero("ID del autor a eliminar: ");
                    servicio.eliminarAutor(id);
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }
        } while(opcion != 0);
    }

    private static void menuUsuarios(UsuarioService servicio) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ USUARIOS =====");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Modificar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerEntero("");

            switch (opcion) {
                case 1 -> {
                    String nombre = leerString("Nombre: ");
                    servicio.addUsuario(new Usuario(0, nombre));
                }
                case 2 -> servicio.getAllUsuarios().forEach(System.out::println);
                case 3 -> {
                    int id = leerEntero("ID del usuario: ");
                    String nuevo = leerString("Nuevo nombre: ");
                    Usuario usuario = servicio.getUsuarioById(id);
                    if (usuario != null) {
                        usuario.setNombre(nuevo);
                        servicio.updateUsuario(usuario);
                    } else {
                        System.out.println("No se encontró el usuario con id=" + id);
                    }
                }
                case 4 -> {
                    int id = leerEntero("ID del usuario a eliminar: ");
                    servicio.deleteUsuario(id);
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void menuPrestamos(PrestamoService servicio) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRÉSTAMOS =====");
            System.out.println("1. Añadir préstamo");
            System.out.println("2. Listar préstamos");
            System.out.println("3. Buscar préstamo por ID");
            System.out.println("4. Eliminar préstamo");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerEntero("");

            switch (opcion) {
                case 1 -> crearPrestamo(servicio);
                case 2 -> servicio.getAllPrestamos().forEach(System.out::println);
                case 3 -> {
                    int id = leerEntero("ID del préstamo: ");
                    Prestamo p = servicio.getPrestamoById(id);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("No se encontró el préstamo con id=" + id);
                    }
                }
                case 4 -> {
                    int id = leerEntero("ID del préstamo a eliminar: ");
                    servicio.deletePrestamo(id);
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void crearPrestamo(PrestamoService servicio) {
        try {
            System.out.println("Utilice el formato (yyyy-MM-dd)");
            Date fechaInicio = leerFecha("Fecha de inicio: ");
            Date fechaFin = leerFecha("Fecha de fin: ");

            if (!fechaFin.after(fechaInicio)) {
                System.out.println("La fecha de fin debe ser posterior a la fecha de inicio.");
                return;
            }

            int usuarioId = leerEntero("ID del usuario: ");
            int libroId = leerEntero("ID del libro: ");

            Prestamo prestamo = new Prestamo(0, fechaInicio, fechaFin, usuarioId, libroId);
            servicio.addPrestamo(prestamo);

        } catch (Exception e) {
            System.err.println("Error al crear préstamo: " + e.getMessage());
        }
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            if (mensaje != null && !mensaje.isEmpty()) System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe introducir un número entero.");
            }
        }
    }

    public static String leerString(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            } else {
                System.out.println("El texto no puede estar vacío.");
            }
        }
    }

    public static Date leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                return Date.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato inválido. Use yyyy-MM-dd (ejemplo: 2025-11-18).");
            }
        }
    }
}
