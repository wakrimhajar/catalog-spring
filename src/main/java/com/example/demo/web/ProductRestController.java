package com.example.demo.web;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductRestController {

    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @GetMapping(path = "/products")
    public List<Product> listProduct(){
        return productRepository.findAll();
    }
    @GetMapping(path = "products/{id}")
    public Product getProduct(@PathVariable(name = "id")Long id){
        return productRepository.findById(id).get();
    }
    @PostMapping(path = "/products")
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
    @PutMapping(path = "/products/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        return productRepository.save(product);
    }
    @DeleteMapping(path = "/products/{id}")
    public void delete(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}
