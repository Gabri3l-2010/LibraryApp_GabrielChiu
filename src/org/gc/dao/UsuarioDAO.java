package org.gc.dao;

import java.util.ArrayList;
import org.gc.model.Usuario;

/**
 * Contrato DAO que define las operaciones de persistencia y autenticación
 * específicas para la entidad {@link Usuario} dentro del sistema.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Usuario
 */
public interface UsuarioDAO {

    /**
     * Valida las credenciales de un usuario para permitir su ingreso al sistema.
     * 
     * @param usernarme Nombre de usuario registrado en el sistema. No debe ser null ni estar vacío.
     * @param passwordHash Contraseña encriptada o hash correspondiente al usuario.
     * @return El objeto {@link Usuario} autenticado si las credenciales son válidas, o {@code null} en caso contrario.
     * @throws IllegalArgumentException Si los parámetros de entrada son nulos o están vacíos.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public Usuario iniciarSesion(String usernarme, String passwordHash);

    /**
     * Registra un nuevo usuario en la base de datos.
     * 
     * @param usuario Objeto {@link Usuario} con los datos a registrar. No debe ser null.
     * @return {@code true} si el registro fue exitoso; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el objeto usuario o sus campos obligatorios son nulos.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public boolean crearUsuario(Usuario usuario);

    /**
     * Actualiza la información personal o de perfil de un usuario existente.
     * 
     * @param usuario Objeto {@link Usuario} con los datos actualizados. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el usuario ingresado contiene datos inválidos.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public boolean actualizarUsuario(Usuario usuario);

    /**
     * Modifica la contraseña de acceso de un usuario específico.
     * 
     * @param idUsuario Identificador único del usuario.
     * @param passwordHash Nuevo hash o contraseña encriptada a registrar.
     * @return {@code true} si el cambio de contraseña fue exitoso; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el idUsuario es inválido o la contraseña está vacía.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public boolean cambiarPassword(int idUsuario, String passwordHash);

    /**
     * Desactiva la cuenta de un usuario sin eliminar su registro de la base de datos (eliminación lógica).
     * 
     * @param idUsuario Identificador único del usuario a desactivar.
     * @return {@code true} si la desactivación fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el idUsuario es menor o igual a cero.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public boolean desactivarUsuario(int idUsuario);

    /**
     * Elimina físicamente el registro de un usuario de la base de datos.
     * 
     * @param idUsuario Identificador único del usuario a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el idUsuario es menor o igual a cero.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public boolean eliminarUsuario(int idUsuario);

    /**
     * Recupera la lista completa de usuarios registrados en el sistema.
     * 
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link Usuario}.
     *         Retorna una lista vacía si no existen registros.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     * @see java.util.ArrayList
     */
    public ArrayList<Usuario> listarTodosUsuarios();

    /**
     * Busca y recupera la información de un usuario según su identificador único.
     * 
     * @param idUsuario Identificador único del usuario a buscar.
     * @return El objeto {@link Usuario} encontrado, o {@code null} si no existe coincidencia.
     * @throws IllegalArgumentException Si el idUsuario es menor o igual a cero.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    public Usuario obtenerUsuarioPorId(int idUsuario);
}