package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private List<FoodItemDto> foodItemsList;
    private RestaurantDto restaurant;
    private UserDto user;
}
