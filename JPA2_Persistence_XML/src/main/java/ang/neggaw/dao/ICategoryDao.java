package ang.neggaw.dao;

import ang.neggaw.entities.Category;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 10:56
 */

public interface ICategoryDao {
    public void addCategory(Category c);
    public Category getCategoryById(long idCat);
    public List<Category> getAllCategories();
    public List<Category> getAllCategoriesByMC(String mc);
    public void updateCategory(Category c);
    public String deleteCategoryById(long idCat);
}
