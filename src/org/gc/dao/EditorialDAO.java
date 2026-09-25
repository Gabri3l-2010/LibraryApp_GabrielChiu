package org.gc.dao;

import org.gc.model.Editorial;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link Editorial} dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} utilizando un identificador de tipo {@link String}.
 * </p>
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Editorial
 * @see org.gc.dao.Crud
 */
public interface EditorialDAO extends Crud<Editorial, String> {

}