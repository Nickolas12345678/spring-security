package com.nickolas.productmanagement;

import com.nickolas.productmanagement.entities.Category;
import com.nickolas.productmanagement.entities.Product;
import com.nickolas.productmanagement.services.CategoryService;
import com.nickolas.productmanagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        Category electronics = new Category();
        electronics.setName("Електроніка");
        categoryService.saveCategory(electronics);

        Category clothing = new Category();
        clothing.setName("Одяг");
        categoryService.saveCategory(clothing);

        Category groceries = new Category();
        groceries.setName("Продукти");
        categoryService.saveCategory(groceries);


        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setPrice(15000);
        product1.setImage("laptop.jpg");
        product1.setCategory(electronics);
        productService.saveProduct(product1);

        Product product2 = new Product();
        product2.setName("T-shirt");
        product2.setPrice(500);
        product2.setImage("t-shirt.jpg");
        product2.setCategory(clothing);
        productService.saveProduct(product2);

        Product product3 = new Product();
        product3.setName("Milk");
        product3.setPrice(30);
        product3.setImage("milk.jpg");
        product3.setCategory(groceries);
        productService.saveProduct(product3);

        Product product4 = new Product();
        product4.setName("Headphones");
        product4.setPrice(30);
        product4.setImage("Headphones.jpg");
        product4.setCategory(electronics);
        productService.saveProduct(product4);

        Product product5 = new Product();
        product5.setName("Powerbank");
        product5.setPrice(800);
        product5.setImage("Powerbank.jpg");
        product5.setCategory(electronics);
        productService.saveProduct(product5);

        Product product6 = new Product();
        product6.setName("Chocolate");
        product6.setPrice(50);
        product6.setImage("Chocolate.jpg");
        product6.setCategory(groceries);
        productService.saveProduct(product6);

        Product product7 = new Product();
        product7.setName("Chicken");
        product7.setPrice(150);
        product7.setImage("Chicken.jpg");
        product7.setCategory(groceries);
        productService.saveProduct(product7);

        Product product8 = new Product();
        product8.setName("Smartphone");
        product8.setPrice(12000);
        product8.setImage("Smartphone.jpg");
        product8.setCategory(electronics);
        productService.saveProduct(product8);

        Product product9 = new Product();
        product9.setName("Bread");
        product9.setPrice(20);
        product9.setImage("Bread.jpg");
        product9.setCategory(groceries);
        productService.saveProduct(product9);

        Product product10 = new Product();
        product10.setName("Mouse");
        product10.setPrice(300);
        product10.setImage("Mouse.jpg");
        product10.setCategory(electronics);
        productService.saveProduct(product10);

        System.out.println("Дані успішно додано в базу");
    }
}
