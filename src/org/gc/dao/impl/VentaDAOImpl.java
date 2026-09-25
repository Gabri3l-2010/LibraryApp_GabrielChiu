package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.gc.dao.DetalleVentaDAO;
import org.gc.dao.VentaDAO;
import org.gc.exception.DaoException;
import org.gc.model.DetalleVenta;
import org.gc.model.LineaVenta;
import org.gc.model.Venta;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link VentaDAO} para la gestión de ventas,
 * sus detalles y el descuento de inventario en la base de datos MySQL.
 *
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.VentaDAO
 * @see org.gc.model.Venta
 */
public class VentaDAOImpl implements VentaDAO {

    /**
     * Instancia DAO para gestionar las operaciones de detalle de venta.
     */
    private final DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAOImpl();

    /**
     * Obtiene la lista completa de ventas registradas mediante el procedimiento
     * almacenado {@code sp_listar_ventas}.
     *
     * @return Lista de tipo {@link ArrayList} con los objetos {@link Venta} encontrados.
     * @throws DaoException Si ocurre un error al realizar la consulta SQL.
     * @see java.util.ArrayList
     */
    @Override
    public ArrayList<Venta> listarTodos() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sql = "{call sp_listar_ventas()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                Venta v = new Venta();
                v.setNoVenta(rs.getInt("no_venta"));
                v.setFechaVenta(rs.getString("fecha_venta"));
                v.setTotalVenta(rs.getDouble("total_venta"));
                v.setCuiCliente(rs.getLong("cui_cliente"));
                v.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(v);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar ventas: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca y recupera un registro de venta por su número correlativo ejecutando
     * el procedimiento almacenado {@code sp_buscar_venta}.
     *
     * @param noVenta Número identificador de la venta a buscar.
     * @return El objeto {@link Venta} mapeado, o {@code null} si no existe.
     * @throws DaoException Si ocurre un error durante la búsqueda en la base de datos.
     */
    @Override
    public Venta buscarPorId(Integer noVenta) {
        Venta v = null;
        String sql = "{call sp_buscar_venta(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, noVenta);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    v = new Venta();
                    v.setNoVenta(rs.getInt("no_venta"));
                    v.setFechaVenta(rs.getString("fecha_venta"));
                    v.setTotalVenta(rs.getDouble("total_venta"));
                    v.setCuiCliente(rs.getLong("cui_cliente"));
                    v.setIdUsuario(rs.getInt("id_usuario"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar venta: " + e.getMessage(), e);
        }
        return v;
    }

    /**
     * Registra un nuevo encabezado de venta en la base de datos mediante el
     * procedimiento almacenado {@code sp_insertar_venta}.
     *
     * @param venta Objeto {@link Venta} que contiene la información del encabezado.
     * @return {@code true} si la venta fue insertada exitosamente; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error durante la inserción.
     */
    @Override
    public boolean crear(Venta venta) {
        String sql = "{call sp_insertar_venta(?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setDouble(1, venta.getTotalVenta());
            consulta.setString(2, String.valueOf(venta.getCuiCliente()));
            consulta.setInt(3, venta.getIdUsuario());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al insertar venta: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza la información de un registro de venta existente ejecutando el
     * procedimiento almacenado {@code sp_actualizar_venta}.
     *
     * @param venta Objeto {@link Venta} con la información modificada.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al modificar el registro.
     */
    @Override
    public boolean actualizar(Venta venta) {
        String sql = "{call sp_actualizar_venta(?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, venta.getNoVenta());
            if (venta.getFechaVenta() == null || venta.getFechaVenta().isEmpty()) {
                consulta.setNull(2, java.sql.Types.DATE);
            } else {
                consulta.setDate(2, java.sql.Date.valueOf(venta.getFechaVenta().substring(0, 10)));
            }
            consulta.setDouble(3, venta.getTotalVenta());
            consulta.setLong(4, venta.getCuiCliente());
            consulta.setInt(5, venta.getIdUsuario());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar venta: " + e.getMessage(), e);
        }
    }

    /**
     * Inserta el encabezado de una venta, obtiene el correlativo generado mediante
     * {@code LAST_INSERT_ID()}, procesa cada línea de detalle y descuenta el stock correspondiente.
     *
     * @param venta  Objeto {@link Venta} con los datos generales de la transacción.
     * @param lineas Lista de objetos {@link LineaVenta} con los ítems comprados.
     * @return El ID ({@code no_venta}) generado para la transacción, o {@code -1} si la inserción del encabezado falla.
     * @throws DaoException Si ocurre un error al insertar la venta o sus detalles.
     * @see org.gc.model.LineaVenta
     * @see org.gc.model.DetalleVenta
     */
    @Override
    public int crearVenta(Venta venta, List<LineaVenta> lineas) {
        int noVenta = -1;
        String sql = "{call sp_insertar_venta(?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setDouble(1, venta.getTotalVenta());
            consulta.setString(2, String.valueOf(venta.getCuiCliente()));
            consulta.setInt(3, venta.getIdUsuario());
            int filasAfectadas = consulta.executeUpdate();
            if (filasAfectadas > 0) {
                try (Statement sentencia = conexion.createStatement();
                        ResultSet rs = sentencia.executeQuery("SELECT LAST_INSERT_ID()")) {
                    if (rs.next()) {
                        noVenta = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al insertar venta: " + e.getMessage(), e);
        }

        if (noVenta > 0) {
            for (LineaVenta linea : lineas) {
                DetalleVenta detalle = new DetalleVenta(0, noVenta,
                        linea.getIsbn(), linea.getCantidad(), linea.getPrecio());
                detalleVentaDAO.crear(detalle);
                descontarStock(linea.getIsbn(), linea.getCantidad());
            }
        }
        return noVenta;
    }

    /**
     * Disminuye las unidades disponibles de un libro en el inventario mediante
     * el procedimiento almacenado {@code sp_descontar_stock}.
     *
     * @param isbn     Código ISBN del libro a actualizar.
     * @param cantidad Cantidad de unidades a descontar.
     * @return {@code true} si se actualizó el stock correctamente; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de actualización en la base de datos.
     */
    private boolean descontarStock(String isbn, int cantidad) {
        String sql = "{call sp_descontar_stock(?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, isbn);
            consulta.setInt(2, cantidad);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al descontar stock: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un registro de venta según su número correlativo mediante el
     * procedimiento almacenado {@code sp_eliminar_venta}.
     *
     * @param noVenta Número identificador de la venta a eliminar.
     * @return {@code true} si la venta fue eliminada; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el borrado en la base de datos.
     */
    @Override
    public boolean eliminar(Integer noVenta) {
        String sql = "{call sp_eliminar_venta(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, noVenta);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar venta: " + e.getMessage(), e);
        }
    }
}