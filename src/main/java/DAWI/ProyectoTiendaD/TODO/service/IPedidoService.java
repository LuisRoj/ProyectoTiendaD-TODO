package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.Pedido;
import DAWI.ProyectoTiendaD.TODO.model.bd.Usuario;

import java.util.List;

public interface IPedidoService {
    List<Pedido> findAll();
    Pedido save(Pedido pedido);
    String generarNumeroPedido();
    List<Pedido> findByUsuario(Usuario usuario);
}
