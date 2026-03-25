package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.entity.Product;

public class HibernateUtil {

    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}