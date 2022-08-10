package com.egg.noticias.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.egg.noticias.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	
//	@Query ("SELECT u FROM Usuario u WHERE u.id=:id")
//	public Usuario buscarUsuarioPorId(@Param ("id") Integer id);

	@Query ("SELECT u FROM Usuario u WHERE u.email=:email")
	public Usuario buscarUsuarioPorEmail(@Param ("email") String email);
}
