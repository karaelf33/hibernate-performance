package com.example.hibernateperformance;

import com.example.hibernateperformance.users.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class TestStatistics {

    public EntityManagerFactory emf;

    @Before
    public void init(){
        emf= Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @After
    public void close() {
        emf.close();
    }

    @Test
    public void logStatistics() {
        log.info("-----------logStatistics.......");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        for (User user : users) {
            log.info(user.getName() + " " + user.getSurName() + " user height" + user.getHeight());
        }
        em.getTransaction().commit();
        em.close();
    }
}
