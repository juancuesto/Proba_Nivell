package prova_nivell.Ex_skin.service;

import org.springframework.stereotype.Service;
import prova_nivell.Ex_skin.entity.Skin;
import prova_nivell.Ex_skin.entity.Usuario;

import java.util.Optional;


public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> buscarUsuario(Long id);
    Usuario actualizarUsuario(Usuario usuario);


}
