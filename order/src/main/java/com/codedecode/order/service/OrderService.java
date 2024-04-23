package com.codedecode.order.service;

import com.codedecode.order.dto.OrderDetails;
import com.codedecode.order.dto.OrderDto;
import com.codedecode.order.dto.UserDto;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public OrderDto addOrder(OrderDetails orderDetails) {
        Integer newOrderID = sequenceGeneratorService.generateNextOrderId();
        Order order = new Order();
        order.setUser(getUserDetailsfromUserMS(orderDetails.getUserId()));
        order.setRestaurant(orderDetails.getRestaurant());
        order.setFoodItemsList(orderDetails.getFoodItemsList());
        order.setOrderId(newOrderID);
        Order order1 = orderRepository.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(order1);
    }

    public UserDto getUserDetailsfromUserMS(Integer userId){
      return   restTemplate.getForObject("http://User-MS/user/fetchUserById/" + userId, UserDto.class);

    }

}
