package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.ClienteDAO;
import org.gc.exception.DaoException;
import org.gc.model.Cliente;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link ClienteDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link Cliente} mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.ClienteDAO
 * @see org.gc.model.Cliente
 */
public class ClienteDAOImpl implements ClienteDAO {

    /**
     * Recupera la lista completa de clientes registrados en la base de datos
     * ejecutando el procedimiento almacenado {@code sp_listarclientes}.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link Cliente}.
     * @throws DaoException Si ocurre un error de acceso a datos o conexión al ejecutar la consulta SQL.
     * @see java.util.ArrayList
     * @see org.gc.model.Cliente
     */
    @Override
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "{call sp_listarclientes()}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consulta = conexion.prepareCall(sql); ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCui(rs.getLong("cui"));
                c.setNombreCliente(rs.getString("nombre_cliente"));
                c.setApellidoCliente(rs.getString("apellido_cliente"));
                c.setCorreoElectronico(rs.getString("correo_electronico"));
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar clientes: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca y obtiene la información de un cliente específico por su número de CUI
     * ejecutando el procedimiento almacenado {@code sp_buscarcliente}.
     * 
     * @param cui Código Único de Identificación del cliente a buscar.
     * @return El objeto {@link Cliente} correspondiente, o {@code null} si no se encuentra.
     * @throws DaoException Si ocurre un error durante la ejecución de la consulta SQL.
     * @see org.gc.model.Cliente
     */
    @Override
    public Cliente buscarPorId(Long cui) {
        Cliente c = null;
        String sql = "{call sp_buscarcliente(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setLong(1, cui);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setCui(rs.getLong("cui"));
                    c.setNombreCliente(rs.getString("nombre_cliente"));
                    c.setApellidoCliente(rs.getString("apellido_cliente"));
                    c.setCorreoElectronico(rs.getString("correo_electronico"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar cliente: " + e.getMessage(), e);
        }
        return c;
    }

    /**
     * Inserta un nuevo registro de cliente en la base de datos mediante el
     * procedimiento almacenado {@code sp_insertarcliente}.
     * 
     * @param cliente Objeto {@link Cliente} que contiene la información a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento almacenado.
     * @see org.gc.model.Cliente
     */
    @Override
    public boolean crear(Cliente cliente) {
        String sql = "{call sp_insertarcliente(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setLong(1, cliente.getCui());
            consulta.setString(2, cliente.getNombreCliente());
            consulta.setString(3, cliente.getApellidoCliente());
            consulta.setString(4, cliente.getCorreoElectronico());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al insertar cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza los datos de un cliente existente mediante el procedimiento
     * almacenado {@code sp_actualizarcliente}.
     * 
     * @param cliente Objeto {@link Cliente} con la información modificada. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar la instrucción en la base de datos.
     * @see org.gc.model.Cliente
     */
    @Override
    public boolean actualizar(Cliente cliente) {
        String sql = "{call sp_actualizarcliente(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setLong(1, cliente.getCui());
            consulta.setString(2, cliente.getNombreCliente());
            consulta.setString(3, cliente.getApellidoCliente());
            consulta.setString(4, cliente.getCorreoElectronico());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un cliente de la base de datos según su número de CUI
     * mediante el procedimiento almacenado {@code sp_eliminarcliente}.
     * 
     * @param cui Código Único de Identificación del cliente a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de persistencia al intentar eliminar el registro.
     */
    @Override
    public boolean eliminar(Long cui) {
        String sql = "{call sp_eliminarcliente(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setLong(1, cui);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar cliente: " + e.getMessage(), e);
        }
    }
}