package DAWI.ProyectoTiendaD.TODO.repository;

import DAWI.ProyectoTiendaD.TODO.model.bd.Pedido;
import DAWI.ProyectoTiendaD.TODO.model.bd.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuario(Usuario usuario);
}
