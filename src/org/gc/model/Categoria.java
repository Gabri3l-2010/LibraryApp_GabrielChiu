package org.gc.model;

/**
 * Representa la entidad Categoria en el siste
 * @author informatica
 * @version 1.0.0
 */
public class Categoria {

    /**
     * Identificador único de la categoría.
     */
    private int idCategoria;

    /**
     * Nombre de la categoría.
     */
    private String nombreCategoria;

    /**
     * Constructor por defecto de la clase Categoria.
     */
    public Categoria() {
    }

    /**
     * Constructor con todos los parámetros para inicializar la categoría.
     * @param idCategoria Identificador único de la categoría.
     * @param nombreCategoria Nombre de la categoría.
     */
    public Categoria(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Obtiene e identificador de la categoría.
     * @return El ID de la categoría.
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * Establece el identificador de la categoría.
     * @param idCategoria El ID de la categoría a asignar.
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Obtiene el nombre de la categoría.
     * @return El nombre de la categoría.
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Establece el nombre de la categoría.
     * @param nombreCategoria El nombre de la categoría a asignar.
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Devuelve la representación en cadena de la categoría.
     * @return El nombre de la categoría.
     */
    @Override
    public String toString() {
        return nombreCategoria;
    }
}