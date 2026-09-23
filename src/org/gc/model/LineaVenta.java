package org.gc.model;

/**
 * Representa una línea de venta en memoria.
 * Contiene un libro y su cantidad asociada, utilizada como fila temporal en la pantalla
 * de interfaz de usuario antes de persistir los registros como {@code DetalleVenta} en la base de datos.
 * @author Gabriel Chiu
 * @version 1.0.0
 */
public class LineaVenta {

    /** Objeto {@link Libro} asociado a esta línea de venta. */
    private Libro libro;
    /** Cantidad de unidades seleccionadas de este libro. */
    private int cantidad;

    /**
     * Constructor por defecto de la clase {@link LineaVenta}.
     */
    public LineaVenta() {
    }

    /**
     * Constructor con parámetros para inicializar la línea de venta.
     * @param libro Objeto {@link Libro} que se agregará a la venta.
     * @param cantidad Cantidad de ejemplares del libro.
     */
    public LineaVenta(Libro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el libro asociado a la línea de venta.
     * @return El objeto {@link Libro}.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro para la línea de venta.
     * @param libro Objeto {@link Libro} a asignar.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la cantidad de ejemplares.
     * @return La cantidad de unidades.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de ejemplares.
     * @param cantidad Cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el código ISBN del libro asociado a la línea de venta.
     * @return El código ISBN del libro.
     */
    public String getIsbn() {
        return libro.getIsbn();
    }

    /**
     * Obtiene el título del libro asociado a la línea de venta.
     * @return El título del libro.
     */
    public String getTitulo() {
        return libro.getTitulo();
    }

    /**
     * Obtiene el precio unitario del libro asociado a la línea de venta.
     * @return El precio del libro.
     */
    public double getPrecio() {
        return libro.getPrecio();
    }

    /**
     * Calcula y obtiene el subtotal de esta línea de venta.
     * @return El subtotal calculado multiplicando el precio del libro por la cantidad.
     */
    public double getSubtotal() {
        return libro.getPrecio() * cantidad;
    }
}