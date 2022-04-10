package ang.neggaw.dao;

import ang.neggaw.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 11:09
 */

public class ProductDaoImpl implements IProductDao {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogue_pu");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void addProduct(Product p) {

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error creation Product: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    @Override
    public Product getProductById(long idPro) {

        Product p = null;
        try {
            em.getTransaction().begin();
            p = em.find(Product.class, idPro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error found Product: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return p;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select p from Product p");
            products = (List<Product>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error Product in getProducts: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsByMC(String mc) {

        List<Product> products = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select p from Product p where productName like %" + mc + "%");
            products = (List<Product>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error Product in getProducts by key: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return products;
    }

    @Override
    public void updateProduct(Product p) {

        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error update Product: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    @Override
    public String deleteProductById(long idPro) {

        String res = "";
        try {
            em.getTransaction().begin();
            em.remove(getProductById(idPro));
            res = "The Product with ID '" + idPro + "' deleted successfully !!!";
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error delete Product: " + e.getMessage());
            em.getTransaction().rollback();
        }
        return res;
    }
}
