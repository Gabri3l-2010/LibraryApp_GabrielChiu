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
 * de persistencia para la entidad {@link Autor} mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.AutorDAO
 * @see org.gc.model.Autor
 */
public class AutorDAOImpl implements AutorDAO {

    /**
     * Recupera la lista completa de autores registrados en la base de datos
     * ejecutando el procedimiento almacenado {@code sp_listarautores}.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link Autor}.
     * @throws DaoException Si ocurre un error al ejecutar la consulta SQL o al conectar con la base de datos.
     * @see java.util.ArrayList
     * @see org.gc.model.Autor
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
     * Busca y recupera la información de un autor específico por su identificador único
     * ejecutando el procedimiento almacenado {@code sp_buscarautor}.
     * 
     * @param idAutor Identificador único del autor a buscar.
     * @return El objeto {@link Autor} correspondiente, o {@code null} si no se encuentra.
     * @throws DaoException Si ocurre un error durante la ejecución de la consulta SQL.
     * @see org.gc.model.Autor
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
     * Inserta un nuevo registro de autor en la base de datos mediante el
     * procedimiento almacenado {@code sp_insertarautor}.
     * 
     * @param autor Objeto {@link Autor} que contiene la información a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento almacenado.
     * @see org.gc.model.Autor
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
     * Actualiza los datos de un autor existente mediante el procedimiento
     * almacenado {@code sp_actualizarautor}.
     * 
     * @param autor Objeto {@link Autor} con la información modificada. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar la instrucción en la base de datos.
     * @see org.gc.model.Autor
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
     * Elimina un autor de la base de datos según su identificador único
     * mediante el procedimiento almacenado {@code sp_eliminarautor}.
     * 
     * @param idAutor Identificador del autor a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de persistencia al intentar eliminar el registro.
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
}