package org.gc.model;

/**
 * Clase que representa el detalle de una venta dentro del sistema.
 * Contiene la información específica de un producto o ítem asociado a una venta.
 * @author Nombre del Estudiante
 * @version 1.0.0
 */
public class DetalleVenta {

    /** Identificador único del detalle de venta. */
    private int idDetalleVenta;
    /** Número de la venta a la cual pertenece este detalle. */
    private int noVenta;
    /** Código ISBN del libro o producto asociado. */
    private String isbn;
    /** Cantidad de unidades vendidas. */
    private int cantidad;
    /** Precio unitario del producto. */
    private double precio;
    /**
     * Constructor por defecto de la clase {@link DetalleVenta}.
     */
    public DetalleVenta() {
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos del detalle de venta.     * @param idDetalleVenta Identificador único del detalle de venta.
      @param noVenta Número de la venta asociada.
     * @param isbn Código ISBN del libro.
     * @param cantidad Cantidad de unidades.
     * @param precio Precio unitario.
     */
    public DetalleVenta(int idDetalleVenta, int noVenta, String isbn, int cantidad, double precio) {
        this.idDetalleVenta = idDetalleVenta;
        this.noVenta = noVenta;
        this.isbn = isbn;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Obtiene el ID del detalle de venta.
     * @return El ID del detalle de venta.
     */
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    /**
     * Establece el ID del detalle de venta.
     * @param idDetalleVenta ID a asignar al detalle de venta.
     */
    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    /**
     * tiene el número de venta asociado.
     * @return El número de venta.
     */
    public int getNoVenta() {
        return noVenta;
    }

    /**
     * Establece el número de venta asociado.
     * @param noVenta Número de venta a asignar.
     */
    public void setNoVenta(int noVenta) {
        this.noVenta = noVenta;
    }

    /**
     * Obtiene el ISBN del producto asociado.
     * @return El código ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del producto asociado.
     * @param isbn Código ISBN a asignar.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene la cantidad de productos en el detalle.
     * @return La cantidad de unidades.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos en el detalle. 
     * @param cantidad Cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del producto.
     * @param precio Precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}