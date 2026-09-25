package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.AutorLibroDAO;
import org.gc.exception.DaoException;
import org.gc.model.AutorLibro;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link AutorLibroDAO} que gestiona las operaciones
 * de persistencia para la relación entre autores y libros mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.AutorLibroDAO
 * @see org.gc.model.AutorLibro
 */
public class AutorLibroDAOImpl implements AutorLibroDAO {

    /**
     * Recupera la lista completa de relaciones entre autores y libros registradas en la base de datos
     * mediante la ejecución del procedimiento almacenado {@code sp_listarautoreslibro}.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link AutorLibro}.
     * @throws DaoException Si ocurre un error de acceso a datos o conexión al ejecutar la consulta SQL.
     * @see java.util.ArrayList
     * @see org.gc.model.AutorLibro
     */
    @Override
    public ArrayList<AutorLibro> listarTodos() {
        ArrayList<AutorLibro> lista = new ArrayList<>();
        String sql = "{call sp_listarautoreslibro()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                AutorLibro al = new AutorLibro();
                al.setIdAutorLibro(rs.getInt("id_autor_libro"));
                al.setIdAutor(rs.getInt("id_autor"));
                al.setIsbn(rs.getString("isbn"));
                lista.add(al);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar autores_libro: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca y obtiene la información de un registro autor_libro por su identificador único
     * ejecutando el procedimiento almacenado {@code sp_buscarautorlibro}.
     * 
     * @param idAutorLibro Identificador único del registro {@link AutorLibro} a buscar.
     * @return El objeto {@link AutorLibro} encontrado, o {@code null} si no existe coincidencia.
     * @throws DaoException Si ocurre un error al ejecutar la consulta en la base de datos.
     * @see org.gc.model.AutorLibro
     */
    @Override
    public AutorLibro buscarPorId(Integer idAutorLibro) {
        AutorLibro al = null;
        String sql = "{call sp_buscarautorlibro(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idAutorLibro);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    al = new AutorLibro();
                    al.setIdAutorLibro(rs.getInt("id_autor_libro"));
                    al.setIdAutor(rs.getInt("id_autor"));
                    al.setIsbn(rs.getString("isbn"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar autor_libro: " + e.getMessage(), e);
        }
        return al;
    }

    /**
     * Inserta una nueva asociación entre un autor y un libro en la base de datos
     * mediante el procedimiento almacenado {@code sp_insertarautorlibro}.
     * 
     * @param autorLibro Objeto {@link AutorLibro} que contiene los datos a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento almacenado.
     * @see org.gc.model.AutorLibro
     */
    @Override
    public boolean crear(AutorLibro autorLibro) {
        String sql = "{call sp_insertarautorlibro(?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, autorLibro.getIdAutor());
            consulta.setString(2, autorLibro.getIsbn());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al insertar autor_libro: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza una relación autor_libro existente en la base de datos
     * mediante el procedimiento almacenado {@code sp_actualizarautorlibro}.
     * 
     * @param autorLibro Objeto {@link AutorLibro} con los datos modificados. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al actualizar los datos en la base de datos.
     * @see org.gc.model.AutorLibro
     */
    @Override
    public boolean actualizar(AutorLibro autorLibro) {
        String sql = "{call sp_actualizarautorlibro(?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, autorLibro.getIdAutorLibro());
            consulta.setInt(2, autorLibro.getIdAutor());
            consulta.setString(3, autorLibro.getIsbn());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar autor_libro: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un registro de relación entre autor y libro de la base de datos
     * según su identificador único mediante el procedimiento almacenado {@code sp_eliminarautorlibro}.
     * 
     * @param idAutorLibro Identificador del registro a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de persistencia al intentar eliminar el registro.
     */
    @Override
    public boolean eliminar(Integer idAutorLibro) {
        String sql = "{call sp_eliminarautorlibro(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idAutorLibro);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar autor_libro: " + e.getMessage(), e);
        }
    }
}