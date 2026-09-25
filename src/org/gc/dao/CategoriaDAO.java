package org.gc.dao;

import org.gc.model.Categoria;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link Categoria} dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} utilizando un identificador de tipo {@link Integer}.
 * </p>
 * 
 * @author Gabriel Chiu 
 * @version 1.0.0
 * @see org.gc.model.Categoria
 * @see org.gc.dao.Crud
 */
public interface CategoriaDAO extends Crud<Categoria, Integer> {
    
}