package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.EditorialDAO;
import org.gc.exception.DaoException;
import org.gc.model.Editorial;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link EditorialDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link Editorial} mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.EditorialDAO
 * @see org.gc.model.Editorial
 */
public class EditorialDAOImpl implements EditorialDAO {

    /**
     * Recupera la lista completa de editoriales registradas en la base de datos
     * ejecutando el procedimiento almacenado {@code sp_listar_todos_editoriales}.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link Editorial}.
     * @throws DaoException Si ocurre un error de acceso a datos o conexión al ejecutar la consulta SQL.
     * @see java.util.ArrayList
     * @see org.gc.model.Editorial
     */
    @Override
    public ArrayList<Editorial> listarTodos() {
        ArrayList<Editorial> lista = new ArrayList<>();
        String sql = "{call sp_listar_todos_editoriales()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                Editorial e = new Editorial();
                e.setNit(rs.getString("nit"));
                e.setNombreEditorial(rs.getString("nombre_editorial"));
                e.setTelefonoEditorial(rs.getString("telefono_editorial"));
                e.setDireccionEditoria(rs.getString("direccion_editorial"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error al listar editoriales: " + ex.getMessage(), ex);
        }
        return lista;
    }

    /**
     * Busca y obtiene la información de una editorial específica por su Número de Identificación Tributaria (NIT)
     * ejecutando el procedimiento almacenado {@code sp_buscar_editorial_por_id}.
     * 
     * @param nit Número de Identificación Tributaria de la editorial a buscar.
     * @return El objeto {@link Editorial} correspondiente, o {@code null} si no se encuentra.
     * @throws DaoException Si ocurre un error durante la ejecución de la consulta SQL.
     * @see org.gc.model.Editorial
     */
    @Override
    public Editorial buscarPorId(String nit) {
        Editorial e = null;
        String sql = "{call sp_buscar_editorial_por_id(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, nit);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    e = new Editorial();
                    e.setNit(rs.getString("nit"));
                    e.setNombreEditorial(rs.getString("nombre_editorial"));
                    e.setTelefonoEditorial(rs.getString("telefono_editorial"));
                    e.setDireccionEditoria(rs.getString("direccion_editorial"));
                }
            }
        } catch (SQLException ex) {
            throw new DaoException("Error al buscar editorial: " + ex.getMessage(), ex);
        }
        return e;
    }

    /**
     * Inserta un nuevo registro de editorial en la base de datos mediante el
     * procedimiento almacenado {@code sp_crear_editorial}.
     * 
     * @param editorial Objeto {@link Editorial} que contiene la información a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento almacenado.
     * @see org.gc.model.Editorial
     */
    @Override
    public boolean crear(Editorial editorial) {
        String sql = "{call sp_crear_editorial(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, editorial.getNit());
            consulta.setString(2, editorial.getNombreEditorial());
            consulta.setString(3, editorial.getTelefonoEditorial());
            consulta.setString(4, editorial.getDireccionEditoria());
            return consulta.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Error al insertar editorial: " + ex.getMessage(), ex);
        }
    }

    /**
     * Actualiza los datos de una editorial existente mediante el procedimiento
     * almacenado {@code sp_actualizar_editorial}.
     * 
     * @param editorial Objeto {@link Editorial} con la información modificada. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar la instrucción en la base de datos.
     * @see org.gc.model.Editorial
     */
    @Override
    public boolean actualizar(Editorial editorial) {
        String sql = "{call sp_actualizar_editorial(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, editorial.getNit());
            consulta.setString(2, editorial.getNombreEditorial());
            consulta.setString(3, editorial.getTelefonoEditorial());
            consulta.setString(4, editorial.getDireccionEditoria());
            return consulta.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Error al actualizar editorial: " + ex.getMessage(), ex);
        }
    }

    /**
     * Elimina una editorial de la base de datos según su Número de Identificación Tributaria (NIT)
     * mediante el procedimiento almacenado {@code sp_eliminar_editorial}.
     * 
     * @param nit Número de Identificación Tributaria de la editorial a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de persistencia al intentar eliminar el registro.
     */
    @Override
    public boolean eliminar(String nit) {
        String sql = "{call sp_eliminar_editorial(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, nit);
            return consulta.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Error al eliminar editorial: " + ex.getMessage(), ex);
        }
    }
}