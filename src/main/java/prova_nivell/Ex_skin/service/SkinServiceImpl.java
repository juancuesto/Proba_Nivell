package prova_nivell.Ex_skin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prova_nivell.Ex_skin.entity.Skin;
import prova_nivell.Ex_skin.entity.Usuario;
import prova_nivell.Ex_skin.repository.SkinRepository;
import prova_nivell.Ex_skin.repository.UsuarioRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
//    public List<Skin> listadoSkinsDispponibles() {
//        try {
//            FileReader entrada = new FileReader("C:\\Users\\juanc\\Desktop\\Cursos\\eclipse\\eclipse2023_WorkSpace\\Ex_skin\\src\\main\\resources\\data\\skins.json");
//
//            BufferedReader mibuffer = new BufferedReader(entrada);
//
//            List<Skin> listadoSkins=new ArrayList<>();
//            String linea = "";
//            Skin miSkin=new Skin();
//            while (linea != null) {
//                linea = mibuffer.readLine();
//                System.out.println(linea);
               // boolean a=true;

//                while(a){
//
//                    //miSkin.setId(1L);
//                    //linea = mibuffer.readLine();
//                    System.out.println("Imprime la linea ------------"+linea);
//
//                    if(linea.contains(":")){
//                        String[] parts=linea.split(":");
//                        String p1=parts[0];
//                        String p2=parts[1];
//                        // System.out.println("imprime el id del skin: "+miSkin.getId());
//
//                        if(p1.contains("id")){
//                            Long b=Long.parseLong(p2);
//                            miSkin.setId(b);
//                            System.out.println("el id es : "+miSkin.getId());
//                            a=false;
//                        }else if (p1.contains("nombre")) {
//                            miSkin.setNombre(p2);
//                            a=false;
//                        } else if (p1.contains("tipo")) {
//                            miSkin.setTipo(p2);
//                            a=false;
//                        } else if (p1.contains("color")) {
//                            miSkin.setColor(p2);
//                            a=false;
//                        } else if (p1.contains("precio")) {
//                            double pr=Double.parseDouble(p2);
//                            miSkin.setPrecio(pr);
//                            listadoSkins.add(miSkin);
//                            a=false;
//                        }
//                    }else {
//                        a=false;
//                    }
//                    if (linea != null) {
//                        System.out.println(linea);
//                        System.out.println(miSkin);
//                        a=false;
//                    }
//                    }
//
//                  }
//            entrada.close();
//            return  listadoSkins;
//        } catch (IOException e) {
//            System.out.println("No se ha encontrado el archivo");
//        }
//        return null;
//    }

public List<Skin> listadoSkinsDispponibles() {
        try {
        FileReader entrada = new FileReader("C:\\Users\\juanc\\Desktop\\Cursos\\eclipse\\eclipse2023_WorkSpace\\Ex_skin\\src\\main\\resources\\data\\skins.json");

        BufferedReader mibuffer = new BufferedReader(entrada);

        List<Skin> listadoSkins=new ArrayList<>();
        String linea = "";
        Skin miSkin=new Skin();
        while (linea != null) {
        linea = mibuffer.readLine();
        System.out.println(linea);
//        if(linea.contains("")){

//            String[] parts=linea.split(":");
//                        String p1=parts[0];
//                        String p2=parts[1];
//            System.out.println("la parte 1 es:------------------"+p1);
//            System.out.println("la parte 2 es:------------------"+p2);
       }

            entrada.close();
            return  listadoSkins;
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        return null;
    }





    @Override
    public List<Skin> listadoSkinsUsuarioId(Long usuario_id) {
        Optional<Usuario> usuarioOptional=usuarioRepository.findById(usuario_id);
        return usuarioOptional.get().getSkins();
    }

    @Override
    public boolean comprarSkin(Skin skin, Long usuario_id) {
        List<Usuario> listadoUsuarios=usuarioRepository.findAll();
        boolean a=true;
        for (Usuario ele: listadoUsuarios
             ) {
            for (Skin e: ele.getSkins()
                 ) {
                if (e.getId() == skin.getId()) {
                    a=false;
                }
        }
        }
        return a;

    }

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
