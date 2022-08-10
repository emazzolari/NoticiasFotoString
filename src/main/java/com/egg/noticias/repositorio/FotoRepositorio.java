//
//package com.egg.noticias.repositorio;
//
//import com.egg.noticias.entidades.Foto;
//import com.egg.noticias.entidades.Noticias;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface FotoRepositorio extends JpaRepository<Foto, Integer>{
//     @Query("SELECT N FROM Foto N WHERE N.id = :id")
//    public Foto buscarFoto(@Param("id") Integer id);
//}
