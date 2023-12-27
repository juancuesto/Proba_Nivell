package prova_nivell.Ex_skin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prova_nivell.Ex_skin.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
