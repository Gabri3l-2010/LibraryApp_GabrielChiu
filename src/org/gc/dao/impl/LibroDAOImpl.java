package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.LibroDAO;
import org.gc.exception.DaoException;
import org.gc.model.Libro;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link LibroDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link Libro} mediante procedimientos almacenados.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.LibroDAO
 * @see org.gc.model.Libro
 */
public class LibroDAOImpl implements LibroDAO {

    /**
     * Recupera la lista completa de libros registrados en la base de datos
     * ejecutando el procedimiento almacenado {@code sp_listar_todos_libros}.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link Libro}.
     * @throws DaoException Si ocurre un error de acceso a datos o conexión al ejecutar la consulta SQL.
     * @see java.util.ArrayList
     * @see org.gc.model.Libro
     */
    @Override
    public ArrayList<Libro> listarTodos() {
        ArrayList<Libro> lista = new ArrayList<>();
        String sql = "{call sp_listar_todos_libros()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet rs = consulta.executeQuery()) {
            while (rs.next()) {
                Libro l = new Libro();
                l.setIsbn(rs.getString("isbn"));
                l.setTitulo(rs.getString("titulo"));
                l.setFechaPublicacion(rs.getString("fecha_publicacion"));
                l.setPrecio(rs.getDouble("precio"));
                l.setIdCategoria(rs.getInt("id_categoria"));
                l.setNitEditorial(rs.getString("nit_editorial"));
                l.setStock(rs.getInt("stock"));
                lista.add(l);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar libros: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca y obtiene la información de un libro específico por su código ISBN
     * ejecutando el procedimiento almacenado {@code sp_buscar_libro_id}.
     * 
     * @param isbn Código Internacional Estándar del Libro (ISBN) a buscar.
     * @return El objeto {@link Libro} correspondiente, o {@code null} si no se encuentra.
     * @throws DaoException Si ocurre un error durante la ejecución de la consulta SQL.
     * @see org.gc.model.Libro
     */
    @Override
    public Libro buscarPorId(String isbn) {
        Libro l = null;
        String sql = "{call sp_buscar_libro_id(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, isbn);
            try (ResultSet rs = consulta.executeQuery()) {
                if (rs.next()) {
                    l = new Libro();
                    l.setIsbn(rs.getString("isbn"));
                    l.setTitulo(rs.getString("titulo"));
                    l.setFechaPublicacion(rs.getString("fecha_publicacion"));
                    l.setPrecio(rs.getDouble("precio"));
                    l.setIdCategoria(rs.getInt("id_categoria"));
                    l.setNitEditorial(rs.getString("nit_editorial"));
                    l.setStock(rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al buscar libro: " + e.getMessage(), e);
        }
        return l;
    }

    /**
     * Inserta un nuevo registro de libro en la base de datos mediante el
     * procedimiento almacenado {@code sp_crear_libro}.
     * 
     * @param libro Objeto {@link Libro} que contiene la información a registrar. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento almacenado.
     * @see org.gc.model.Libro
     */
    @Override
    public boolean crear(Libro libro) {
        String sql = "{call sp_crear_libro(?,?,?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, libro.getIsbn());
            consulta.setString(2, libro.getTitulo());
            consulta.setString(3, libro.getFechaPublicacion());
            consulta.setDouble(4, libro.getPrecio());
            consulta.setInt(5, libro.getIdCategoria());
            consulta.setString(6, libro.getNitEditorial());
            consulta.setInt(7, libro.getStock());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al insertar libro: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza los datos de un libro existente mediante el procedimiento
     * almacenado {@code sp_actualizar_libro}.
     * 
     * @param libro Objeto {@link Libro} con la información modificada. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar la instrucción en la base de datos.
     * @see org.gc.model.Libro
     */
    @Override
    public boolean actualizar(Libro libro) {
        String sql = "{call sp_actualizar_libro(?,?,?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, libro.getIsbn());
            consulta.setString(2, libro.getTitulo());
            consulta.setString(3, libro.getFechaPublicacion());
            consulta.setDouble(4, libro.getPrecio());
            consulta.setInt(5, libro.getIdCategoria());
            consulta.setString(6, libro.getNitEditorial());
            consulta.setInt(7, libro.getStock());
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar libro: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un libro de la base de datos según su código ISBN
     * mediante el procedimiento almacenado {@code sp_eliminar_libro}.
     * 
     * @param isbn Código Internacional Estándar del Libro (ISBN) a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de persistencia al intentar eliminar el registro.
     */
    @Override
    public boolean eliminar(String isbn) {
        String sql = "{call sp_eliminar_libro(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, isbn);
            return consulta.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar libro: " + e.getMessage(), e);
        }
    }
}