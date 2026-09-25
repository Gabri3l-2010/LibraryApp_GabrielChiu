package org.gc.dao;

import java.util.List;
import org.gc.model.LineaVenta;
import org.gc.model.Venta;

public interface VentaDAO extends Crud<Venta, Integer>{
    //crearVenta inserta el encabezado de la venta, sus líneas y descuenta el stock.
    //Devuelve el no_venta generado (o -1 si falla).
    int crearVenta(Venta venta, List<LineaVenta> lineas);
}

