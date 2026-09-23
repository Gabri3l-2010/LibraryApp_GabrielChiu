package org.gc.model;

/**
 * Representa la entidad de modelo para gestionar la información de una venta.
 * @author Nombre del Estudiante
 * @version 1.0.0
 */
public class Venta {

    /** Número identificador único de la venta. */
    private int noVenta;
    /** Fecha en la que se realizó la venta. */
    private String fechaVenta;
    /** Monto total acumulado de la venta. */
    private double totalVenta;
    /** Código Único de Identificación (CUI) del cliente que realiza la compra. */
    private long cuiCliente;
    /** Identificador del usuario o empleado que registró la venta. */
    private int idUsuario;

    /**
     * Constructor por defecto para instanciar una nueva venta sin valores iniciales.
     */
    public Venta() {
    }

    /**
     * Constructor con parámetros para inicializar todos los campos de una venta.
     * @param noVenta Número identificador de la venta.
     * @param fechaVenta Fecha de realización de la venta.
     * @param totalVenta Monto total de la venta.
     * @param cuiCliente CUI del cliente asociado.
     * @param idUsuario ID del usuario que registra la venta.
     */
    public Venta(int noVenta, String fechaVenta, double totalVenta, long cuiCliente, int idUsuario) {
        this.noVenta = noVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.cuiCliente = cuiCliente;
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el número de venta.
     * @return El número identificador de la venta.
     */
    public int getNoVenta() {
        return noVenta;
    }

    /**
     * Establece el número de venta.
     * @param noVenta Número identificador de la venta a asignar.
     */
    public void setNoVenta(int noVenta) {
        this.noVenta = noVenta;
    }

    /**
     * Obtiene la fecha de realización de la venta.
     * @return La fecha de la venta en formato String.
     */
    public String getFechaVenta() {
        return fechaVenta;
    }

    /**
     * Establece la fecha de realización de la venta.
     * @param fechaVenta La fecha a asignar para la venta.
     */
    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * Obtiene el total de la venta.
     * @return El monto total de la venta.
     */
    public double getTotalVenta() {
        return totalVenta;
    }

    /**
     * Establece el total de la venta.
     * @param totalVenta El monto total a asignar.
     */
    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    /**
     * Obtiene el CUI del cliente que realizó la compra.
     * @return El número CUI del cliente.
     */
    public long getCuiCliente() {
        return cuiCliente;
    }

    /**
     * Establece el CUI del cliente que realizó la compra.
     * @param cuiCliente El número CUI del cliente a asignar.
     */
    public void setCuiCliente(long cuiCliente) {
        this.cuiCliente = cuiCliente;
    }

    /**
     * Obtiene el ID del usuario que registró la venta.
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario que registró la venta.
     * @param idUsuario El identificador del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}