package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.Pedido;
import DAWI.ProyectoTiendaD.TODO.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public String generarNumeroPedido() {
        int numero=0;
        String numeroConcatenado="";

        List<Pedido> pedidos = findAll();

        List<Integer> numeros= new ArrayList<Integer>();

        pedidos.stream().forEach(o -> numeros.add( Integer.parseInt( o.getNumero())));

        if (pedidos.isEmpty()) {
            numero=1;
        }else {
            numero= numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero<10) { //0000001000
            numeroConcatenado="000000"+String.valueOf(numero);
        }else if(numero<100) {
            numeroConcatenado="00000"+String.valueOf(numero);
        }else if(numero<1000) {
            numeroConcatenado="0000"+String.valueOf(numero);
        }else if(numero<10000) {
            numeroConcatenado="000"+String.valueOf(numero);
        }

        return numeroConcatenado;
    }


}
