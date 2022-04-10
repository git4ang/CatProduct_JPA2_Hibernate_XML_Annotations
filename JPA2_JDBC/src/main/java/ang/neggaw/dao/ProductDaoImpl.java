package ang.neggaw.dao;

import ang.neggaw.connections.MyConnectionDB;
import ang.neggaw.entities.Category;
import ang.neggaw.entities.Product;
import ang.neggaw.services.CategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 10:32
 */

public class ProductDaoImpl implements IProductDao {

    private final String tableNameProduct = "products_JDBC_tb";

    @Override
    public void createTableProduct() {

        // idProduct, productName, description, price, idCategory
        String sql = "create table if not exists " + tableNameProduct + """
                (
                    idProduct int(7) primary key auto_increment,
                    productName varchar(20) not null unique,
                    description text(100),
                    price double not null,
                    idCategory int(7) not null,
                    foreign key (idCategory) references categories_JDBC_tb (idCategory)
                )
                """;
        try (
                Connection cn = MyConnectionDB.getConnection();
                Statement st = cn.createStatement();
        ){
            if(st.executeUpdate(sql) > 0)
                System.out.println("The table '" + tableNameProduct + "'  created successfully !!!");
            else
                System.out.println("The table '" + tableNameProduct + "' already created !!!");
        } catch (Exception e) {
            System.err.println("Error connection to DB: " + e.getMessage());
        }
    }

    @Override
    public void addProduct(Product p) {

        String sql = "insert into " + tableNameProduct + " (productName, description, price, idCategory) values (?, ?, ?, ?)";

        CategoryService categoryService = new CategoryService();
        Category c = categoryService.getCategoryById(p.getIdCategory());

        if (c != null) {
            try (
                    Connection cn = MyConnectionDB.getConnection();
                    PreparedStatement stmt = cn.prepareStatement(sql);
            ) {
                stmt.setString(1, p.getProductName());
                stmt.setString(2, p.getDescription());
                stmt.setDouble(3, p.getPrice());
                stmt.setLong(4, p.getIdCategory());

                int i = stmt.executeUpdate();
                if (i > 0)
                    System.out.println("Product with name: '" + p.getProductName() + "' created successfully !!!");
            } catch(Exception e){
                System.err.println("Error creation Product: " + e.getMessage());
            }
        }
    }

    @Override
    public Product getProductById(long idPro) {

        String sql = "select * from " + tableNameProduct + " where idProduct = ?";
        Product p = null;
        ResultSet rs = null;
        CategoryService categoryService = new CategoryService();

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
        ){
            stmt.setLong(1, idPro);
            rs = stmt.executeQuery();
            if(!rs.next())
                System.err.println("Product with Id: '" + idPro + "' not found !!!");
            else {
                p = new Product();
                p.setIdProduct(idPro);
                p.setProductName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setIdCategory(rs.getLong(5));

                Category c = categoryService.getCategoryById(rs.getLong(5));
                p.setCategory(c);
            }
            return p;
        } catch (Exception e) {
            System.err.println("Error found Product: " + e.getMessage());
        }
        return p;
    }

    @Override
    public List<Product> getAllProducts() {

        String sql = "select * from " + tableNameProduct;
        List<Product> products = null;

        try (
                Connection cn = MyConnectionDB.getConnection();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ){
            products = new ArrayList<>();
            while(rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getLong(1));
                p.setProductName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setIdCategory(rs.getLong(5));
                products.add(p);
            }
            return products;
        } catch (Exception e) {
            System.err.println("Error Product in getProducts: " + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsByMC(String mc) {

        String sql = "select * from " + tableNameProduct + " where productName like ?";
        ResultSet rs;
        List<Product> products = null;

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
        ) {
            stmt.setString(1, '%' + mc + '%');
            rs = stmt.executeQuery();

            products = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getLong(1));
                p.setProductName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setIdCategory(rs.getLong(5));
                products.add(p);
            }
            return products;
        } catch (Exception e) {
            System.err.println("Error Product in getProducts by key: " + e.getMessage());
        }
        return products;
    }

    @Override
    public void updateProduct(Product p) {

        String sql = "update " + tableNameProduct + " set productName = ?, description = ?, price = ?";

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
        ){
            stmt.setString(1, p.getProductName());
            stmt.setString(2, p.getDescription());
            stmt.setDouble(3, p.getPrice());

            int i = stmt.executeUpdate();
            if (i > 0)
                System.out.println("Product with name: '" + p.getProductName() + "' created successfully !!!");

        } catch (Exception e) {
            System.err.println("Error update Product: " + e.getMessage());
        }
    }

    @Override
    public String deleteProductById(long idPro) {

        String sql = "delete * from " + tableNameProduct + " where idProduct = ?";
        String res = "";

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
        ){
            stmt.setLong(1, idPro);
            if (stmt.executeUpdate() > 0)
                res = "The Product with ID '" + idPro + "' deleted successfully !!!";
        } catch (Exception e) {
            System.err.println("Error delete Product: " + e.getMessage());
        }
        return res;
    }
}

