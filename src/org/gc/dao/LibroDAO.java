package org.gc.dao;

import java.util.List;
import org.gc.model.Libro;

/**
 * Contrato DAO que define las operaciones de persistencia CRUD
 * para la entidad {@link Libro} dentro del sistema LibraryApp.
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Libro
 */
public interface LibroDAO extends crud<Libro, String> {

    /**
     * Registra un nuevo libro en la base de datos.
     * @param libro Objeto {@link Libro} que contiene los datos a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException si el parámetro libro contiene datos inválidos o vacíos.
     * @throws RuntimeException si ocurre un error de conexión o en la base de datos.
     */
    boolean insertar(Libro libro);

    /**
     * Recupera el listado completo de libros registrados en el sistema.
     * @return Una lista de tipo {@link List} que contiene los objetos {@link Libro}.
     *         Retorna una lista vacía si no existen registros en la base de datos.
     */
    List<Libro> listar();
}