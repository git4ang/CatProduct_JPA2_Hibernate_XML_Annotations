package ang.neggaw.dao;

import ang.neggaw.utils.HibernateUtil;
import ang.neggaw.entities.Category;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:34
 */

public class CategoryDaoImpl implements ICategoryDao {

    private final Session s = HibernateUtil.getSf().openSession();

    @Override
    public Category addCategory(Category c) {

        try {
            s.beginTransaction();
            s.persist(c);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error creation Category: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public Category getCategoryById(long idCat) {

        Category c = null;
        try {
            s.beginTransaction();
            s.find(Category.class, idCat);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error close with resultSet or preparedStatement");
            s.getTransaction().rollback();
        }
        return c;
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("select c from Category c");
            categories = (List<Category>) query.getResultList();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error Category in getAllCategory: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return categories;
    }

    @Override
    public List<Category> getAllCategoriesByMC(String mc) {

        List<Category> categories = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("select c from Category c where c.categoryName like %" + mc + "%");
            categories = (List<Category>) query.getResultList();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error Category in getAllCategory by key: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return categories;
    }

    @Override
    public Category updateCategory(Category c) {

        Category category = null;
        try {
            s.beginTransaction();
            category = (Category) s.merge(c);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error update Category: " + e.getMessage());
            s.getTransaction().rollback();
        }

        return category;
    }

    @Override
    public String deleteCategoryById(long idCat) {

        String res = "";
        try {
            s.beginTransaction();
            s.remove(getCategoryById(idCat));
            res = "The Category with ID '" + idCat + "' deleted successfully !!!";
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error delete Category: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return res;
    }
}
