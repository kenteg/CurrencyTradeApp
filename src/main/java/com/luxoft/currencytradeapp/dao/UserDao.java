package com.luxoft.currencytradeapp.dao;

import com.luxoft.currencytradeapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 20 2016
 */
public class UserDao {
    @PersistenceContext(unitName="entityManagerFactory")
    private EntityManager em;

    public User getUserByName(String login){
     //   EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        try {
            String queryText = "select u from user u where u.username = :login";
            TypedQuery<User> query = em.createQuery(queryText, User.class);
            query.setParameter("login", login);
            return query.getSingleResult();
        }
        finally {
            em.getTransaction().rollback();
        }
    }

    public void addUser(User user){
      //  EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        try {
           em.persist(user);
        }
        finally {
            em.getTransaction().commit();
        }
    }
}
