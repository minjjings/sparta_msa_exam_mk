package com.sparta.msa.msa_exam.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name", unique = true)
    private String name;

    @Column(name = "supply_price")
    private Long price;

    // 생성자 또는 정적 팩토리 메서드
    public static Product create(String name, Long price) {
        Product product = new Product();
        product.name = name;
        product.price = price;
        return product;
    }
}
