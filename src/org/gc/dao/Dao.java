
package org.gc.dao;
 
/**
* Contrato DAO genérico que actúa como interfaz base centralizada para la
* gestión de persistencia de datos en el sistema, extendiendo las operaciones
* estándar definidas en {@link Crud}.
* @param <T> El tipo de entidad o modelo de dominio que maneja el DAO.
* @param <K> El tipo de dato correspondiente a la clave primaria (ID) de la entidad.
* @author Gabriel Chiu
* @version 1.0.0
* @see org.gc.dao.Crud
*/

public interface Dao<T, K> extends crud<T, K> {
 
}
 