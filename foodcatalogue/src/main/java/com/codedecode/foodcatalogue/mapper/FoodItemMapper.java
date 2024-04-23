package com.codedecode.foodcatalogue.mapper;

import com.codedecode.foodcatalogue.dto.FoodItemDto;
import com.codedecode.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOToFoodItem(FoodItemDto foodItemDTO);

    FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);

}
