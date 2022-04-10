package ang.neggaw.dao;

import ang.neggaw.entities.Category;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:20
 */

public interface ICategoryDao {
    void addCategory(Category c);
    Category getCategoryById(long idCat);
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByMC(String mc);
    void updateCategory(Category c);
    String deleteCategoryById(long idCat);
}