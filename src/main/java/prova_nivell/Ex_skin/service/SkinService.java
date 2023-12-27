package prova_nivell.Ex_skin.service;

import prova_nivell.Ex_skin.entity.Skin;
import prova_nivell.Ex_skin.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface SkinService {

    Skin guardarSkin(Skin skin);
    List<Skin> listadoSkinsDispponibles(String fichero);
    List<Skin> listadoSkinsUsuarioId(Long usuario_id);
   // List<Skin> comprarSkin(Skin skin, Long usuario_id);
    Skin actualizarSkin(Skin skin);
    void borrarSkin(Usuario usuario);

    Optional<Skin> buscarSkin(Long skin_id);
}
