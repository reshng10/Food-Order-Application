package com.codedecode.order.entity;

import com.codedecode.order.dto.FoodItemDto;
import com.codedecode.order.dto.RestaurantDto;
import com.codedecode.order.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    @Id
    private Integer orderId;
    private List<FoodItemDto> foodItemsList;
    private RestaurantDto restaurant;
    private UserDto user;
}
