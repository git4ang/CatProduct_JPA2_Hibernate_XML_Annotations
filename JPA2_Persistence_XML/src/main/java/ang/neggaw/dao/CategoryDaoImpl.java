package ang.neggaw.dao;

import ang.neggaw.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 10:56
 */

public class CategoryDaoImpl implements ICategoryDao {

    private final EntityManagerFactory efm = Persistence.createEntityManagerFactory("catalogue_pu");
    private final EntityManager em = efm.createEntityManager();

    @Override
    public void addCategory(Category c) {

        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error creation Category: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    @Override
    public Category getCategoryById(long idCat) {

        Category c = null;

        try {
            em.getTransaction().begin();
            c = em.find(Category.class, idCat);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error find Category: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select c from Category c");
            categories = (List<Category>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error Category in getAllCategory: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return categories;
    }

    @Override
    public List<Category> getAllCategoriesByMC(String mc) {

        List<Category> categories = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select c from Category c where c like %" + mc + "%");
            categories = (List<Category>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error Category in getAllCategory by key: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return categories;
    }

    @Override
    public void updateCategory(Category c) {

        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error update Category: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    @Override
    public String deleteCategoryById(long idCat) {

        String res = "";
        try {
            em.getTransaction().begin();
            em.remove(getCategoryById(idCat));
            res = "The Category with ID '" + idCat + "' deleted successfully !!!";
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error delete Category: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return res;
    }
}
