package org.gc.dao;

import java.util.List;
import org.gc.model.DetalleVenta;

/**
 * Contrato DAO que define las operaciones de persistencia CRUD
 * para la entidad {@link DetalleVenta} dentro del sistema LibraryApp[cite: 1].
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.DetalleVenta
 */
public interface DetalleVentaDAO extends crud<DetalleVenta, Integer> {

    /**
     * Registra un nuevo detalle de venta en la base de datos[cite: 1].
     * @param detalleVenta Objeto {@link DetalleVenta} que contiene los datos a registrar. No debe ser null[cite: 1].
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws IllegalArgumentException si el parámetro detalleVenta contiene datos inválidos o vacíos[cite: 1].
     * @throws RuntimeException si ocurre un error de conexión o en la base de datos[cite: 1].
     */
    boolean insertar(DetalleVenta detalleVenta);

    /**
     * Recupera el listado completo de detalles de venta registrados en el sistema[cite: 1].
     * @return Una lista de tipo {@link List} que contiene los objetos {@link DetalleVenta}[cite: 1].
     * Retorna una lista vacía si no existen registros en la base de datos[cite: 1].
     */
    List<DetalleVenta> listar();
}