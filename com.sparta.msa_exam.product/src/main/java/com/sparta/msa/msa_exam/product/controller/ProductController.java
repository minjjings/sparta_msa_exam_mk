package com.sparta.msa.msa_exam.product.controller;


import com.sparta.msa.msa_exam.product.application.ProductService;
import com.sparta.msa.msa_exam.product.application.dto.CreateProductRequest;
import com.sparta.msa.msa_exam.product.application.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService)  {
        this.productService = productService;
    }

    //제품 등록
    @PostMapping
    public Boolean addProduct(
            final @RequestBody CreateProductRequest request
    ) {
        productService.createProduct(request);
        return true;

    }
    //제품 리스트 조회
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }


    }//class
