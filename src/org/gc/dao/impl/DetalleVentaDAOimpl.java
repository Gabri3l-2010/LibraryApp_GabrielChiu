package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.gc.dao.DetalleVentaDAO;
import org.gc.exception.DaoException;
import org.gc.model.DetalleVenta;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link DetalleVentaDAO} que gestiona las operaciones
 * de persistencia para los detalles de venta mediante procedimientos almacenados en MySQL[cite: 1].
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.DetalleVentaDAO
 * @see org.gc.model.DetalleVenta
 */
public class DetalleVentaDAOimpl implements DetalleVentaDAO {

    /**
     * Recupera el listado completo de los detalles de venta registrados en la base de datos
     * mediante el procedimiento almacenado {@code sp_listar_detalle_venta}[cite: 1].
     * 
     * @return Un {@link ArrayList} que contiene los objetos {@link DetalleVenta} registrados[cite: 1].
     *         Retorna una lista vacía si no existen registros.
     * @throws DaoException si ocurre un error en la consulta SQL o de conexión[cite: 1].
     */
    @Override
    public ArrayList<DetalleVenta> listarTodos() {
        ArrayList<DetalleVenta> lista = new ArrayList<>();
        String sql = "{call sp_listar_detalle_venta()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                DetalleVenta dv = new DetalleVenta();
                dv.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                dv.setNoVenta(rs.getInt("no_venta"));
                dv.setIsbn(rs.getString("isbn"));
                dv.setCantidad(rs.getInt("cantidad"));
                dv.setPrecio(rs.getDouble("precio"));
                lista.add(dv);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar detalle_venta: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca un detalle de venta por su identificador único mediante el procedimiento almacenado {@code sp_buscar_detalle_venta}[cite: 1].
     * @param idDetalleVenta El ID único del detalle de venta a buscar[cite: 1].
     * @return El objeto {@link DetalleVenta} si se encuentra registrado; {@code null} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error de conexión o en la base de datos[cite: 1].
     */
    @Override
    public DetalleVenta buscarPorId(Integer idDetalleVenta) {
        DetalleVenta dv = null;
        String sql = "{call sp_buscar_detalle_venta(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idDetalleVenta);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    dv = new DetalleVenta();
                    dv.setIdDetalleVenta(rs.getInt("id_detalle_venta"));
                    dv.setNoVenta(rs.getInt("no_venta"));
                    dv.setIsbn(rs.getString("isbn"));
                    dv.setCantidad(rs.getInt("cantidad"));
                    dv.setPrecio(rs.getDouble("precio"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar detalle_venta: " + e.getMessage(), e);
        }
        return dv;
    }

    /**
     * Registra un nuevo detalle de venta mediante el procedimiento almacenado {@code sp_insertar_detalle_venta}[cite: 1].
     * @param detalleVenta Objeto {@link DetalleVenta} con la información a registrar[cite: 1].
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error al ejecutar la inserción en la base de datos[cite: 1].
     */
    @Override
    public boolean crear(DetalleVenta detalleVenta) {
        String sql = "{call sp_insertar_detalle_venta(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, detalleVenta.getNoVenta());
            consulta.setString(2, detalleVenta.getIsbn());
            consulta.setInt(3, detalleVenta.getCantidad());
            consulta.setDouble(4, detalleVenta.getPrecio());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al insertar detalle_venta: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza la información de un detalle de venta existente mediante el procedimiento almacenado {@code sp_actualizar_detalle_venta}[cite: 1].
     * @param detalleVenta Objeto {@link DetalleVenta} con los datos actualizados[cite: 1].
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error al ejecutar la actualización en la base de datos[cite: 1].
     */
    @Override
    public boolean actualizar(DetalleVenta detalleVenta) {
        String sql = "{call sp_actualizar_detalle_venta(?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, detalleVenta.getIdDetalleVenta());
            consulta.setInt(2, detalleVenta.getNoVenta());
            consulta.setString(3, detalleVenta.getIsbn());
            consulta.setInt(4, detalleVenta.getCantidad());
            consulta.setDouble(5, detalleVenta.getPrecio());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar detalle_venta: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un detalle de venta de la base de datos mediante el procedimiento almacenado {@code sp_eliminar_detalle_venta}[cite: 1].
     * @param idDetalleVenta El identificador único del detalle de venta a eliminar[cite: 1].
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error al ejecutar el borrado en la base de datos[cite: 1].
     */
    @Override
    public boolean eliminar(Integer idDetalleVenta) {
        String sql = "{call sp_eliminar_detalle_venta(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idDetalleVenta);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar detalle_venta: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean insertar(DetalleVenta detalleVenta) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<DetalleVenta> listar() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}