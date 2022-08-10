//package com.egg.noticias.controladores;
//
//import com.egg.noticias.entidades.Noticias;
//import com.egg.noticias.servicio.NoticiaServicio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/foto")
//public class FotoControlador {
//
//    @Autowired
//    private NoticiaServicio noticiaServicio;
//
//    @GetMapping("/noticia")
//    public ResponseEntity<byte[]> fotoNoticia(@RequestParam Integer id) throws Exception {
//        Noticias noticia = noticiaServicio.obtenerNoticiaId(id);
//        
//        System.out.println("Obtubimos la noticia " + noticia.getFoto1());
//        try {
//            if (noticia.getFoto1() != null) {
//                throw new Exception();
//            }
//                byte[] foto = noticia.getFoto1().getContenido();
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.IMAGE_JPEG);
//                return new ResponseEntity<>(foto, headers, HttpStatus.OK);
//            
//        } catch (Exception ex) {
//            System.out.println("La noticia no tiene foto");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}

