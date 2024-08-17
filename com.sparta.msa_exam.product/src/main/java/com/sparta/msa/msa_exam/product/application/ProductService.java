package com.sparta.msa.msa_exam.product.application;

import com.sparta.msa.msa_exam.product.application.dto.CreateProductRequest;
import com.sparta.msa.msa_exam.product.application.dto.ProductResponse;
import com.sparta.msa.msa_exam.product.entity.Product;
import com.sparta.msa.msa_exam.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {



    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //상품 목록 조회 비즈니스 로직
    public List<ProductResponse> getProducts() {
    return productRepository.findAll().stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }

    public void createProduct(CreateProductRequest request) {
    productRepository.save(Product.create(request.getName(),request.getPrice()));
    }


}