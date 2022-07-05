package com.example.demo.web;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
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
    @PutMapping(path = "products/promotion/{id}")
    public ResponseEntity<?> setPromotion(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product p = product.get();
            p.setPromotion(!p.isPromotion());
            productRepository.save(p);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @GetMapping(path = "PagesProducts")
    public Page<Product> getAllProducts(@RequestParam int page,@RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
    @GetMapping(path = "PagesProductsByName")
    public Page<Product> getProductsByName(@RequestParam String search, @RequestParam int page,@RequestParam int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findByNameLike('%'+search+'%',pageable);
    }
}
