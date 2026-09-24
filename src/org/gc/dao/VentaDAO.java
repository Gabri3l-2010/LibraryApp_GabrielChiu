package org.gc.dao;

import java.util.List;
import org.gc.exception.DaoException;
import org.gc.model.LineaVenta;
import org.gc.model.Venta;

/**
 * Contrato DAO que define las operaciones de persistencia específicas 
 * para la entidad {@link Venta} dentro del sistema.
 * Extiende de {@link crud} para incluir las operaciones básicas de acceso a datos.
 * @author Nombre del Estudiante
 * @version 1.0.0
 * @see Venta
 * @see LineaVenta
 * @see crud
 */
public interface VentaDAO extends crud<Venta, Integer> {

    /**
     * Inserta el encabezado de una venta, registra sus líneas de detalle asociadas y 
     * actualiza el stock correspondiente en la base de datos dentro de una transacción.
     * @param venta Objeto {@link Venta} que contiene la información general del encabezado de la venta. No debe ser null.
     * @param lineas Lista de objetos {@link LineaVenta} que representan los productos y detalles de la venta. No debe ser null ni estar vacía.
     * @return El número de venta (no_venta) generado tras la inserción exitosa; {@code -1} si la operación falla.
     * @throws IllegalArgumentException Si la venta o la lista de líneas contienen datos inválidos o nulos.
     * @throws DaoException Si ocurre un error de conexión con JDBC o durante la ejecución en la base de datos.
     */
    int crearVenta(Venta venta, List<LineaVenta> lineas);
}