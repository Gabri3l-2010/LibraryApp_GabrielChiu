package org.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.gc.dao.UsuarioDAO;
import org.gc.exception.DaoException;
import org.gc.model.Usuario;
import org.gc.util.Conexion;

/**
 * Implementación de la interfaz {@link UsuarioDAO} que gestiona las operaciones de persistencia CRUD 
 * para la entidad {@link Usuario} utilizando procedimientos almacenados en una base de datos MySQL.
 *
 * @author Nombre del Estudiante
 * @version 1.0.0
 * @see UsuarioDAO
 * @see Usuario
 */
public class UsuarioDAOimpl implements UsuarioDAO {

    /**
     * Valida las credenciales de un usuario para realizar el inicio de sesión mediante el procedimiento almacenado sp_iniciar_sesion.
     * @param usernarme Nombre de usuario único del sistema.
     * @param passwordHash Hash de la contraseña del usuario.
     * @return El objeto {@link Usuario} autenticado con sus datos básicos si las credenciales son correctas; {@code null} en caso contrario.
     * @throws DaoException Si ocurre un error de acceso a la base de datos MySQL durante la ejecución.
     */
    @Override
    public Usuario iniciarSesion(String usernarme, String passwordHash) {
        Usuario usuario = null;
        String sql = "{call sp_iniciar_sesion(?,?)}";

        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {

            consulta.setString(1, usernarme);
            consulta.setString(2, passwordHash);

            try (ResultSet tablaResultado = consulta.executeQuery()) {
                if (tablaResultado.next()) {
                    usuario = new Usuario();
                    usuario.setId(tablaResultado.getInt(1));
                    usuario.setUsername(tablaResultado.getString(2));
                    usuario.setRol(tablaResultado.getString(3));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al iniciar sesion: " + e.getMessage(), e);
        }

        return usuario;
    }

    /**
     * Registra un nuevo usuario en la base de datos mediante el procedimiento almacenado sp_crear_usuario.
     * @param usuario Objeto {@link Usuario} que contiene la información del nuevo registro. No debe ser null.
     * @return {@code true} si la inserción fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al ejecutar el procedimiento en la base de datos.
     */
    @Override
    public boolean crearUsuario(Usuario usuario) {
        String sql = "{call sp_crear_usuario(?,?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setString(1, usuario.getUsername());
            consulta.setString(2, usuario.getEmail());
            consulta.setString(3, usuario.getFirstName());
            consulta.setString(4, usuario.getLastName());
            consulta.setString(5, usuario.getPasswordHash());
            consulta.setString(6, usuario.getRol());
            int filasAfectadas = consulta.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al crear usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza la información de un usuario existente en la base de datos mediante el procedimiento almacenado sp_actualizar_usuario.
     * @param usuario Objeto {@link Usuario} con los datos actualizados. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al procesar la actualización en la base de datos.
     */
    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "{call sp_actualizar_usuario(?,?,?,?,?,?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, usuario.getId());
            consulta.setString(2, usuario.getUsername());
            consulta.setString(3, usuario.getEmail());
            consulta.setString(4, usuario.getFirstName());
            consulta.setString(5, usuario.getLastName());
            consulta.setString(6, usuario.getRol());
            consulta.setBoolean(7, usuario.isActivo());
            int filasAfectadas = consulta.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Modifica la contraseña de un usuario mediante el procedimiento almacenado sp_cambiar_password.
          * @param idUsuario Identificador único del usuario.
     * @param passwordHash Nuevo hash de la contraseña.
     * @return {@code true} si la contraseña se actualizó correctamente; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error de ejecución en la base de datos.
     */
    @Override
    public boolean cambiarPassword(int idUsuario, String passwordHash) {
        String sql = "{call sp_cambiar_password(?,?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idUsuario);
            consulta.setString(2, passwordHash);
            int filasAfectadas = consulta.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al cambiar password: " + e.getMessage(), e);
        }
    }

    /**
     * Desactiva el estado de un usuario en la base de datos mediante el procedimiento almacenado sp_desactivar_usuario.
     * @param idUsuario Identificador único del usuario a desactivar.
     * @return {@code true} si el estado del usuario fue modificado a inactivo; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error en la base de datos al desactivar el registro.
     */
    @Override
    public boolean desactivarUsuario(int idUsuario) {
        String sql = "{call sp_desactivar_usuario(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idUsuario);
            int filasAfectadas = consulta.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al desactivar usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina físicamente el registro de un usuario mediante el procedimiento almacenado sp_eliminar_usuario.
     *
     * @param idUsuario Identificador único del usuario a eliminar.
     * @return {@code true} si el registro fue eliminado exitosamente; {@code false} en caso contrario.
     * @throws DaoException Si ocurre un error al intentar eliminar el registro de la base de datos.
     */
    @Override
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "{call sp_eliminar_usuario(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idUsuario);
            int filasAfectadas = consulta.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Recupera el listado completo de todos los usuarios registrados mediante el procedimiento almacenado sp_listar_todos_usuarios.
     *
     * @return Un {@link ArrayList} que contiene los objetos {@link Usuario} registrados. Retorna una lista vacía si no existen registros.
     * @throws DaoException Si ocurre un error de consulta con JDBC o la base de datos.
     */
    @Override
    public ArrayList<Usuario> listarTodosUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "{call sp_listar_todos_usuarios()}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql);
                ResultSet tablaResultado = consulta.executeQuery()) {
            while (tablaResultado.next()) {
                Usuario u = new Usuario();
                u.setId(tablaResultado.getInt("id_usuario"));
                u.setUsername(tablaResultado.getString("username"));
                u.setEmail(tablaResultado.getString("email"));
                u.setFirstName(tablaResultado.getString("first_name"));
                u.setLastName(tablaResultado.getString("last_name"));
                u.setRol(tablaResultado.getString("rol"));
                u.setActivo(tablaResultado.getBoolean("activo"));
                u.setFechaCreacion(tablaResultado.getTimestamp("fecha_creacion"));
                lista.add(u);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar todos los usuarios: " + e.getMessage(), e);
        }
        return lista;
    }

    /**
     * Busca y recupera la información de un usuario específico a través de su ID con el procedimiento sp_obtener_usuario_por_id.
     * @param idUsuario Identificador único del usuario.
     * @return El objeto {@link Usuario} encontrado; {@code null} si no se halló ningún registro coincidente.
     * @throws DaoException Si ocurre un error al realizar la consulta en la base de datos.
     */
    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        String sql = "{call sp_obtener_usuario_por_id(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consulta = conexion.prepareCall(sql)) {
            consulta.setInt(1, idUsuario);
            try (ResultSet tablaResultado = consulta.executeQuery()) {
                if (tablaResultado.next()) {
                    usuario = new Usuario();
                    usuario.setId(tablaResultado.getInt("id_usuario"));
                    usuario.setUsername(tablaResultado.getString("username"));
                    usuario.setEmail(tablaResultado.getString("email"));
                    usuario.setFirstName(tablaResultado.getString("first_name"));
                    usuario.setLastName(tablaResultado.getString("last_name"));
                    usuario.setRol(tablaResultado.getString("rol"));
                    usuario.setActivo(tablaResultado.getBoolean("activo"));
                    usuario.setFechaCreacion(tablaResultado.getTimestamp("fecha_creacion"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error al obtener usuario por id: " + e.getMessage(), e);
        }
        return usuario;
    }

}