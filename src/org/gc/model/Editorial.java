package org.gc.model;

/**
 * Clase que representa la entidad Editorial dentro del sistema.
 * Contiene la información legal y de contacto de la casa editorial.
 *@author Gabriel Chiu 
 * @version 1.0.0
 */
public class Editorial {

    /** Número de Identificación Tributaria (NIT) de la editorial. */
    private String nit;
    /** Nombre comercial o razón social de la editorial. */
    private String nombreEditorial;
    /** Número telefónico de contacto de la editorial. */
    private String telefonoEditorial;
    /** Dirección física de la editorial. */
    private String direccionEditoria;
    /**
     * Constructor por defecto de la clase {@link Editorial}.
     */
    public Editorial() {
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos de la editorial.
     * @param nit Número de Identificación Tributaria.
     * @param nombreEditorial Nombre de la editorial.
     * @param telefonoEditorial Teléfono de contacto.
     * @param direccionEditoria Dirección física.
     */
    public Editorial(String nit, String nombreEditorial, String telefonoEditorial, String direccionEditoria) {
        this.nit = nit;
        this.nombreEditorial = nombreEditorial;
        this.telefonoEditorial = telefonoEditorial;
        this.direccionEditoria = direccionEditoria;
    }

    /**
     * Obtiene el NIT de la editorial.
     * @return El NIT de la editorial.
     */
    public String getNit() {
        return nit;
    }

    /**
     * Establece el NIT de la editorial.
     * @param nit NIT a asignar.
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * Obtiene el nombre de la editorial.
     * @return El nombre de la editorial.
     */
    public String getNombreEditorial() {
        return nombreEditorial;
    }

    /**
     * Establece el nombre de la editorial.
     * @param nombreEditorial Nombre a asignar.
     */
    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    /**
     * Obtiene el teléfono de la editorial.
     * @return El teléfono de la editorial.
     */
    public String getTelefonoEditorial() {
        return telefonoEditorial;
    }

    /**
     * Establece el teléfono de la editorial.
     * @param telefonoEditorial Teléfono a asignar.
     */
    public void setTelefonoEditorial(String telefonoEditorial) {
        this.telefonoEditorial = telefonoEditorial;
    }

    /**
     * Obtiene la dirección de la editorial.
     * @return La dirección de la editorial.
     */
    public String getDireccionEditoria() {
        return direccionEditoria;
    }

    /**
     * Establece la dirección de la editorial.
     * @param direccionEditoria Dirección a asignar.
     */
    public void setDireccionEditoria(String direccionEditoria) {
        this.direccionEditoria = direccionEditoria;
    }

    /**
     * Retorna una representación en texto de la editorial.
     * @return El nombre de la editorial.
     */
    @Override
    public String toString() {
        return nombreEditorial;
    }
}