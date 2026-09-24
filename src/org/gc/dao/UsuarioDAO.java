package org.gc.dao;

import java.util.ArrayList;
import org.gc.model.Usuario;

/**
 * Contrato DAO que define las operaciones de persistencia y autenticación
 * para la entidad {@link Usuario} dentro del sistema LibraryApp.
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Usuario
 */
public interface UsuarioDAO {

    /**
     * Valida las credenciales de un usuario para iniciar sesión en el sistema.
     * @param usernarme Nombre de usuario registrado.
     * @param passwordHash Contraseña encriptada (hash) del usuario.
     * @return El objeto {@link Usuario} correspondiente si las credenciales son válidas; {@code null} en caso contrario.
     */
    public Usuario iniciarSesion(String usernarme, String passwordHash);

    /**
     * Registra un nuevo usuario en la base de datos.
     
     * @param usuario Objeto {@link Usuario} con la información a registrar.
     * @return {@code true} si la creación fue exitosa; {@code false} en caso contrario.
     */
    public boolean crearUsuario(Usuario usuario);

    /**
     * Actualiza la información personal o de perfil de un usuario existente.
     * @param usuario Objeto {@link Usuario} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     */
    public boolean actualizarUsuario(Usuario usuario);

    /**
     * Modifica la contraseña de acceso de un usuario específico.
     * @param idUsuario Identificador único del usuario.
     * @param passwordHash Nueva contraseña encriptada (hash).
     * @return {@code true} si el cambio de contraseña fue exitoso; {@code false} en caso contrario.
     */
    public boolean cambiarPassword(int idUsuario, String passwordHash);

    /**
     * Desactiva el acceso de un usuario en el sistema sin eliminar su registro de la base de datos.
     * @param idUsuario Identificador único del usuario a desactivar.
     * @return {@code true} si el usuario fue desactivado correctamente; {@code false} en caso contrario.
     */
    public boolean desactivarUsuario(int idUsuario);

    /**
     * Elimina permanentemente el registro de un usuario de la base de datos.
     * @param idUsuario Identificador único del usuario a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     */
    public boolean eliminarUsuario(int idUsuario);

    /**
     * Recupera el listado completo de usuarios registrados en el sistema.
     * @return Un {@link ArrayList} que contiene los objetos {@link Usuario}.
     * Retorna una lista vacía si no existen registros.
     */
    public ArrayList<Usuario> listarTodosUsuarios();

    /**
     * Busca y obtiene los datos de un usuario por su identificador único.
     * @param idUsuario Identificador único del usuario a buscar.
     * @return El objeto {@link Usuario} si se encuentra registrado; {@code null} en caso contrario.
     */
    public Usuario obtenerUsuarioPorId(int idUsuario);
}