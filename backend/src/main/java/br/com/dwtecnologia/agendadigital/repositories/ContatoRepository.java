package br.com.dwtecnologia.agendadigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dwtecnologia.agendadigital.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>  {

}
