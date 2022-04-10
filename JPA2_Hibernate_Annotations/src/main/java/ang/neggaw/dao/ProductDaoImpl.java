package ang.neggaw.dao;

import ang.neggaw.utils.HibernateUtil;
import ang.neggaw.entities.Product;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:34
 */

public class ProductDaoImpl implements IProductDao {

    private Session s = HibernateUtil.getSf().openSession();

    @Override
    public void addProduct(Product p) {

        try {
            s.beginTransaction();
            s.persist(p);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error creation Product: " + e.getMessage());
            s.getTransaction().rollback();
        }
    }

    @Override
    public Product getProductById(long idPro) {

        Product p = null;
        try {
            s.beginTransaction();
            p = s.find(Product.class, idPro);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error find Product: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return p;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("select p from Product p");
            products = (List<Product>) query.getResultList();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error Product in getProducts: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsByMC(String mc) {

        List<Product> products = null;
        try {
            s.beginTransaction();
            Query query = s.createQuery("select p from Product p where p.productName like %" + mc + "%");
            products = (List<Product>) query.getResultList();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error Product in getProducts: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return products;
    }

    @Override
    public void updateProduct(Product p) {

        try {
            s.beginTransaction();
            s.merge(p);
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error update Product: " + e.getMessage());
            s.getTransaction().rollback();
        }
    }

    @Override
    public String deleteProductById(long idPro) {

        String res = "";
        try {
            s.beginTransaction();
            s.remove(getProductById(idPro));
            res = "Product with Id: '" + idPro + "' deleted successfull !!!";
            s.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error delete Product: " + e.getMessage());
            s.getTransaction().rollback();
        }
        return res;
    }
}
