package com.example.main;

import com.example.entity.Product;
import com.example.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;

public class HQLDemo {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Insert Records
        session.save(new Product("Laptop", "Electronics", 55000, 10));
        session.save(new Product("Mobile", "Electronics", 25000, 15));
        session.save(new Product("Chair", "Furniture", 3000, 20));
        session.save(new Product("Table", "Furniture", 7000, 5));
        session.save(new Product("Pen", "Stationery", 20, 100));
        session.save(new Product("Notebook", "Stationery", 60, 50));

        tx.commit();

        // Sorting by price ASC
        Query<Product> q1 =
                session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class);
        q1.list().forEach(p -> System.out.println(p.getPname()));

        // Pagination
        Query<Product> q2 = session.createQuery("FROM Product", Product.class);
        q2.setFirstResult(0);
        q2.setMaxResults(3);
        q2.list().forEach(p -> System.out.println(p.getPname()));

        // Aggregate - Count
        Long count = (Long) session
                .createQuery("SELECT COUNT(p) FROM Product p")
                .uniqueResult();
        System.out.println("Total Products: " + count);

        // Min & Max price
        Object[] result = (Object[]) session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p")
                .uniqueResult();
        System.out.println("Min Price: " + result[0]);
        System.out.println("Max Price: " + result[1]);

        session.close();
    }
}
