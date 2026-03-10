package com.example.productsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productsearch.entity.Product;
import com.example.productsearch.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Product> getByPriceRange(double min, double max) {
        return repository.findByPriceBetween(min, max);
    }

    public List<Product> getSortedProducts() {
        return repository.getProductsSortedByPrice();
    }

    public List<Product> getExpensiveProducts(double price) {
        return repository.getExpensiveProducts(price);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}