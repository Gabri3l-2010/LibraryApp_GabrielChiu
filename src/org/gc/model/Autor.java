package org.gc.model;
/**
 * Representa la entidad Autor dentro del sistema LibraryApp
 * @author informatica
 */
public class Autor {
    /** Identificador único del autor
    private int idAutor;
    /** Nombre del auto */
    private String nombreAutor;
    /** Apellido del auto */
    private String apellidoAutor;
    /** Nacionalidad de origen del autor */
    private String nacionalidad;
    /** Breve reseña biográfica del autor */
    private String biografia;
    /**
     * Constructor por defecto
     */
    public Autor() {
    }
    /**
     * Constructor con todos los campos de la entidad Autor
     * @param idAutor Identificador único el autor
     * @param nombreAutor Nombre del autor
     * @param apellidoAutor Apellido del autor
     * @param nacionalidad País de origen del autor
     * @param biografia Reseña biográfica del autor
     */
    public Autor(int idAutor, String nombreAutor, String apellidoAutor, String nacionalidad, String biografia) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidad = nacionalidad;
        this.biografia = biografia;
    }
    /**
     * Obtiene el identificador del autor
     * @return El ID del autor
     */
    public int getIdAutor() {
        return idAutor;
    }
    /**
     * Establece el identificador del autor
     * @param idAutor El nuevo ID a asignar
     */
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
    /**
     * Obtiene el nombre del autor
     * @return El nombre del autor
     */
    public String getNombreAutor() {
        return nombreAutor;
    }
    /**
     * Establece el nombre del autor
     * @param nombreAutor El nombre a asignar
     */
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    /**
     * Obtiene el apellido del autor
     * @return El apellido del autor
     */
    public String getApellidoAutor() {
        return apellidoAutor;
    }

    /**
     * Establece el apellido del autor
     * @param apellidoAutor El apellido a asignar
     */
    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }
    /**
     * Obtiene la nacionalidad del autor
     * @return La nacionalidad del autor     */
    public String getNacionalidad() {
        return nacionalidad;
    }
    /*
     * Establece la nacionalidad del autor
     * @param nacionalidad La nacionalidad a asignar
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    /**
     * Obtiene la biografía del autor
     * @return La biografía del autor
     */
    public String getBiografia() {
        return biografia;
    }
    /**
     * Establece la biografía del autor
     * @param biografia La biografía a asign     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    /**
     * Devuelve una representación en texto del autor con su nombre y apellido
     * @return Una cadena con el nombre y apellido concatenados
     */
    @Override
    public String toString() {
        return nombreAutor + " " + apellidoAutor;
    }
}