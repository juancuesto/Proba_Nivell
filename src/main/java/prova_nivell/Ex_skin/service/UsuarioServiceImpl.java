package prova_nivell.Ex_skin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prova_nivell.Ex_skin.entity.Usuario;
import prova_nivell.Ex_skin.repository.SkinRepository;
import prova_nivell.Ex_skin.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SkinRepository skinRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional=usuarioRepository.findById(usuario.getId());

        usuarioOptional.get().setNombre(usuario.getNombre());
        usuarioOptional.get().setApellido(usuario.getApellido());
        usuarioOptional.get().setEdad(usuario.getEdad());
        usuarioOptional.get().setSaldo(usuario.getSaldo());
        usuarioOptional.get().setSkins(usuario.getSkins());
        usuarioRepository.save(usuarioOptional.get());
        return null;
    }
}
