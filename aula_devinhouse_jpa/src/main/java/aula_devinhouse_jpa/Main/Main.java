package aula_devinhouse_jpa.Main;

import aula_devinhouse_jpa.dominio.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static javax.persistence.Persistence.getPersistenceUtil;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("aula_jpa");

        EntityManager entityManager = emf.createEntityManager();

        // System.out.println("Está rodando!");

        // Evento evento = new Evento("Rock in Rio");
        // evento.setCodigo(1L);
        // Evento que seria inserido ou atualizado

        //Evento evento;

        List<Evento> eventos;

        entityManager.getTransaction().begin();
        // Abre a transação

        // entityManager.persist(evento);
        // Insere um dado no BD

        // entityManager.merge(evento);
        // Altera um dado no BD

        // entityManager.getTransaction().commit();
        // Realiza o commit alusivo à transação

        // evento = entityManager.find(Evento.class, 1L);
        // Busca o evento para monitorá-lo

        // evento.setNome("Rock in Rio");

//        evento = new Evento("Dev in House");
//        entityManager.persist(evento);
//
//        evento = new Evento("RD Summit");
//        entityManager.persist(evento);
//
//        evento = new Evento("PG Conf");
//        entityManager.persist(evento);
//
//        evento = new Evento("Latino Ware");
//        entityManager.persist(evento);
//
//        evento = new Evento("Mundo Senai");
//        entityManager.persist(evento);
//
//        evento = new Evento("Inova Senai");
//        entityManager.persist(evento);

//        evento = entityManager.find(Evento.class, 12L);
//
//        entityManager.remove(evento);
        // Remover um evento pelo codigo | o evento é monitorado!

        //eventos = entityManager.createQuery("FROM Evento").getResultList();

        //eventos = entityManager.createQuery("FROM Evento WHERE nome LIKE '%in%'").getResultList();

//       TypedQuery<Evento> query = entityManager.createQuery("FROM Evento WHERE nome LIKE ?1", Evento.class);
//
//       query.setParameter(1, "%in%");

       TypedQuery<Evento> query = entityManager.createQuery("FROM Evento WHERE nome LIKE :nome", Evento.class);

       query.setParameter("nome", "%in%");

       eventos = query.getResultList();
        //entityManager.getTransaction().commit();

        entityManager.close();
        // Fecha a transação

        //System.out.println(evento);

        eventos.forEach(System.out::println);

//        eventos.forEach(a -> {
//            System.out.println(a);
//        });
    }

}
