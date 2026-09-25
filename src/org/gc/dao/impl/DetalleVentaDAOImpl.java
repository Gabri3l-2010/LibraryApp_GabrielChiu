package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.DetalleVentaDAO;
import org.gc.exception.DaoException;
import org.gc.model.DetalleVenta;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link DetalleVentaDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link DetalleVenta} mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.DetalleVentaDAO
 * @see org.gc.model.DetalleVenta
 */
public class DetalleVentaDAOImpl implements DetalleVentaDAO {

    /**
     * Recupera la lista completa de detalles de venta registrados en la base de datos
     * ejecutando el procedimiento almacenado {@code sp_listar_detalle_venta}.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link DetalleVenta}.
     * @throws DaoException Si ocurre un error de acceso a datos o conexión al ejecutar la consulta SQL.
     * @see java.util.ArrayList
     * @see org.gc.model.DetalleVenta
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
     * Busca y obtiene la información de un detalle de venta específico por su identificador único
     * ejecutando el procedimiento almacenado {@code sp_buscar_detalle_venta}.
     * 
     * @param idDetalleVenta Identificador único del detalle de venta a buscar.
     * @return El objeto {@link DetalleVenta} correspondiente, o {@code null} si no se encuentra.
     * @throws DaoException Si ocurre un error durante la ejecución de la consulta SQL.
     * @see org.gc.model.DetalleVenta
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
     * Inserta un nuevo registro de detalle de venta en la base de datos mediante el
     * procedimiento almacenado {@code sp_insertar_detalle_venta}.
     * 
     * @param detalleVenta Objeto {@link DetalleVenta} que contiene la información a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento almacenado.
     * @see org.gc.model.DetalleVenta
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
     * Actualiza los datos de un detalle de venta existente mediante el procedimiento
     * almacenado {@code sp_actualizar_detalle_venta}.
     * 
     * @param detalleVenta Objeto {@link DetalleVenta} con la información modificada. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar la instrucción en la base de datos.
     * @see org.gc.model.DetalleVenta
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
     * Elimina un detalle de venta de la base de datos según su identificador único
     * mediante el procedimiento almacenado {@code sp_eliminar_detalle_venta}.
     * 
     * @param idDetalleVenta Identificador único del detalle de venta a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de persistencia al intentar eliminar el registro.
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
}