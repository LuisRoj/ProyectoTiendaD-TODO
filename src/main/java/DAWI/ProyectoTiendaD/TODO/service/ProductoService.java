package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.Producto;
import DAWI.ProyectoTiendaD.TODO.repository.ProductosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductoService implements IProductoService{
    @Autowired
    private ProductosRepository ProductosRepository;

    @Override
    public List<Producto> listarProductos() {
        return ProductosRepository.findAll();
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return ProductosRepository.findById(id);
    }

    @Override
    public Producto FindById(Integer id) {
        return ProductosRepository.findById(id).get();
    }
}
