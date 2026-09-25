package org.gc.dao;

import org.gc.model.AutorLibro;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link AutorLibro} dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} utilizando un identificador de tipo {@link Integer}.
 * </p>
 * 
 * @author Gabriel Chiu 
 * @version 1.0.0
 * @see org.gc.model.AutorLibro
 * @see org.gc.dao.Crud
 */
public interface AutorLibroDAO extends Crud<AutorLibro, Integer> {
    
}