package prova_nivell.Ex_skin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prova_nivell.Ex_skin.entity.Skin;
import prova_nivell.Ex_skin.entity.Usuario;
import prova_nivell.Ex_skin.service.SkinService;
import prova_nivell.Ex_skin.service.UsuarioService;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skins")
public class SkinController {

    @Autowired
    private SkinService skinService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/buy/{usuario_id}")
    public ResponseEntity<?> comrprarSkin(@PathVariable("usuario_id") Long id,@RequestBody Skin skin){
        try{
            Optional<Usuario> usuarioOptional=usuarioService.buscarUsuario(id);
            if(usuarioOptional.isEmpty()){
                return new ResponseEntity<>("Error al buscar el usuario",HttpStatus.NOT_FOUND);
            }
            if(usuarioOptional.get().getSaldo()>=skin.getPrecio()){
                double saldoInicial=usuarioOptional.get().getSaldo();
                double saldoFinal=saldoInicial-skin.getPrecio();
                usuarioOptional.get().setSaldo(saldoFinal);
                usuarioOptional.get().afegirSkin(skin);

                return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioOptional.get()),HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No tienes saldo suficiente para comprar el skin",HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/myskins/{usuario_id}")
    public ResponseEntity<?> listadoSkinUsuario(@PathVariable("usuario_id") Long id){
        try {
            Optional<Usuario> usuarioOptional=usuarioService.buscarUsuario(id);
            if(usuarioOptional.isEmpty()){
                return new ResponseEntity<>("Error al buscar el usuario",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(skinService.listadoSkinsUsuarioId(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/color/{usuario_id}")
    public ResponseEntity<?> actualizarSkin(@RequestBody Skin skin,@PathVariable("usuario_id") Long id){
        try {
            Optional<Usuario> usuarioOptional=usuarioService.buscarUsuario(id);
            if(usuarioOptional.isEmpty()){
                return new ResponseEntity<>("Error al buscar el usuario",HttpStatus.NOT_FOUND);
            }
            for (Skin ele:usuarioOptional.get().getSkins()){
                if(ele.getId()==skin.getId()){
                    ele.setColor(skin.getColor());
                    return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioOptional.get()),HttpStatus.OK);
                }
            }

            return new ResponseEntity<>("No se ha encontrado el skin",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{skin_id}/{usuario_id}")
    public ResponseEntity<String> borrarSkingById(@PathVariable("skin_id") Long id,@PathVariable Long usuario_id){
        try{
            Optional<Usuario> usuarioOptional=usuarioService.buscarUsuario(usuario_id);
            if(usuarioOptional.isEmpty()){
                return new ResponseEntity<>("Error al buscar el usuario",HttpStatus.NOT_FOUND);
            }

                for(Skin ele:usuarioOptional.get().getSkins()) {
                    if (ele.getId() == id) {
                        usuarioOptional.get().eliminarSkin(ele);
                        skinService.borrarSkin(usuarioOptional.get());
                        return new ResponseEntity<>("elemento borrardo correctamente", HttpStatus.OK);
                    }
                }
            return new ResponseEntity<>("No se ha encontrado el elmento a borrar",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getskin/{id}")
    public ResponseEntity<?> buscarSkinById(@PathVariable Long id) {
        try {
            Optional<Skin> skinOptional = skinService.buscarSkin(id);
            if (skinOptional.isEmpty()) {
                return new ResponseEntity<>("Error al buscar el skin", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(skinService.buscarSkin(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearSking(@RequestBody Skin skin){
        try {
            Skin skin1=skinService.guardarSkin(skin);
            if(skin1==null){
                return new ResponseEntity<>("error al crear el skin",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(skin1,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/avalaible")
    public ResponseEntity<?> mostrarSkinDisponibles(){
        try{
            //crear objeto mapper
            ObjectMapper miMapper=new ObjectMapper();
            //leer el JSON y convertirlo a POJO
            Skin obj=miMapper.readValue(new File("data/skins.json"),Skin.class);
            return new ResponseEntity<>(obj,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("error al leer el archivo JSON",HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("afegir/skin")
    public ResponseEntity<?> afegirSkin(@RequestBody Skin skin){
        Skin skin1=skinService.guardarSkin(skin);
        if(skin1==null){
            return new ResponseEntity<>("Error al crear el skin",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skin1,HttpStatus.CREATED);
    }
}
