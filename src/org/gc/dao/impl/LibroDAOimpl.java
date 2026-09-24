package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.gc.dao.LibroDAO;
import org.gc.exception.DaoException;
import org.gc.model.Libro;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link LibroDAO} que gestiona las operaciones
 * de persistencia para la entidad {@link Libro} utilizando procedimientos almacenados en MySQL.
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.dao.LibroDAO
 * @see org.gc.model.Libro
 */
public class LibroDAOimpl implements LibroDAO {

    /**
     * Recupera el listado completo de libros registrados en la base de datos
     * mediante el procedimiento almacenado {@code sp_listar_todos_libros}.
     * @return Un {@link ArrayList} que contiene los objetos {@link Libro} registrados.
     *         Retorna una lista vacía si no existen registros.
     * @throws DaoException si ocurre un error en la consulta SQL o de conexión.
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
     * Busca un libro por su código ISBN mediante el procedimiento almacenado {@code sp_buscar_libro_id}.
     * @param isbn El código ISBN del libro a buscar.
     * @return El objeto {@link Libro} si se encuentra registrado; {@code null} en caso contrario.
     * @throws DaoException si ocurre un error de conexión o en la base de datos.
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
     * Registra un nuevo libro en la base de datos mediante el procedimiento almacenado {@code sp_crear_libro}.
     * @param libro Objeto {@link Libro} con la información a registrar.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException si ocurre un error al ejecutar la inserción en la base de datos.
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
     * Actualiza la información de un libro existente mediante el procedimiento almacenado {@code sp_actualizar_libro}.
     * 
     * @param libro Objeto {@link Libro} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException si ocurre un error al ejecutar la actualización en la base de datos.
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
     * Elimina un libro de la base de datos mediante el procedimiento almacenado {@code sp_eliminar_libro}.
     * @param isbn El código ISBN del libro a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws DaoException si ocurre un error al ejecutar el borrado en la base de datos.
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

    @Override
    public boolean insertar(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Libro> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}