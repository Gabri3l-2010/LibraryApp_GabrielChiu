package org.gc.dao;

import java.util.ArrayList;

public interface crud<T, K> {
    boolean crear(T entidad);
    boolean actualizar(T entidad);
    boolean eliminar(K id);
    T buscarPorId(K id);
    ArrayList<T> listarTodos();
}