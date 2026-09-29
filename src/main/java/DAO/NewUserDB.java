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
import java.util.HashMap;
import java.util.Map;




public class NewUserDB {
    private static final EntityManagerFactory emf;

static {
    Map<String, Object> properties = new HashMap<>();

    String dbHost = System.getenv("DB_HOST");
    String dbUser = System.getenv("DB_USER");
    String dbPassword = System.getenv("DB_PASSWORD");

    if (dbHost != null && !dbHost.isBlank()) {
        properties.put(
            "jakarta.persistence.jdbc.url",
            "jdbc:postgresql://" + dbHost + ":5432/sqlgateway_db"
        );

        properties.put(
            "jakarta.persistence.jdbc.user",
            dbUser
        );

        properties.put(
            "jakarta.persistence.jdbc.password",
            dbPassword
        );
    }

    emf = Persistence.createEntityManagerFactory(
        "emailListPU",
        properties
    );
}
    
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
