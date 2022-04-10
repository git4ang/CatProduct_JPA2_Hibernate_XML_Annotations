package ang.neggaw.dao;

import ang.neggaw.entities.Product;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:34
 */

public interface IProductDao {
    void addProduct(Product p);
    Product getProductById(long idPro);
    List<Product> getAllProducts();
    List<Product> getAllProductsByMC(String mc);
    void updateProduct(Product p);
    String deleteProductById(long idPro);
}