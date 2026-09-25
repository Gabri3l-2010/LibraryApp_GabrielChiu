
package org.gc.dao;

import org.gc.model.Autor;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link Autor} dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} definiendo el identificador como tipo {@link Integer}.
 * </p>
 * 
 * @author Gabriel Chiu 
 * @version 1.0.0
 * @see org.gc.model.Autor
 * @see org.gc.dao.Crud
 */
public interface AutorDAO extends Crud<Autor, Integer> {
    
}