package com.example.library;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class LibraryController {

    List<Book> bookList = new ArrayList<>();

    // 1. Welcome message
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System";
    }

    // 2. Total books count
    @GetMapping("/count")
    public int getBookCount() {
        return 100;
    }

    // 3. Sample book price
    @GetMapping("/price")
    public double getBookPrice() {
        return 499.99;
    }

    // 4. List of book titles
    @GetMapping("/books")
    public List<String> getBooks() {
        return Arrays.asList("Java Programming", "Spring Boot Guide", "Database Systems", "Cloud Computing");
    }

    // 5. Get book by ID
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Book details for ID: " + id;
    }

    // 6. Search book by title
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Searching for book: " + title;
    }

    // 7. Author name
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by author: " + name;
    }

    // 8. Add new book
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully";
    }

    // 9. View all added books
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}