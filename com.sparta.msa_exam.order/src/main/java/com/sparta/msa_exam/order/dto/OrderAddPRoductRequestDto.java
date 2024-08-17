package com.sparta.msa_exam.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//주문 추가 요청시 사용
public class OrderAddPRoductRequestDto {
    private Long product_id;
}
