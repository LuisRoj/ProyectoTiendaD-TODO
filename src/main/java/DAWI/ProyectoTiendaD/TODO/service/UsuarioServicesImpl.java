package DAWI.ProyectoTiendaD.TODO.service;

import DAWI.ProyectoTiendaD.TODO.model.bd.Usuario;
import DAWI.ProyectoTiendaD.TODO.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicesImpl implements IUsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
}
