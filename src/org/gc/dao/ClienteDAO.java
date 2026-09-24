
package org.gc.dao;

import java.util.List;
import org.gc.model.Cliente;

/**
 * Contrato DAO que define las operaciones de persistencia CRUD
 * para la entidad {@link Cliente} dentro del sistema LibraryApp[cite: 1].
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Cliente
 */
public interface ClienteDAO extends crud<Cliente, Long> {

    /**
     * Registra un nuevo cliente en la base de datos[cite: 1].
     * @param cliente Objeto {@link Cliente} que contiene los datos a registrar. No debe ser null[cite: 1].
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws IllegalArgumentException si el parámetro cliente contiene datos inválidos o vacíos[cite: 1].
     * @throws RuntimeException si ocurre un error de conexión o en la base de datos[cite: 1].
     */
    boolean insertar(Cliente cliente);

    /**
     * Recupera el listado completo de clientes registrados en el sistema[cite: 1].
     * @return Una lista de tipo {@link List} que contiene los objetos {@link Cliente}[cite: 1].
     *         Retorna una lista vacía si no existen registros en la base de datos[cite: 1].
     */
    List<Cliente> listar();
}