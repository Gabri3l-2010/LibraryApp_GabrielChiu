package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.AutorDAO;
import org.gc.exception.DaoException;
import org.gc.model.Autor;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link AutorDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link Autor} utilizando procedimientos almacenados en MySQL.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.AutorDAO
 * @see org.gc.model.Autor
 */
public class AutorDAOimpl implements AutorDAO {

    /**
     * Recupera el listado completo de autores registrados en la base de datos
     * mediante el procedimiento almacenado {@code sp_listarautores}.
     * @return Un {@link ArrayList} que contiene los objetos {@link Autor} registrados.
     * Retorna una lista vacía si no hay registros.
     * @throws DaoException si ocurre un error en la consulta SQL o de conexión.
     */
    @Override
    public ArrayList<Autor> listarTodos() {
        ArrayList<Autor> lista = new ArrayList<>();
        String sql = "{call sp_listarautores()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                Autor a = new Autor();
                a.setIdAutor(rs.getInt("id_autor"));
                a.setNombreAutor(rs.getString("nombre_autor"));
                a.setApellidoAutor(rs.getString("apellido_autor"));
                a.setNacionalidad(rs.getString("nacionalidad"));
                a.setBiografia(rs.getString("biografia"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar autores: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca un autor por su identificador único mediante el procedimiento almacenado {@code sp_buscarautor}.
     * @param idAutor El ID del autor a buscar.
     * @return El objeto {@link Autor} si se encuentra registrado; {@code null} en caso contrario.
     * @throws DaoException si ocurre un error de conexión o en la base de datos.
     */
    @Override
    public Autor buscarPorId(Integer idAutor) {
        Autor a = null;
        String sql = "{call sp_buscarautor(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idAutor);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    a = new Autor();
                    a.setIdAutor(rs.getInt("id_autor"));
                    a.setNombreAutor(rs.getString("nombre_autor"));
                    a.setApellidoAutor(rs.getString("apellido_autor"));
                    a.setNacionalidad(rs.getString("nacionalidad"));
                    a.setBiografia(rs.getString("biografia"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar autor: " + e.getMessage(), e);
        }
        return a;
    }

    /**
     * Registra un nuevo autor en la base de datos mediante el procedimiento almacenado {@code sp_insertarautor}.
     * @param autor Objeto {@link Autor} con la información a registrar.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException si ocurre un error al ejecutar la inserción en la base de datos.
     */
    @Override
    public boolean crear(Autor autor) {
        String sql = "{call sp_insertarautor(?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, autor.getNombreAutor());
            consulta.setString(2, autor.getApellidoAutor());
            consulta.setString(3, autor.getNacionalidad());
            consulta.setString(4, autor.getBiografia());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al insertar autor: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza la información de un autor existente mediante el procedimiento almacenado {@code sp_actualizarautor}.
     * @param autor Objeto {@link Autor} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException si ocurre un error al ejecutar la actualización en la base de datos.
     */
    @Override
    public boolean actualizar(Autor autor) {
        String sql = "{call sp_actualizarautor(?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, autor.getIdAutor());
            consulta.setString(2, autor.getNombreAutor());
            consulta.setString(3, autor.getApellidoAutor());
            consulta.setString(4, autor.getNacionalidad());
            consulta.setString(5, autor.getBiografia());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar autor: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un autor de la base de datos mediante el procedimiento almacenado {@code sp_eliminarautor}.
     * @param idAutor El identificador único del autor a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException si ocurre un error al ejecutar el borrado en la base de datos.
     */
    @Override
    public boolean eliminar(Integer idAutor) {
        String sql = "{call sp_eliminarautor(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idAutor);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar autor: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean insertar(Autor autor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}