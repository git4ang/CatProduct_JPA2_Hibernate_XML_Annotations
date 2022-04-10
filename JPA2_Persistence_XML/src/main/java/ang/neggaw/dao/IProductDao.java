package ang.neggaw.dao;

import ang.neggaw.entities.Product;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 10:56
 */

public interface IProductDao {
    public void addProduct(Product p);
    public Product getProductById(long idPro);
    public List<Product> getAllProducts();
    public List<Product> getAllProductsByMC(String mc);
    public void updateProduct(Product p);
    public String deleteProductById(long idPro);
}
