package ang.neggaw.dao;
import ang.neggaw.connections.MyConnectionDB;
import ang.neggaw.entities.Category;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 10:31
 */

public class CategoryDaoImpl implements ICategoryDao {

    private final String tableNameCategory = "categories_JDBC_tb";

    @Override
    public void createTableCategory() {

        // idCategory, nomCategory, description, dateCreation
        String sql = "create table if not exists " + tableNameCategory + """ 
                (
                    idCategory int(7) primary key auto_increment,
                    nomCategory varchar(20) not null unique,
                    description text(100),
                    dateCreation Date    
                )
                """;
        try (Connection cn = MyConnectionDB.getConnection(); Statement st = cn.createStatement(); ){
            if(st.executeUpdate(sql) > 0)
                System.out.println("The table 'categories_JDBC_tb' created successfully !!!\n");
            else
                System.out.println("The table 'categories_JDBC_tb' already created !!!\n");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error creation table: " + e.getMessage());
        }
    }

    @Override
    public void addCategory(Category c) {

        String sql = "insert into " + tableNameCategory + " (idCategory, nomCategory, description, dateCreation) values (null, ?, ?, ?)";

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement pst = cn.prepareStatement(sql);
        ) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dateCreation = sdf.format(new java.util.Date());

            pst.setString(1, c.getCategoryName());
            pst.setString(2, c.getDescription());
            pst.setString(3, dateCreation);

            int i = pst.executeUpdate();
            if(i > 0)
                System.out.println("The Category with the category's name: '" + c.getCategoryName() + "' created successfully !!!");
            else
                System.err.println("Error creation Category !!!");

        } catch (Exception e) {
            System.err.println("Error creation Category: " + e.getMessage());
        }
    }

    @Override
    public Category getCategoryById(long idCat) {

        String sql = "select * from " + tableNameCategory + " where idCategory = ?";
        Category c = null;
        ResultSet rs = null;

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement pst = cn.prepareStatement(sql);
        ){
            pst.setLong(1, idCat);
            rs = pst.executeQuery();

            if (!rs.next())
                System.out.println("The Category with ID: '" + idCat + "' is not found !!!\n");
            else {
                c = new Category();
                c.setIdCategory(rs.getLong(1));
                c.setCategoryName(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setDateCreation(rs.getDate(4));
            }
            return c;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("Error found Category: " + e.getMessage());

        }
        finally {
            try {
                if(rs != null) rs.close();
            } catch (Exception e) {
                System.err.println("Error close with resultSet or preparedStatement");
            }
        }
        return c;
    }

    @Override
    public List<Category> getAllCategories() {

        String sql = "select * from " + tableNameCategory;
        List<Category> categories = null;

        try (
                Connection cn = MyConnectionDB.getConnection();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
        ) {
            categories = new ArrayList<>();
            while (rs.next()) {
                Category c = new Category();
                c.setIdCategory(rs.getLong(1));
                c.setCategoryName(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setDateCreation(rs.getDate(4));
                categories.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error Category in getAllCategory: " + e.getMessage());
        }

        return categories;
    }

    @Override
    public List<Category> getAllCategoriesByMC(String mc) {

        String sql = "select * from " + tableNameCategory + " where nomCategory like ?";
        List<Category> categories = null;
        ResultSet rs;

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
        ) {

            stmt.setString(1, "%" + mc + "%");
            rs = stmt.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                Category c = new Category();
                c.setIdCategory(rs.getLong(1));
                c.setCategoryName(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setDateCreation(rs.getDate(4));
                categories.add(c);
            }
        } catch (Exception e) {
            System.err.println("Error Category in getAllCategory by key: " + e.getMessage());
        }

        return categories;
    }

    @Override
    public void updateCategory(Category c) {

        String sql = "update " + tableNameCategory + " set nomCategory = ?, description = ?, dateCreation = ? where idCategory = ?";

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement pst = cn.prepareStatement(sql);
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateCreation = sdf.format(c.getDateCreation());

            pst.setString(1, c.getCategoryName());
            pst.setString(2, c.getDescription());
            pst.setString(3, dateCreation);
            pst.setLong(4, c.getIdCategory());

            if(pst.executeUpdate() > 0)
                System.out.println("the Category with ID: '" + c.getIdCategory() + "' updated successfully !!!\n");
        } catch (Exception e) {
            System.err.println("Error update Category: " + e.getMessage());
        }
    }

    @Override
    public String deleteCategoryById(long idCat) {

        String sql = "delete * from " + tableNameCategory + " where idCategory = ?";
        String res = "";

        try (
                Connection cn = MyConnectionDB.getConnection();
                PreparedStatement stmt = cn.prepareStatement(sql);
        ) {
            stmt.setLong(1, idCat);
            if (stmt.executeUpdate() > 0)
                res = "The Category with ID '" + idCat + "' deleted successfully !!!";

        } catch (Exception e) {
            System.err.println("Error delete Category: " + e.getMessage());
        }

        return res;
    }
}
