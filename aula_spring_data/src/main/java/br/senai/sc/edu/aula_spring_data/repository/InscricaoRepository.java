package br.senai.sc.edu.aula_spring_data.repository;

import br.senai.sc.edu.aula_spring_data.dominio.Inscricao;
import br.senai.sc.edu.aula_spring_data.dto.ClienteEventoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    @Query("Select new br.senai.sc.edu.aula_spring_data.dto.ClienteEventoDTO(i.cliente.nome, i.evento.nome) from Inscricao i")
    List<ClienteEventoDTO> buscarClienteEvento();

    @Query(name = "ClienteEventoQuery", nativeQuery = true)
    List<ClienteEventoDTO> buscarClienteEventoNativo();


}
