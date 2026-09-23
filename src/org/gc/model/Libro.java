package org.gc.model;

/**
 * Clase que representa la entidad Libro dentro del sistema.
 * Contiene los datos descriptivos, comerciales y de inventario de un libro.
 * @author informatica
 */
public class Libro {

    /** Código ISBN único que identifica al libro. */
    private String isbn;
    /** Título del libro. */
    private String titulo;
    /** Fecha de publicación del libro. */
    private String fechaPublicacion;
    /** Precio de venta del libro. */
    private double precio;
    /** Identificador de la categoría a la que pertenece el libro. */
    private int idCategoria;
    /** NIT de la editorial que publica el libro. */
    private String nitEditorial;
    /** Cantidad de ejemplares disponibles en inventario. */
    private int stock;

    /**
     * Constructor por defecto de la clase {@link Libro}.
     */
    public Libro() {
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos del libro.
     * @param isbn Código ISBN del libro.
     * @param titulo Título del libro.
     * @param fechaPublicacion Fecha de publicación del libro.
     * @param precio Precio de venta.
     * @param idCategoria Identificador de la categoría asociadas.
     * @param nitEditorial NIT de la editorial correspondiente.
     * @param stock Cantidad inicial en inventario.
     */
    public Libro(String isbn, String titulo, String fechaPublicacion, double precio, int idCategoria, String nitEditorial, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
        this.idCategoria = idCategoria;
        this.nitEditorial = nitEditorial;
        this.stock = stock;
    }

    /**
     * Obtiene el código ISBN del libro.
     * @return El código ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el código ISBN del libro.
     * @param isbn Código ISBN a asignar.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     * @return El título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     * @param titulo Título a asignar.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la fecha de publicación del libro.
     * @return La fecha de publicación.
     */
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Establece la fecha de publicación del libro.
     * @param fechaPublicacion Fecha de publicación a asignar.
     */
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * Obtiene el precio del libro.
     * @return El precio del libro.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del libro.
     * @param precio Precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el ID de la categoría del libro.
     * @return El ID de la categoría.
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * Establece el ID de la categoría del libro.
     * @param idCategoria ID de la categoría a asignar.
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Obtiene el NIT de la editorial asociada.
     * @return El NIT de la editorial.
     */
    public String getNitEditorial() {
        return nitEditorial;
    }

    /**
     * Establece el NIT de la editorial asociada.
     * @param nitEditorial NIT a asignar.
     */
    public void setNitEditorial(String nitEditorial) {
        this.nitEditorial = nitEditorial;
    }

    /**
     * Obtiene la cantidad de stock disponible del libro.
     * @return La cantidad en stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad en stock del libro.
     * @param stock Cantidad a asignar.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retorna una representación en texto del libro.
     * @return El título del libro.
     */
    @Override
    public String toString() {
        return titulo;
    }
}