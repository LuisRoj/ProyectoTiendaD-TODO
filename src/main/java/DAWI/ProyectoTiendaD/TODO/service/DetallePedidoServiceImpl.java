package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.DetallePedido;
import DAWI.ProyectoTiendaD.TODO.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService{
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
}
