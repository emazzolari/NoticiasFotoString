package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticias;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.servicio.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/crear")
    public String crear() {
        return "crearNoticia.html";
    }

    @PostMapping("/crearNoti")
    public String guardar(@RequestParam ("archivo") MultipartFile imagen, ModelMap modelo, @RequestParam String titulo, @RequestParam String cuerpo) throws Exception{

        try {
            noticiaServicio.crearNoticia(imagen, titulo, cuerpo);
            modelo.put("exito", "La noticia fue cargada correctamente");

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "crearNoticia";
        }
        return "crearNoticia";
    }

    @GetMapping("/admin")
    public String listar(ModelMap modelo) {

        try {
            List<Noticias> listNoticias = noticiaServicio.listarActivos();
            modelo.addAttribute("listNoticias", listNoticias);
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
        return "admin.html";
    }

    @GetMapping("/editar")
    public String editar() {
        return "admin.html";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "editar.html";
    }

    @PostMapping("/editar/{id}")
    public String editar(@RequestParam ("archivo") MultipartFile imagen, @RequestParam Integer id, @RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) throws Exception {
        try {
            noticiaServicio.modificarNoticia(imagen, id, titulo, cuerpo);

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "index.html";
        }
        return "redirect:/";
    }

    @GetMapping("/baja/{id}")
    public String baja(@PathVariable Integer id, ModelMap modelo) throws MiException {
        noticiaServicio.baja(id);
       
        return "redirect:/noticia/admin";
    }
    
   @GetMapping("/mostrar/{id}")
   public String mostar(@PathVariable Integer id, ModelMap modelo) {
       modelo.put("noticia", noticiaServicio.getOne(id));
       return "cuerpo.html";
   }
}
