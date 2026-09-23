package org.gc.model;

import java.sql.Timestamp;

/**
 * Representa la entidad Usuario dentro del sistema.
 * @author informatica
 */
public class Usuario {

    /** Identificador único del usuario. */
    private int id;
    /** Nombre de usuario registrado para autenticación. */
    private String username;
    /** Correo electrónico del usuario. */
    private String email;
    /** Primer nombre del usuario. */
    private String firstName;
    /** Apellido del usuario. */
    private String lastName;
    /** Contraseña cifrada en formato Hash. */
    private String passwordHash;
    /** Rol asignado al usuario en el sistema. */
    private String rol;
    /** Estado activo o inactivo de la cuenta. */
    private boolean activo;
    /** Fecha y hora exacta de creación del registro. */
    private Timestamp fechaCreacion;
    /**
     * Constructor por defecto de la clase {@link Usuario}.
     */
    public Usuario() {
    }

    /**
     * Inicializa una instancia básica de {@link Usuario} con identificador, usuario y rol.
     * @param id Identificador único del usuario.
     * @param username Nombre de usuario.
     * @param rol Rol asignado dentro del sistema.
     */
    public Usuario(int id, String username, String rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
    }

    /**
     * Inicializa una nueva instancia de {@link Usuario} para el registro completo de datos.
     * @param username Nombre de usuario único.
     * @param email Correo electrónico válido.
     * @param firstName Primer nombre del usuario.
     * @param lastName Apellido del usuario.
     * @param passwordHash Hash cifrado de la contraseña.
     * @param rol Rol asignado dentro del sistema.
     */
    public Usuario(String username, String email, String firstName, String lastName,
                   String passwordHash, String rol) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }

    /**
     * Obtiene el rol del usuario.
     * @return {@link String} que representa el rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     * @param rol {@link String} con el nuevo rol asignado.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene el identificador único del usuario.
     * @return Un número entero que representa el identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     * @param id Identificador entero a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return {@link String} con el nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * @param username {@link String} con el nuevo nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el correo electrónico.
          * @return {@link String} con el correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email {@link String} con el nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el primer nombre del usuario.
     * @return {@link String} con el nombre.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el primer nombre del usuario.
     * @param firstName {@link String} con el primer nombre.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     * @return {@link String} con el apellido.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     * @param lastName {@link String} con el apellido.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el hash de la contraseña del usuario.
     * @return {@link String} con el hash cifrado de la contraseña.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Establece el hash de la contraseña del usuario.
     * @param passwordHash {@link String} con el nuevo hash cifrado.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Verifica si el usuario está activo.
     * @return {@code true} si está activo; {@code false} en caso contrario.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece el estado de activación del usuario.
     * @param activo {@code true} para activar el usuario; {@code false} para desactivarlo.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Obtiene la fecha y hora de creación del usuario.
     * @return Objeto de tipo {@link Timestamp} con la fecha de creación.
     */
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha y hora de creación del usuario.
     * @param fechaCreacion Objeto {@link Timestamp} con la fecha a establecer.
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Retorna el nombre de usuario como representación en texto del objeto.
     * @return {@link String} correspondiente al atributo username.
     */
    @Override
    public String toString() {
        return username;
    }
}