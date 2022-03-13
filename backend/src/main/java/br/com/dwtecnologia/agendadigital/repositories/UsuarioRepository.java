package br.com.dwtecnologia.agendadigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dwtecnologia.agendadigital.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
