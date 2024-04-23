package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private List<FoodItemDto> foodItemsList;
    private Integer userId;
    private RestaurantDto restaurant;
}
