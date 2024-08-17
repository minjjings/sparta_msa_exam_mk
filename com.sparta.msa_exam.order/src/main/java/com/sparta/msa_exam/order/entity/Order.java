package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_tbl")
@NoArgsConstructor
//주문
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(nullable = false)
    private String name; //주문 이름

    //@OneToMany 어노테이션은 일대다 관계를 나타냅니다.
    // 즉, 한 개의 `order` 엔티티가 여러 개의 `OrderProduct`엔티티를 가질 수 있음을 의미합니다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> product_ids = new ArrayList<>();  // 주문에 포함된 제품 목록


}
