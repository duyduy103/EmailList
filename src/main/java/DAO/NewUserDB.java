/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import Model.User;
import java.util.List;




public class NewUserDB {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emailListPU");
    
    public static EntityManagerFactory getEmfactory () {
        return emf;
    }
    
    public static EntityManager getEntityManager() {
        return getEmfactory().createEntityManager();
    } 
    
    public static void insert(User user) {
        
        EntityManager em = getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
        trans.begin();
        em.persist(user);
        trans.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();

        if (trans.isActive()) {
            trans.rollback();
        }
        }
        finally {
            em.close();
        }
    }
    
    public static User getUser(long userId) {
        EntityManager em = getEntityManager();
        try {
            User user = em.find(User.class, userId);
            return user;
        }
        catch(Exception ex){
            return null;
        }
        finally {
            em.close();
        }
    }
    
    public static void update(User user){
        EntityManager em = getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        }
        catch (Exception ex ) {
            trans.rollback();
        }
        finally {
            em.close();
        }
    }
    
    public static void delete(long userId) {
    EntityManager em = getEntityManager();
    EntityTransaction trans = em.getTransaction();

    try {
        trans.begin();

        User user = em.find(User.class, userId);

        if (user != null) {
            em.remove(user);
        }

        trans.commit();

    } catch (Exception ex) {
        ex.printStackTrace();

        if (trans.isActive()) {
            trans.rollback();
        }

    } finally {
        em.close();
    }
}
    
    public static List<User> getUsers() {
        EntityManager em = getEntityManager();
        String qstring = "Select u from User u";
        TypedQuery<User> query = em.createQuery(qstring, User.class);
        List<User> user;
        try {
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }
    
    
}
