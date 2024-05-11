package DAWI.ProyectoTiendaD.TODO.repository;

import DAWI.ProyectoTiendaD.TODO.model.bd.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
