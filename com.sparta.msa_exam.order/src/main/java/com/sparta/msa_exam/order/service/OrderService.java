package com.sparta.msa_exam.order.service;




import com.sparta.msa_exam.order.dto.OrderProductDto;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;


import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.feign.ProductClient;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;



    @Transactional
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {

    //1. Order 엔티티 생성 및 이름 설정

    Order order = new Order();
    order.setName(orderRequestDto.getName());

    List<OrderProduct> orderProducts = new ArrayList<>();
    for (Long product_id : orderRequestDto.getProduct_ids()) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct_id(product_id);
        orderProduct.setOrder(order);
        orderProducts.add(orderProduct);
    }
    order.setProduct_ids(orderProducts);

    //3. Order와 OrderProduct 저장
    Order savedOrder = orderRepository.save(order);
    orderProductRepository.saveAll(orderProducts);

    return mapToOrderResponseDto(savedOrder);
}

    private OrderResponseDto mapToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto(order);
        orderResponseDto.setOrder_id(order.getOrder_id());
        orderResponseDto.setName(order.getName());

        List<OrderProductDto> orderProductDtos = order.getProduct_ids().stream()
                .map(orderProduct -> {
                    OrderProductDto orderProductDto = new OrderProductDto();
                    orderProductDto.setProduct_id(orderProduct.getProduct_id());
                    return orderProductDto;
                })
                .collect(Collectors.toList());

        orderResponseDto.setProducts(orderProductDtos);
        return orderResponseDto;
    }

    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        return mapToOrderResponseDto(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(Long order_id, OrderRequestDto orderRequestDto) {
        // 1. 주문 ID로 기존 주문 조회
        Optional<Order> optionalOrder = orderRepository.findById(order_id);

        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order order = optionalOrder.get();

        // 2. 주문 정보 수정
        order.setName(orderRequestDto.getName());

        // 만약 OrderRequestDto에 상품 정보가 포함되어 있다면, 아래와 같이 상품 리스트를 업데이트할 수 있습니다.
        List<OrderProduct> updatedProducts = orderRequestDto.getProduct_ids().stream()
                .map(productId -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProduct_id(productId);
                    orderProduct.setOrder(order);
                    return orderProduct;
                }).collect(Collectors.toList());

        order.setProduct_ids(updatedProducts);

        // 3. 수정된 주문 저장
        Order savedOrder = orderRepository.save(order);

        // 4. OrderResponseDto로 변환하여 반환
        return new OrderResponseDto(savedOrder);
    }

//    public OrderResponseDto getOrderById(Long order_id) {
//        Optional<Order> orderOptional = orderRepository.findById(order_id);
//        if(orderOptional.isEmpty()) {
//            throw new IllegalArgumentException("Order not found");
//        }
//
//        Order order = orderOptional.get(); // Optional에서 Order 객체를 꺼냄
//
//        return new OrderResponseDto(order);
//    }

}//class
