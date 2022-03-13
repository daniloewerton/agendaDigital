package br.com.dwtecnologia.agendadigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dwtecnologia.agendadigital.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>  {

}
