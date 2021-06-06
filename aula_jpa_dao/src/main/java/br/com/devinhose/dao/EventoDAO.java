package br.com.devinhose.dao;

import br.com.devinhose.dominio.Evento;

import javax.persistence.EntityManager;

import static br.com.devinhose.dominio.JPAUtil.entityManagerFactory;

public class EventoDAO extends DAO<Evento> {

    public void remover(Evento objeto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            objeto = entityManager.find(Evento.class, objeto.getCodigo());
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public Evento find(long l) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Evento evento = null;
        try {
            entityManager.getTransaction().begin();
            evento = entityManager.find(Evento.class, l);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return evento;
    }
}
