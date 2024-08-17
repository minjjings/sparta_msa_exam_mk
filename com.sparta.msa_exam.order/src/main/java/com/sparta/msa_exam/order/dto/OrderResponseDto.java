package com.sparta.msa_exam.order.dto;


import com.sparta.msa_exam.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//주문 정보를 클라이언트에 응답할 때 사용
public class OrderResponseDto {
    private Long order_id;
    private String name;
    private List<OrderProductDto> products;
    private List<Long> product_ids;
    public OrderResponseDto(Order order) {
    }
}
