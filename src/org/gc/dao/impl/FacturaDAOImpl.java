package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.FacturaDAO;
import org.gc.exception.DaoException;
import org.gc.model.LineaFactura;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link FacturaDAO} que gestiona la consulta y
 * recuperación de datos de facturación mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.FacturaDAO
 * @see org.gc.model.LineaFactura
 */
public class FacturaDAOImpl implements FacturaDAO {

    /**
     * Busca y obtiene las líneas que componen una factura específica a partir de su número de venta
     * ejecutando el procedimiento almacenado {@code sp_buscar_factura}.
     * 
     * @param noVenta Número correlativo de la venta a consultar.
     * @return Un {@link ArrayList} de objetos {@link LineaFactura} con el detalle completo de la factura.
     * @throws DaoException Si ocurre un error de acceso a datos o conexión durante la consulta SQL.
     * @see java.util.ArrayList
     * @see org.gc.model.LineaFactura
     */
    @Override
    public ArrayList<LineaFactura> buscarFactura(int noVenta) {
        ArrayList<LineaFactura> lista = new ArrayList<>();
        String sql = "{call sp_buscar_factura(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, noVenta);
            try (ResultSet rs = consulta.executeQuery()) {
                while (rs.next()) {
                    LineaFactura linea = new LineaFactura();
                    linea.setNumeroFactura(rs.getInt("numero_factura"));
                    linea.setFechaEmision(rs.getString("fecha_emision"));
                    linea.setCuiCliente(rs.getLong("cui_cliente"));
                    linea.setNombreCliente(rs.getString("nombre_cliente"));
                    linea.setCorreoCliente(rs.getString("correo_cliente"));
                    linea.setIsbnLibro(rs.getString("isbn_libro"));
                    linea.setTituloLibro(rs.getString("titulo_libro"));
                    linea.setCantidad(rs.getInt("cantidad"));
                    linea.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    linea.setSubtotal(rs.getDouble("subtotal"));
                    linea.setUsuarioAtendio(rs.getString("usuario_atendio"));
                    linea.setGranTotal(rs.getDouble("gran_total"));
                    lista.add(linea);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar la factura: " + e.getMessage(), e);
        }
        return lista;
    }
}