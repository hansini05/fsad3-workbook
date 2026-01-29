package com.inventory.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductCRUD {

    public static void main(String[] args) {

        // INSERT PRODUCTS
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Laptop", "Dell i5", 55000, 10);
        Product p2 = new Product("Mobile", "Samsung Galaxy", 25000, 20);

        session.save(p1);
        session.save(p2);

        tx.commit();
        session.close();

        // RETRIEVE PRODUCT
        session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, 1);
        System.out.println("Retrieved: " + product.getName());
        session.close();

        // UPDATE PRODUCT
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        product = session.get(Product.class, 1);
        product.setPrice(52000);
        product.setQuantity(8);

        session.update(product);
        tx.commit();
        session.close();

        // DELETE PRODUCT
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Product p = session.get(Product.class, 1); // existing ID
        if (p != null) {
            session.delete(p);
        }

        tx.commit();
        session.close();

        System.out.println("CRUD Operations Completed Successfully");
    }
}
