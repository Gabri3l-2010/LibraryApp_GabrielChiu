
package org.gc.dao;

import org.gc.model.Autor;

/**
 * Contrato DAO que define las operaciones de persistencia CRUD
 * para la entidad {@link Autor} dentro del sistema LibraryApp.
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Autor
 */
public interface AutorDAO extends crud<Autor, Integer> {

    /**
     * Registra un nuevo autor en la base de datos.
     * @param autor Objeto {@link Autor} que contiene los datos a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException si el parámetro autor contiene datos inválidos o vacíos.
     * @throws RuntimeException si ocurre un error de conexión o en la base de datos.
     */
    boolean insertar(Autor autor);

    /**
     * Recupera el listado completo de autores registrados en el sistema.
     * @return Una lista de tipo {@link List} que contiene los objetos {@link Autor}.
     *         Retorna una lista vacía si no existen registros en la base de datos.
     */
}