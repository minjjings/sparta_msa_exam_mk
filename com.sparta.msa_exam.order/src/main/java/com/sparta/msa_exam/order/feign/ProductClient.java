package com.sparta.msa_exam.order.feign;


import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.ProductResponseDto;

import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="product-service" , url="localhost:19093")
public interface ProductClient {


    @GetMapping("/products")
    List<ProductResponseDto> getAllProducts();


    @GetMapping("/products/{product_id}")
    ProductResponseDto getProductById(@PathVariable("product_id") Long product_id);

}
