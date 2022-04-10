package ang.neggaw.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * author by: ANG
 * since: 10/04/2022 12:12
 */

public class HibernateUtil {

    private static final SessionFactory sf;

    static {
        try {
            sf = new Configuration()
//                    .addClass(ang.neggaw.entities.Category.class)
//                    .addResource("mappings/Category.hbm.xml")
//                    .addClass(ang.neggaw.entities.Product.class)
//                    .addResource("mappings/Product.hbm.xml")
                    .configure("utils/Hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed. " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSf() {
        return sf;
    }
}
