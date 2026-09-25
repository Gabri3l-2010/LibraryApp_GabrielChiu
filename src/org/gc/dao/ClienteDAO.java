package org.gc.dao;

import org.gc.model.Cliente;

/**
 * Contrato DAO que define las operaciones de persistencia específicas
 * para la entidad {@link Cliente} dentro del sistema.
 * <p>
 * Extiende la interfaz genérica {@link Crud} utilizando un identificador de tipo {@link Long}.
 * </p>
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 * @see org.gc.model.Cliente
 * @see org.gc.dao.Crud
 */
public interface ClienteDAO extends Crud<Cliente, Long> {
    
}