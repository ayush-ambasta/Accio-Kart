package com.example.acciokartservice.controller;

import com.example.acciokartservice.dto.request.ProductRequest;
import com.example.acciokartservice.model.Product;
import com.example.acciokartservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestParam("s_id") String sellerUniqueNumber,
                              @RequestBody ProductRequest productRequest){
        log.info("Product added");
        return productService.addProduct(sellerUniqueNumber,productRequest);
    }
}