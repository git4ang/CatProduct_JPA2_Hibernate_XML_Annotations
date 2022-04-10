package ang.neggaw.services;

import ang.neggaw.dao.CategoryDaoImpl;
import ang.neggaw.entities.Category;
import ang.neggaw.dao.ICategoryDao;

import java.util.List;

/**
 * author by: ANG
 * since: 10/04/2022 12:34
 */

public class CategoryService {

    private final ICategoryDao categoryDao = new CategoryDaoImpl();

    public Category createCategory(Category c) {
        return categoryDao.addCategory(c);
    }

    public Category getCategoryById(long idCat) {
        return categoryDao.getCategoryById(idCat);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public List<Category> getAllCategoriesParMC(String mc) {
        return categoryDao.getAllCategoriesByMC(mc);
    }

    public void updateCategory(Category c) {
        categoryDao.updateCategory(c);
    }

    public String deleteCategory(long idCat) {
        return categoryDao.deleteCategoryById(idCat);
    }
}
