package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarProductos();
    public List<Producto> findAll();
    Optional<Producto> get(Integer id);

    Producto FindById(Integer id);
    void registrarProducto(Producto producto);
    void eliminarProducto(Integer id);
}
