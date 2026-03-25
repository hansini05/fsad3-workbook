package com.example;

import com.example.entity.Product;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Insert Data
        session.save(new Product("Laptop", "Electronics", 55000, 10));
        session.save(new Product("Mouse", "Electronics", 500, 50));
        session.save(new Product("Keyboard", "Electronics", 1500, 30));
        session.save(new Product("Chair", "Furniture", 3000, 5));
        session.save(new Product("Table", "Furniture", 7000, 2));
        session.save(new Product("Pen", "Stationery", 20, 100));
        session.save(new Product("Notebook", "Stationery", 80, 60));

        tx.commit();

        // -------- SORTING --------
        System.out.println("Ascending Price:");
        session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class)
                .list().forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        System.out.println("\nDescending Price:");
        session.createQuery("FROM Product p ORDER BY p.price DESC", Product.class)
                .list().forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        System.out.println("\nQuantity Desc:");
        session.createQuery("FROM Product p ORDER BY p.quantity DESC", Product.class)
                .list().forEach(p -> System.out.println(p.getName()+" "+p.getQuantity()));

        // -------- PAGINATION --------
        System.out.println("\nFirst 3:");
        Query<Product> q1 = session.createQuery("FROM Product", Product.class);
        q1.setFirstResult(0);
        q1.setMaxResults(3);
        q1.list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\nNext 3:");
        Query<Product> q2 = session.createQuery("FROM Product", Product.class);
        q2.setFirstResult(3);
        q2.setMaxResults(3);
        q2.list().forEach(p -> System.out.println(p.getName()));

        // -------- AGGREGATES --------
        Long total = (Long) session.createQuery("SELECT COUNT(*) FROM Product").uniqueResult();
        System.out.println("\nTotal: " + total);

        Long available = (Long) session.createQuery(
                "SELECT COUNT(*) FROM Product p WHERE p.quantity > 0").uniqueResult();
        System.out.println("Available: " + available);

        Object[] minMax = (Object[]) session.createQuery(
                "SELECT MIN(p.price), MAX(p.price) FROM Product p").uniqueResult();
        System.out.println("Min: " + minMax[0] + " Max: " + minMax[1]);

        System.out.println("\nGroup By Description:");
        List<Object[]> list = session.createQuery(
                "SELECT p.description, COUNT(*) FROM Product p GROUP BY p.description").list();

        for (Object[] row : list) {
            System.out.println(row[0] + " -> " + row[1]);
        }

        // -------- WHERE --------
        System.out.println("\nPrice between 1000 and 10000:");
        Query<Product> q3 = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max", Product.class);
        q3.setParameter("min", 1000);
        q3.setParameter("max", 10000);
        q3.list().forEach(p -> System.out.println(p.getName()));

        // -------- LIKE --------
        System.out.println("\nStarts with L:");
        session.createQuery("FROM Product p WHERE p.name LIKE 'L%'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\nEnds with p:");
        session.createQuery("FROM Product p WHERE p.name LIKE '%p'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\nContains 'top':");
        session.createQuery("FROM Product p WHERE p.name LIKE '%top%'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\nLength = 5:");
        session.createQuery("FROM Product p WHERE LENGTH(p.name)=5", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        session.close();
    }
}