package org.gc.dao;

import java.util.List;
import org.gc.model.AutorLibro;

/**
 * Contrato DAO que define las operaciones de persistencia CRUD
 * para la entidad relacional {@link AutorLibro} dentro del sistema LibraryApp.
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.AutorLibro
 */
public interface AutorLibroDAO extends crud<AutorLibro, Integer> {

    /**
     * Registra una nueva asociación entre un autor y un libro en la base de datos.
     * @param autorLibro Objeto {@link AutorLibro} que contiene los datos de la relación a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException si el parámetro autorLibro contiene datos inválidos.
     * @throws RuntimeException si ocurre un error de conexión o en la base de datos.
     */
    boolean insertar(AutorLibro autorLibro);

    /**
     * Recupera el listado completo de las relaciones entre autores y libros registradas en el sistema.
     * @return Una lista de tipo {@link List} que contiene los objetos {@link AutorLibro}.
     * Retorna una lista vacía si no existen registros en la base de datos.
     */
    List<AutorLibro> listar();
}