package DAWI.ProyectoTiendaD.TODO.repository;

import DAWI.ProyectoTiendaD.TODO.model.bd.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Integer> {
}
