/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gc.manager;

import org.gc.model.Usuario;

/**
 * Gestiona el contexto de sesión de la aplicación.
 * <p>
 * Esta clase implementa el patrón Singleton para mantener una única instancia
 * encargada de almacenar y administrar el usuario que actualmente tiene una
 * sesión iniciada en el sistema.
 * </p>
 *
 * @author gchiu-2026118
 */
public class SesionContext {

    /**
     * Única instancia de la clase {@code SesionContext}.
     */
    private static SesionContext instancia;

    /**
     * Usuario que actualmente tiene una sesión iniciada.
     * Si no existe una sesión activa, su valor es {@code null}.
     */
    private Usuario usuarioActual;

    /**
     * Constructor privado que evita la creación directa de instancias
     * de {@code SesionContext}.
     * <p>
     * La instancia debe obtenerse mediante el método
     * {@link #getInstancia()}.
     * </p>
     */
    private SesionContext() {
    }

    /**
     * Obtiene la única instancia de {@code SesionContext}.
     * <p>
     * Si la instancia todavía no ha sido creada, este método la crea antes
     * de retornarla. El método está sincronizado para controlar el acceso
     * concurrente durante la creación de la instancia.
     * </p>
     *
     * @return la instancia única de {@code SesionContext}.
     */
    public static synchronized SesionContext getInstancia() {
        if (instancia == null) {
            instancia = new SesionContext();
        }
        return instancia;
    }

    /**
     * Obtiene el usuario que actualmente tiene una sesión iniciada.
     *
     * @return el usuario actual, o {@code null} si no existe una sesión activa.
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Establece el usuario que tendrá la sesión activa.
     *
     * @param usuario usuario que se establecerá como usuario actual.
     */
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    /**
     * Cierra la sesión del usuario actual.
     * <p>
     * Elimina la referencia al usuario almacenado estableciendo
     * {@code usuarioActual} en {@code null}.
     * </p>
     */
    public void cerrarSesion() {
        this.usuarioActual = null;
    }
}