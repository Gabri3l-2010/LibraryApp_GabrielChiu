package org.gc.dao;

import java.util.List;
import org.gc.model.LineaVenta;
import org.gc.model.Venta;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link Venta} y sus transacciones asociadas dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} utilizando un identificador de tipo {@link Integer}.
 * </p>
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Venta
 * @see org.gc.model.LineaVenta
 * @see org.gc.dao.Crud
 */
public interface VentaDAO extends Crud<Venta, Integer> {

    /**
     * Inserta el encabezado de la venta, procesa cada una de sus líneas de detalle 
     * y descuenta automáticamente el stock disponible de los productos correspondientes.
     * 
     * @param venta Objeto {@link Venta} con los datos generales del encabezado de la venta. No debe ser null.
     * @param lineas Lista de objetos {@link LineaVenta} que componen los detalles de la venta. No debe ser null ni estar vacía.
     * @return El número de venta {@code no_venta} generado tras la transacción exitosa, o {@code -1} en caso de falla.
     * @throws IllegalArgumentException Si el parámetro venta es nulo o la lista de líneas está vacía.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC, fallo en la transacción o error en la base de datos.
     * @see java.util.List
     * @see org.gc.model.Venta
     * @see org.gc.model.LineaVenta
     */
    int crearVenta(Venta venta, List<LineaVenta> lineas);
}