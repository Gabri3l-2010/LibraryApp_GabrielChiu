package org.gc.dao;

import org.gc.model.Libro;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link Libro} dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} utilizando un identificador de tipo {@link String} (ISBN/código).
 * </p>
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Libro
 * @see org.gc.dao.Crud
 */
public interface LibroDAO extends Crud<Libro, String> {

}