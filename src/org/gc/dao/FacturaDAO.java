package org.gc.dao;

import java.util.ArrayList;
import org.gc.model.LineaFactura;

public interface FacturaDAO {
    ArrayList<LineaFactura> buscarFactura(int noVenta);
}

