package org.gc.model;

/**
 * Proyección de solo lectura para la representación de una línea de factura.
 * Mapea una fila del resultado del procedimiento almacenado {@code sp_buscar_factura}
 * uniendo la información de venta, cliente, libro y usuario.
 * @author Nombre del Estudiante
 * @version 1.0.0
 */
public class LineaFactura {

    /** Número correlativo de la factura. */
    private int numeroFactura;
    /** Fecha de emisión de la factura. */
    private String fechaEmision;
    /** CUI del cliente que realizó la compra. */
    private long cuiCliente;

    /** Nombre completo del cliente. */
    private String nombreCliente;
    /** Correo electrónico de contacto del cliente. */
    private String correoCliente;
    /** Código ISBN del libro vendido. */
    private String isbnLibro;
    /** Título del libro vendido. */
    private String tituloLibro;
    /** Cantidad de unidades vendidas. */
    private int cantidad;
    /** Precio unitario del producto al momento de la venta. */
    private double precioUnitario;
    /** Subtotal del ítem (cantidad * precioUnitario). */
    private double subtotal;
    /** Identificador o nombre del usuario que procesó la venta. */
    private String usuarioAtendio;
    /** Gran total acumulado de la factura. */
    private double granTotal;

    /**
     * Constructor por defecto de la clase {@link LineaFactura}.
     */
    public LineaFactura() {
    }

    /**
     * Obtiene el número de factura.
     * @return El número de la factura.
     */
    public int getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Establece el número de factura.
     * @param numeroFactura Número de factura a asignar.
     */
    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * Obtiene la fecha de emisión de la factura.
     * @return La fecha de emisión.
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha de emisión de la factura.
     * @param fechaEmision Fecha a asignar.
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el CUI del cliente.
     * @return El CUI del cliente.
     */
    public long getCuiCliente() {
        return cuiCliente;
    }

    /**
     * Establece el CUI del cliente.
     * @param cuiCliente CUI a asignar.
     */
    public void setCuiCliente(long cuiCliente) {
        this.cuiCliente = cuiCliente;
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
     * @param nombreCliente Nombre a asignar.
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return El correo electrónico del cliente.
     */
    public String getCorreoCliente() {
        return correoCliente;
    }

    /**
     * Establece el correo electrónico del cliente.
     * @param correoCliente Correo electrónico a asignar.
     */
    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    /**
     * Obtiene el código ISBN del libro.
     * @return El ISBN del libro.
     */
    public String getIsbnLibro() {
        return isbnLibro;
    }

    /**
     * Establece el código ISBN del libro.
     * @param isbnLibro Código ISBN a asignar.
     */
    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    /**
     * Obtiene el título del libro.
     * @return El título del libro.
     */
    public String getTituloLibro() {
        return tituloLibro;
    }

    /**
     * Establece el título del libro.
     * @param tituloLibro Título a asignar.
     */
    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    /**
     * Obtiene la cantidad de unidades vendidas de esta línea.
     * @return La cantidad de unidades.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades vendidas.
     * @param cantidad Cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto.
     * @return El precio unitario.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto.
     * @param precioUnitario Precio unitario a asignar.
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el subtotal calculado para este ítem.
     * @return El subtotal de la línea.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal de este ítem.
     * @param subtotal Subtotal a asignar.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el usuario que atendió la venta.
     * @return El identificador/nombre del usuario.
     */
    public String getUsuarioAtendio() {
        return usuarioAtendio;
    }

    /**
     * Establece el usuario que atendió la venta.
     * @param usuarioAtendio Usuario a asignar.
     */
    public void setUsuarioAtendio(String usuarioAtendio) {
        this.usuarioAtendio = usuarioAtendio;
    }

    /**
     * Obtiene el gran total de la factura.
     * @return El monto del gran total.
     */
    public double getGranTotal() {
        return granTotal;
    }

    /**
     * Establece el gran total de la factura.
     * @param granTotal Gran total a asignar.
     */
    public void setGranTotal(double granTotal) {
        this.granTotal = granTotal;
    }
}