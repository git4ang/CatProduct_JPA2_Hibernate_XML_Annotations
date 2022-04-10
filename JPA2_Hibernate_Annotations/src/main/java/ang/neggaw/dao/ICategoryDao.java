package ang.neggaw.dao;

import ang.neggaw.entities.Category;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:33
 */

public interface ICategoryDao {
    Category addCategory(Category c);
    Category getCategoryById(long idCat);
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByMC(String mc);
    Category updateCategory(Category c);
    String deleteCategoryById(long idCat);
}
