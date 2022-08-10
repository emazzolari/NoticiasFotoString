package com.egg.noticias.repositorio;

import com.egg.noticias.entidades.Noticias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiasRepositorio extends JpaRepository<Noticias, Integer> {

    @Query("SELECT N FROM Noticias N WHERE N.id = :id")
    public Noticias buscarNoticias(@Param("id") Integer id);

    @Query("SELECT a from Noticias a WHERE a.activo = true ORDER BY a.alta DESC")
    public List<Noticias> buscarActivos();

    @Query("SELECT a from Noticias a WHERE a.activo = true and a.activo = false")
    public List<Noticias> buscarTodos();
}
