package com.sparta.msa_exam.order.controller;


import com.sparta.msa_exam.order.dto.*;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.feign.ProductClient;
import com.sparta.msa_exam.order.repository.OrderRepository;
import com.sparta.msa_exam.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderRepository orderRepository;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    // 주문추가 API
    @PostMapping("/order")
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto orderRequestDto) {
       OrderResponseDto savedOrder = orderService.addOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    // 단건조회
//    @GetMapping("/order/{order_id}")
//    public ResponseEntity<OrderResponseDto> getOrderById(
//            @PathVariable Long order_id){
//
//        System.out.println(order_id);
//
//        //Order에 id에 대한 조회
//        OrderResponseDto searchOrder = orderService.getOrderById(order_id);
//        return ResponseEntity.status(HttpStatus.OK).body(searchOrder);
//
//    }
    //주문 번호로 주문 name , product_id 리스트 조회
    @GetMapping("/order/{order_id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long order_id) {
        OrderResponseDto getOrderById = orderService.getOrderById(order_id);
        return ResponseEntity.status(HttpStatus.OK).body(getOrderById);
    }


    //수정
    @PutMapping("/order/{order_id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable Long order_id,
            @RequestBody OrderRequestDto orderRequestDto) {

        try {
            // 서비스에서 주문 업데이트 처리
            OrderResponseDto updatedOrder = orderService.updateOrder(order_id, orderRequestDto);
            return ResponseEntity.ok(updatedOrder);
        } catch (IllegalArgumentException e) {
            // 주문이 존재하지 않는 경우 404 Not Found 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    }

