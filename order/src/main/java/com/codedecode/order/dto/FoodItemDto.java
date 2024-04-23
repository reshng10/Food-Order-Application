package com.codedecode.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDto {
    private String itemName;

    private String itemDescription;

    private boolean isVeg;

    private Long price;

    private Integer restaurantId;

    private Integer quantity;
}
