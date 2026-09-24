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
 * de persistencia para la relación entre autores y libros mediante procedimientos almacenados en MySQL[cite: 1].
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.AutorLibroDAO
 * @see org.gc.model.AutorLibro
 */
public class AutorLibroDAOimpl implements AutorLibroDAO {

    /**
     * Recupera el listado completo de relaciones entre autores y libros registradas en la base de datos
     * mediante el procedimiento almacenado {@code sp_listarautoreslibro}[cite: 1].
     * @return Un {@link ArrayList} que contiene los objetos {@link AutorLibro} registrados[cite: 1].
     *         Retorna una lista vacía si no existen registros.
     * @throws DaoException si ocurre un error en la consulta SQL o de conexión[cite: 1].
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
     * Busca una relación autor-libro por su identificador único mediante el procedimiento almacenado {@code sp_buscarautorlibro}[cite: 1].
     * @param idAutorLibro El ID único de la relación autor-libro a buscar[cite: 1].
     * @return El objeto {@link AutorLibro} si se encuentra registrado; {@code null} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error de conexión o en la base de datos[cite: 1].
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
     * Registra una nueva asociación autor-libro mediante el procedimiento almacenado {@code sp_insertarautorlibro}[cite: 1].
     * @param autorLibro Objeto {@link AutorLibro} con la información a registrar[cite: 1].
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error al ejecutar la inserción en la base de datos[cite: 1].
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
     * Actualiza una relación autor-libro existente mediante el procedimiento almacenado {@code sp_actualizarautorlibro}[cite: 1].
     * @param autorLibro Objeto {@link AutorLibro} con los datos actualizados[cite: 1].
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error al ejecutar la actualización en la base de datos[cite: 1].
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
     * Elimina una relación autor-libro de la base de datos mediante el procedimiento almacenado {@code sp_eliminarautorlibro}[cite: 1].
     * @param idAutorLibro El identificador único del registro a eliminar[cite: 1].
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario[cite: 1].
     * @throws DaoException si ocurre un error al ejecutar el borrado en la base de datos[cite: 1].
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