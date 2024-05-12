package DAWI.ProyectoTiendaD.TODO.repository;

import DAWI.ProyectoTiendaD.TODO.model.bd.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
    Usuario findByNomusuario(String nomusuario);
    Optional<Usuario> findById(Integer id);
}
