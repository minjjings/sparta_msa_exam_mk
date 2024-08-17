package com.sparta.msa_exam.order.dto;

import lombok.*;
//Product 서비스에서 제품 정보를 받을 때 사용

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDto {

    private String name;
    private Integer supply_price;

    
}
