package org.gc.model;

/**
 * Clase que representa la entidad Cliente dentro del sistema.
 * Contiene la información personal y de contacto asociada a un cliente.
 * @author Nombre del Estudiante
 * @version 1.0.0
 */
public class Cliente {

    /** Código Único de Identificación del cliente. */
    private long cui;
    /** Nombre(s) del cliente. */
    private String nombreCliente;
    /** Apellido(s) del cliente. */
    private String apellidoCliente;
    /** Dirección de correo electrónico del cliente. */
    private String correoElectronico;
    /**
     * Constructor por defecto de la clase {@link Cliente}.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos del cliente.
     * @param cui Código Único de Identificación.
     * @param nombreCliente Nombre(s) del cliente.
     * @param apellidoCliente Apellido(s) del cliente.
     * @param correoElectronico Correo electrónico del cliente.
     */
    public Cliente(long cui, String nombreCliente, String apellidoCliente, String correoElectronico) {
        this.cui = cui;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el CUI del cliente.
     * @return El número de CUI.
     */
    public long getCui() {
        return cui;
    }

    /**
     * Establece el CUI del cliente.
     * @param cui Código Único de Identificación a asignar.
     */
    public void setCui(long cui) {
        this.cui = cui;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombreCliente Nombre a asignar al cliente.
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el apellido del cliente.
     * @return El apellido del cliente.
     */
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    /**
     * Establece el apellido del cliente.
     * @param apellidoCliente Apellido a asignar al cliente.
     */
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return El correo electrónico del cliente.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del cliente.
     * @param correoElectronico Correo electrónico a asignar.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Retorna una representación en texto del cliente.
     * @return Cadena que concatena el nombre y apellido del cliente.
     */
    @Override
    public String toString() {
        return nombreCliente + " " + apellidoCliente;
    }
}