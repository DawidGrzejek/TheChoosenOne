package JPA;

import DBEntities.CredentialsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityManager {
    public static void main (String[] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        CredentialsEntity newCredentialsEntity = new CredentialsEntity();
        newCredentialsEntity.setUserName("JPATest");
        newCredentialsEntity.setPassword("PasswordJPAText");

        entityManager.persist(newCredentialsEntity);

        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();


    }
}
