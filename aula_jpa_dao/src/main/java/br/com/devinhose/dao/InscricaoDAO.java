package br.com.devinhose.dao;

import br.com.devinhose.dominio.Inscricao;
import br.com.devinhose.dto.ClienteDTO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static br.com.devinhose.dominio.JPAUtil.entityManagerFactory;

public class InscricaoDAO extends DAO<Inscricao> {

    public List<ClienteDTO> findAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ClienteDTO> list = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            list = entityManager.createQuery(""
                    + "SELECT new br.com.devinhose.dto.ClienteDTO(i.cliente.nome, i.evento.nome, i.valor) from Inscricao i")
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return list;
    }
}
