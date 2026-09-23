package org.gc.model;

/**
 * Representa la entidad de relación entre un autor y un libro en el sistema.
 * @author informatica
 * @version 1.0.0
 */
public class AutorLibro {

    /**
     * Identificador único de la relación entre autor y libro.
     */
    private int idAutorLibro;

    /**
     * Identificador único del autor.
     */
    private int idAutor;

    /**
     * Código ISBN del libro.
     */
    private String isbn;

    /**
     * Constructor por defecto de la clase AutorLibro.
     */
    public AutorLibro() {
    }

    /**
     * Constructor con todos los parámetros para inicializar la entidad AutorLibro.
     * @param idAutorLibro Identificador único de la relación.
     * @param idAutor Identificador único del autor.
     * @param isbn Código ISBN del libro.
     */
    public AutorLibro(int idAutorLibro, int idAutor, String isbn) {
        this.idAutorLibro = idAutorLibro;
        this.idAutor = idAutor;
        this.isbn = isbn;
    }

    /**
     * Obtiene el identificador único de la relación autor-libro.
     * @return El ID de la relación autor-libro.
     */
    public int getIdAutorLibro() {
        return idAutorLibro;
    }

    /**
     * Establece el identificador único de la relación autor-libro.
     * @param idAutorLibro El ID de la relación a asignar.
     */
    public void setIdAutorLibro(int idAutorLibro) {
        this.idAutorLibro = idAutorLibro;
    }

    /**
     * Obtiene el identificador del autor.
     * @return El ID del autor.
     */
    public int getIdAutor() {
        return idAutor;
    }

    /**
     * Establece el identificador del autor.
     * @param idAutor El ID del autor a asignar.
     */
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    /**
     * Obtiene el código ISBN del libro.
     * @return El código ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el código ISBN del libro.
     * @param isbn El código ISBN a asignar.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}