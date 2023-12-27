package prova_nivell.Ex_skin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prova_nivell.Ex_skin.entity.Usuario;
import prova_nivell.Ex_skin.service.UsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){

        return new ResponseEntity<>(usuarioService.crearUsuario(usuario),HttpStatus.CREATED);
//        try{
//            Usuario usuario1=usuarioService.crearUsuario(usuario);
//            if(usuario1==null){
//                return new ResponseEntity<>("error al crear el usuario", HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(usuario1,HttpStatus.CREATED);
//        }catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/buscar/{id}")
    public  ResponseEntity<?> buscarUsuarioById(@PathVariable Long id){
        try {
            Optional<Usuario> usuarioOptional=usuarioService.buscarUsuario(id);
            if(usuarioOptional.isEmpty()){
                return new ResponseEntity<>("No se ha encontrado el usuario",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(usuarioOptional,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
