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
 * Implementación de la interfaz {@link VentaDAO} para el manejo de la persistencia 
 * de la entidad {@link Venta} y sus operaciones asociadas en la base de datos MySQL.
 * @author Nombre del Estudiante
 * @version 1.0.0
 * @see VentaDAO
 * @see Venta
 * @see LineaVenta
 * @see DetalleVenta
 */
public class VentaDAOimpl implements VentaDAO {

    private final DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAOimpl();

    /**
     * Recupera el listado completo de las ventas registradas en la base de datos.
     * @return Un {@link ArrayList} de objetos {@link Venta} registrados. Retorna una lista vacía si no se encuentran registros.
     * @throws DaoException Si ocurre un error al realizar la consulta en la base de datos.
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
     * Busca una venta específica en la base de datos mediante su número de venta.
     * @param noVenta Identificador único de la venta a consultar.
     * @return El objeto {@link Venta} encontrado; {@code null} si no existe un registro coincidente.
     * @throws DaoException Si ocurre un error de acceso a la base de datos.
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
     * Inserta unicamente el encabezado de una venta mediante el procedimiento almacenado sp_insertar_venta.
     * @param venta Objeto {@link Venta} que contiene la información del encabezado. No debe ser null.
     * @return {@code true} si el encabezado de la venta se creó correctamente; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error durante el registro en la base de datos.
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
     * Actualiza la información de un registro de venta existente mediante el procedimiento sp_actualizar_venta.
     * @param venta Objeto {@link Venta} con los datos actualizados a persistir. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al procesar la actualización en la base de datos.
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
     * Inserta el encabezado de la venta, recupera el ID generado, inserta cada línea de detalle 
     * y descuenta el stock de los productos vendidos.
     * @param venta Objeto {@link Venta} que contiene el encabezado de la transacción. No debe ser null.
     * @param lineas Lista de objetos {@link LineaVenta} con el detalle de productos. No debe ser null ni estar vacía.
     * @return El número de venta (no_venta) generado tras la operación; {@code -1} en caso de fallo.
     * @throws DaoException Si ocurre un error durante el proceso de inserción o actualización de stock.
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
     * Disminuye las unidades disponibles en el inventario para un libro específico mediante el procedimiento sp_descontar_stock.
     * @param isbn Código ISBN del libro al que se le descontará stock.
     * @param cantidad Cantidad de unidades a descontar del inventario.
     * @return {@code true} si el descuento de stock se realizó con éxito; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al actualizar el inventario.
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
     * Elimina el registro de una venta en la base de datos mediante el procedimiento almacenado sp_eliminar_venta.
     * @param noVenta Identificador único de la venta a eliminar.
     * @return {@code true} si la venta fue eliminada correctamente; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error durante el proceso de eliminación.
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