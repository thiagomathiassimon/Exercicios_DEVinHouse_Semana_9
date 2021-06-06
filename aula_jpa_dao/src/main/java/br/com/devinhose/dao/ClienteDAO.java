package br.com.devinhose.dao;

import br.com.devinhose.dominio.Cliente;

import javax.persistence.EntityManager;

import static br.com.devinhose.dominio.JPAUtil.entityManagerFactory;

public class ClienteDAO extends DAO<Cliente>{

    public Cliente find(long l) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = null;
        try {
            entityManager.getTransaction().begin();
            cliente = entityManager.find(Cliente.class, l);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return cliente;
    }

}
