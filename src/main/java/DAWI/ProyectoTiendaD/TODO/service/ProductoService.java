package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.Producto;
import DAWI.ProyectoTiendaD.TODO.repository.ProductosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoService implements IProductoService{
    private ProductosRepository ProductosRepository;
    @Override
    public List<Producto> listarProductos() {
        return ProductosRepository.findAll();
    }

}
