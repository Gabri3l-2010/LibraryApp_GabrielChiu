package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.gc.dao.ClienteDAO;
import org.gc.exception.DaoException;
import org.gc.model.Cliente;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link ClienteDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link Cliente} mediante procedimientos almacenados en MySQL.
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.ClienteDAO
 * @see org.gc.model.Cliente
 */
public class ClienteDAOimpl implements ClienteDAO {

    /**
     * Recupera el listado completo de clientes registrados en la base de datos
     * mediante el procedimiento almacenado {@code sp_listarclientes}.
     * @return Un {@link ArrayList} que contiene los objetos {@link Cliente} registrados.
     * Retorna una lista vacía si no existen registros en la base de datos.
     * @throws DaoException si ocurre un error en la consulta SQL o de conexión.
     */
    @Override
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "{call sp_listarclientes()}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consulta = conexion.prepareCall(sql); 
             ResultSet rs = consulta.executeQuery()) {
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
     * Busca un cliente por su Código Único de Identificación (CUI) 
     * mediante el procedimiento almacenado {@code sp_buscarcliente}.
     * @param cui El CUI del cliente a buscar
     * @return El objeto {@link Cliente} si se encuentra registrado; {@code null} en caso contrario
     * @throws DaoException si ocurre un error de conexión o en la base de datos
     */
    @Override
    public Cliente buscarPorId(Long cui) {
        Cliente c = null;
        String sql = "{call sp_buscarcliente(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consulta = conexion.prepareCall(sql)) {
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
     * Registra un nuevo cliente en la base de datos mediante el procedimiento almacenado {@code sp_insertarcliente}.
     * @param cliente Objeto {@link Cliente} con la información a registrar
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario
     * @throws DaoException si ocurre un error al ejecutar la inserción en la base de datos
     */
    @Override
    public boolean crear(Cliente cliente) {
        String sql = "{call sp_insertarcliente(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consulta = conexion.prepareCall(sql)) {
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
     * Actualiza la información de un cliente existente mediante el procedimiento almacenado {@code sp_actualizarcliente}.
     * @param cliente Objeto {@link Cliente} con los datos actualizados
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario
     * @throws DaoException si ocurre un error al ejecutar la actualización en la base de datos
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
     * Elimina un cliente de la base de datos según su CUI mediante el procedimiento almacenado {@code sp_eliminarcliente}.
     * @param cui El CUI del cliente a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario
     * @throws DaoException si ocurre un error al ejecutar el borrado en la base de datos.
     */
    @Override
    public boolean eliminar(Long cui) {
        String sql = "{call sp_eliminarcliente(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setLong(1, cui);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Constructor por defecto de la clase.
     */
    public ClienteDAOimpl() {
    }

    @Override
    public boolean insertar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Cliente> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}