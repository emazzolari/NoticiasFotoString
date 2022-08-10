package com.egg.noticias.servicio;

import com.egg.noticias.entidades.Noticias;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.repositorio.NoticiasRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import net.iharder.Base64;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiasRepositorio noticiaRepositorio;

   
   

    @Transactional
    public void crearNoticia(MultipartFile imagen, String titulo, String cuerpo) throws Exception {
    	
    	Noticias noticia=new Noticias();
             if (!imagen.isEmpty()) {
            try {
                noticia.setImagen(Base64.encodeBytes(imagen.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setAlta(new Date());
            noticia.setActivo(true);

            noticiaRepositorio.save(noticia);
        }
    }

    @Transactional
    public void modificarNoticia(MultipartFile imagen, Integer id, String titulo, String cuerpo) throws Exception {

        validar(titulo, cuerpo);

        Optional<Noticias> respuesta = noticiaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Noticias noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setActivo(true);
            noticia.setImagen(Base64.encodeBytes(imagen.getBytes()));
            
            noticiaRepositorio.save(noticia);
        } else {
            throw new MiException("no se encontro el id");
        }
    }

    public List<Noticias> listarNoticias() {
        List<Noticias> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();
        return noticias;
    }

    @Transactional
    public void alta(Integer id) throws MiException {
        Optional<Noticias> respuesta = noticiaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Noticias noticias = respuesta.get();
            noticias.setActivo(true);
            noticiaRepositorio.save(noticias);
        } else {
            throw new MiException("no se encontro el id");
        }
    }

    @Transactional
    public void baja(Integer id) throws MiException {
        Optional<Noticias> respuesta = noticiaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Noticias noticia = respuesta.get();
            noticia.setActivo(false);
            noticiaRepositorio.save(noticia);
        } else {
            throw new MiException("no se encontro el id");
        }
    }

    public void eliminar(Integer id) throws Exception {
        Noticias entidad = noticiaRepositorio.getOne(id);
        noticiaRepositorio.delete(entidad);
    }

    @Transactional(readOnly = true)
    public List<Noticias> listarActivos() throws MiException {
        List<Noticias> lista = noticiaRepositorio.buscarActivos();
        return lista;
    }

    @Transactional(readOnly = true)
    public List<Noticias> listarTodos() throws MiException {
        List<Noticias> lista = noticiaRepositorio.buscarTodos();
        return lista;
    }

    public Noticias getOne(Integer id) {
        return noticiaRepositorio.getOne(id);
    }

    private void validar(String titulo, String cuerpo) throws MiException {

        if (titulo == null) {
            throw new MiException("El titulo no puede ser nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo no puede ser nulo");
        }

    }

    @Transactional
    public Noticias obtenerNoticiaId(Integer id) {

        System.out.println("Estamos buscando la noticia");

        Noticias noticia = new Noticias();
        Optional<Noticias> respuestaNoticia = noticiaRepositorio.findById(id);
        if (respuestaNoticia.isPresent()) {
            noticia = respuestaNoticia.get();
        }
        System.out.println("Encontramos la noticia " + noticia);

        return noticia;
    }
}
