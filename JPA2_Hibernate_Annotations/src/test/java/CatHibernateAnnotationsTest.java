
import ang.neggaw.utils.HibernateUtil;
import ang.neggaw.dao.ICategoryDao;
import ang.neggaw.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * author by: ANG
 * since: 10/04/2022 12:40
 */

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CatHibernateAnnotationsTest {

    private static SessionFactory mockSessionFactory;
    private Session mockSession;
    private ICategoryDao mockCategoryDao;

    @BeforeAll
    public static void setup() {
        mockSessionFactory = HibernateUtil.getSf();
        System.out.println("SessionFactory created !");
    }

    @AfterAll
    public static void tearDown() {
        if(mockSessionFactory != null) mockSessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    @DisplayName("01 createCategory")
    @Order(1)
    public void createCategoryTest() {
        System.out.println("Running createCategory test... ");
        Category category = new Category("catTest", "bla bla bla ...test", new Date());
        category.setIdCategory(1L);

        when(mockCategoryDao.addCategory(category)).thenReturn(category);

        Category c = mockCategoryDao.addCategory(category);
        assertEquals("catTest", c.getCategoryName());
        assertEquals(1L, c.getIdCategory());

        verify(mockCategoryDao).addCategory(category);
    }

    @Test
    @DisplayName("02 getCategory")
    @Order(2)
    public void getCategoryTest() {

        System.out.println("Running getCategory test... ");
        Category c = new Category("catTest_get", "bla bla bla", new Date());

        when(mockCategoryDao.getCategoryById(anyLong())).thenReturn(c);
        Category c2 = mockCategoryDao.getCategoryById(3L);

        assertEquals("catTest_get", c2.getCategoryName());
        verify(mockCategoryDao).getCategoryById(3L);
    }

    @Test
    @DisplayName("03 allCategories")
    @Order(3)
    public void allCategoriesTest() {

        System.out.println("Running getAllCategories test... ");
        List<Category> categories = List.of(
                new Category("Sport", "bla bla bla...", new Date()),
                new Category("Vehicle", "bla bla bla...", new Date()),
                new Category("Legumes", "bla bla bla...", new Date()));

        when(mockCategoryDao.getAllCategories()).thenReturn(categories);

        assertEquals(3, mockCategoryDao.getAllCategories().size());
    }

    @Test
    @DisplayName("04 allCategoriesParMC")
    @Order(4)
    public void allCategoriesParMCTest() {

        System.out.println("Running getAllCategories test... ");
        List<Category> categories = List.of(
                new Category("Sport", "bla bla bla...", new Date()),
                new Category("Vehicle", "bla bla bla...", new Date()),
                new Category("Legumes", "bla bla bla...", new Date()));
        List<Category> listCatMC = List.of(
                new Category("Vehicle", "bla bla bla...", new Date()),
                new Category("Legumes", "bla bla bla...", new Date()));

        when(mockCategoryDao.getAllCategoriesByMC(anyString())).thenReturn(listCatMC);

        assertEquals(2, mockCategoryDao.getAllCategoriesByMC("e").size());
    }

    @Test
    @DisplayName("05 updateCategory")
    @Order(5)
    public void updateCategoryTest() {

        System.out.println("Running updateCategory test... ");
        Category c1_inserted = new Category("cat_name", "cat_description", new Date());
        c1_inserted.setIdCategory(1L);
        when(mockCategoryDao.getCategoryById(anyLong())).thenReturn(c1_inserted);
        Category c1_updated = mockCategoryDao.getCategoryById(1L);
        c1_updated.setDescription("bla bla bla " + c1_inserted.getDescription());
        c1_updated.setCategoryName("cat_name_updated");
        when(mockCategoryDao.updateCategory(c1_updated)).thenReturn(c1_updated);
        mockCategoryDao.updateCategory(c1_updated);
//        doNothing().when(mockCategoryDao).updateCategory(c1_updated);

        assertEquals("cat_name_updated", c1_updated.getCategoryName());
        verify(mockCategoryDao).updateCategory(c1_updated);
    }

    @Test
    @DisplayName("06 deleteCategory")
    @Order(6)
    public void deleteCategoryTest() {

        System.out.println("Running deleteCategory test... ");
        Category c1_inserted = new Category("cat_name", "cat_description", new Date());
        c1_inserted.setIdCategory(1L);
        when(mockCategoryDao.deleteCategoryById(anyLong())).thenReturn("cat deleted");

        assertEquals("cat deleted", mockCategoryDao.deleteCategoryById(1L));
        verify(mockCategoryDao).deleteCategoryById(1L);
    }

    @BeforeEach
    public void beforeEach() {
        mockSessionFactory = Mockito.mock(SessionFactory.class);
        mockSession = mock(Session.class);
        mockCategoryDao = mock(ICategoryDao.class);
        doReturn(mockSession).when(mockSessionFactory).openSession();
        MockitoAnnotations.openMocks(this);
        System.out.println("Session created");
    }

    @AfterEach
    public void afterEach() {
        if(mockSession != null) mockSession.close();
        System.out.println("Session closed");
    }
}
