package org.gc.dao;

import java.util.List;
import org.gc.model.Editorial;

/**
 * Contrato DAO que define las operaciones de persistencia CRUD
 * para la entidad {@link Editorial} dentro del sistema LibraryApp[cite: 1].
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Editorial
 */
public interface EditorialDAO extends crud<Editorial, String> {

    /**
     * Registra una nueva editorial en la base de datos[cite: 1].
     * @param editorial Objeto {@link Editorial} que contiene los datos a registrar. No debe ser null[cite: 1].
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws IllegalArgumentException si el parámetro editorial contiene datos inválidos o vacíos[cite: 1].
     * @throws RuntimeException si ocurre un error de conexión o en la base de datos[cite: 1].
     */
    boolean insertar(Editorial editorial);

    /**
     * Recupera el listado completo de editoriales registradas en el sistema[cite: 1].
     * @return Una lista de tipo {@link List} que contiene los objetos {@link Editorial}[cite: 1].
     *      Retorna una lista vacía si no existen registros en la base de datos[cite: 1].
     */
    List<Editorial> listar();
}