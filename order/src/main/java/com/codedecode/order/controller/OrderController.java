package com.codedecode.order.controller;

import com.codedecode.order.dto.OrderDetails;
import com.codedecode.order.dto.OrderDto;
import com.codedecode.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDetails orderDetails){
      OrderDto newOrderDto1 =  orderService.addOrder(orderDetails);
        return new ResponseEntity<>(newOrderDto1, HttpStatus.CREATED);
    }
}
