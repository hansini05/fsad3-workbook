package com.inventory;

import com.inventory.dao.ProductDAO;
import com.inventory.entity.Product;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // INSERT
        dao.addProduct(new Product("Laptop", "Gaming Laptop", 75000, 10));
        dao.addProduct(new Product("Phone", "Android Phone", 20000, 25));

        // READ
        Product p = dao.getProduct(1);
        System.out.println(p.getName() + " - " + p.getPrice());

        // UPDATE
        dao.updateProduct(1, 70000, 8);

        // DELETE
        dao.deleteProduct(2);
    }
}