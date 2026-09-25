package org.gc.dao;

import java.util.ArrayList;
import org.gc.model.LineaFactura;

/**
 * Contrato DAO que define las operaciones específicas para la consulta
 * y generación de facturas dentro del sistema.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.LineaFactura
 */
public interface FacturaDAO {

    /**
     * Recupera el detalle de líneas de factura pertenecientes a un número de venta específico.
     * 
     * @param noVenta Número identificador de la venta a consultar.
     * @return Una lista de tipo {@link ArrayList} con los objetos {@link LineaFactura} asociados a la venta.
     *         Retorna una lista vacía si no se encuentran detalles para el número indicado.
     * @throws IllegalArgumentException Si el parámetro noVenta es un valor inválido o menor a 1.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     * @see java.util.ArrayList
     * @see org.gc.model.LineaFactura
     */
    ArrayList<LineaFactura> buscarFactura(int noVenta);
}