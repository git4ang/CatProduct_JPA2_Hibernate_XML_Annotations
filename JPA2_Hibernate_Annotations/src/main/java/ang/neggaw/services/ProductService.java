package ang.neggaw.services;

import ang.neggaw.dao.IProductDao;
import ang.neggaw.dao.ProductDaoImpl;
import ang.neggaw.entities.Product;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:35
 */

public class ProductService {

    private final IProductDao productDao = new ProductDaoImpl();

    public void addProduct(Product p) {
        productDao.addProduct(p);
    }

    public Product getProductById(long idPro) {
        return productDao.getProductById(idPro);
    }

    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }

    public List<Product> getAllProductsByMC(String mc) {
        return productDao.getAllProductsByMC(mc);
    }

    public void updateProduct(Product p) {
        productDao.updateProduct(p);
    }

    public String deleteProductById(long idPro) {
        return productDao.deleteProductById(idPro);
    }
}

