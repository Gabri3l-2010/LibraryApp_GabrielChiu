package org.gc.dao;

import java.util.ArrayList;

/**
 * Contrato genérico que define las operaciones de persistencia CRUD
 * (Create, Read, Update, Delete) principales para las entidades del sistema.
 *
 * @param <T> Tipo de la entidad a gestionar.
 * @param <K> Tipo de dato de la clave primaria o identificador de la entidad.
 * 
 * @author Gabriel Chiu
 * @version 1.0.0
 */
public interface Crud<T, K> {

    /**
     * Registra una nueva entidad en la base de datos.
     *
     * @param entidad Objeto de tipo {@link T} que contiene los datos a registrar. No debe ser null.
     * @return {@code true} si el registro fue exitoso; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el parámetro entidad contiene datos inválidos o vacíos.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    boolean crear(T entidad);

    /**
     * Actualiza la información existente de una entidad en la base de datos.
     *
     * @param entidad Objeto de tipo {@link T} con la información modificada. No debe ser null.
     * @return {@code true} si la actualización fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el parámetro entidad contiene datos inválidos.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    boolean actualizar(T entidad);

    /**
     * Elimina un registro de la base de datos según su identificador único.
     *
     * @param id Clave primaria de tipo {@link K} que identifica el registro a eliminar.
     * @return {@code true} si la eliminación fue exitosa; {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el id ingresado es inválido o nulo.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    boolean eliminar(K id);

    /**
     * Busca y recupera un registro específico mediante su identificador.
     *
     * @param id Clave primaria de tipo {@link K} del registro a buscar.
     * @return La entidad encontrada de tipo {@link T}, o {@code null} si no existe coincidencia.
     * @throws IllegalArgumentException Si el id ingresado es inválido o nulo.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     */
    T buscarPorId(K id);

    /**
     * Recupera el listado completo de registros almacenados.
     *
     * @return Una lista de tipo {@link ArrayList} que contiene los objetos {@link T}. 
     *         Retorna una lista vacía si no existen registros en la base de datos.
     * @throws RuntimeException Si ocurre un error de conexión con JDBC o la base de datos.
     * @see java.util.ArrayList
     */
    ArrayList<T> listarTodos();
}