package com.sparta.msa_exam.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//주문에 포함된 제품 정보를 응답할 때 사용
public class OrderProductDto {
    private Long product_id;

}
