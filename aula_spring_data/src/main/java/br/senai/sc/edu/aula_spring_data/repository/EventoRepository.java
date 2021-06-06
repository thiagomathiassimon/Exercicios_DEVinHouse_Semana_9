package br.senai.sc.edu.aula_spring_data.repository;

import br.senai.sc.edu.aula_spring_data.dominio.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> nomeContainingOrderByNomeDesc(String nome);

    @Query("Select k from Evento k where k.nome like ?1")
    List<Evento> buscarPorNome(String nome);

    @Query(value = "Select count (*) from Evento", nativeQuery = true)
    Integer countEvento();

    Evento codigo(Long codigo);

    @Query(value = "Select * from Evento e where e.nome like ?1", nativeQuery = true)
    List<Evento> buscarPorNomeNativo(String nome);


}
