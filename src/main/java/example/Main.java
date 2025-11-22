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
        BibliotecaService servicioLibros = new BibliotecaService(libroDAO);

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
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero("");

            switch (opcion) {
                case 1 -> menuLibros(servicioLibros);
                case 2 -> menuUsuarios(servicioUsuarios);
                case 3 -> menuPrestamos(servicioPrestamos);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void menuLibros(BibliotecaService servicio) {
//        int opcion;
//        do {
//            System.out.println("\n===== MENÚ LIBROS =====");
//            System.out.println("1. Añadir libro");
//            System.out.println("2. Listar libros");
//            System.out.println("3. Modificar título");
//            System.out.println("4. Eliminar libro");
//            System.out.println("0. Volver");
//            System.out.print("Opción: ");
//            opcion = leerEntero("");
//
//            switch (opcion) {
//                case 1 -> {
//                    String titulo = leerString("Título: ");
//                    servicio.addLibro(new Libro(0, titulo));
//                }
//                case 2 -> servicio.getAllLibros().forEach(System.out::println);
//                case 3 -> {
//                    int id = leerEntero("ID del libro: ");
//                    String nuevo = leerString("Nuevo título: ");
//                    Libro libro = servicio.getLibroById(id);
//                    if (libro != null) {
//                        libro.setTitulo(nuevo);
//                        servicio.updateLibro(libro);
//                    } else {
//                        System.out.println("No se encontró el libro con id=" + id);
//                    }
//                }
//                case 4 -> {
//                    int id = leerEntero("ID del libro a eliminar: ");
//                    servicio.deleteLibro(id);
//                }
//                case 0 -> System.out.println("Volviendo al menú principal...");
//                default -> System.out.println("Opción no válida");
//            }
//        } while (opcion != 0);
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