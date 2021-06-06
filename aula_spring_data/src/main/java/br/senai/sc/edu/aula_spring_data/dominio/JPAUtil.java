package br.senai.sc.edu.aula_spring_data.dominio;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    public static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("aula_jpa");

}
