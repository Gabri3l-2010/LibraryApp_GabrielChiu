package org.gc.dao;

/**
 * Contrato genérico DAO que extiende la interfaz {@link Crud}.
 * <p>
 * Sirve como base para definir las operaciones de persistencia de datos
 * sobre las entidades del sistema.
 * </p>
 *
 * @param <T> Tipo de la entidad a gestionar.
 * @param <K> Tipo de dato de la clave primaria o identificador de la entidad.
 * 
 * @author Gabriel Cjiu 
 * @version 1.0.0
 * @see org.gc.dao.Crud
 */
public interface Dao<T, K> extends Crud<T, K> {

}