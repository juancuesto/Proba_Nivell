package prova_nivell.Ex_skin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prova_nivell.Ex_skin.entity.Skin;
import prova_nivell.Ex_skin.entity.Usuario;
import prova_nivell.Ex_skin.repository.SkinRepository;
import prova_nivell.Ex_skin.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SkinServiceImpl implements SkinService{

    @Autowired
    private SkinRepository skinRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Skin guardarSkin(Skin skin) {
        return skinRepository.save(skin);
    }

    @Override
    public List<Skin> listadoSkinsDispponibles(String fichero) {

        return null;
    }

    @Override
    public List<Skin> listadoSkinsUsuarioId(Long usuario_id) {
        Optional<Usuario> usuarioOptional=usuarioRepository.findById(usuario_id);
        return usuarioOptional.get().getSkins();
    }

//    @Override
//    public List<Skin> comprarSkin(Skin skin, Long usuario_id) {
//        Optional<Usuario> usuarioOptional=usuarioRepository.findById(usuario_id);
//        if(usuarioOptional.isEmpty()){
//            return null;
//        }
//        return usuarioOptional.get().afegirSkin(skin);
//    }

    @Override
    public Skin actualizarSkin(Skin skin) {
        return skinRepository.save(skin);
    }

    @Override
    public void borrarSkin(Usuario usuario) {
        usuarioRepository.save(usuario);


    }

    @Override
    public Optional<Skin> buscarSkin(Long skin_id) {
        return skinRepository.findById(skin_id);
    }
}
