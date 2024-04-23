package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDto;
import com.codedecode.foodcatalogue.dto.RestaurantDto;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    RestTemplate restTemplate;
    public FoodItemDto addFoodItem(FoodItemDto foodItemDTO) {
        FoodItem foodItemSavedInDB = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemSavedInDB);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItemDto> foodItemList =  fetchFoodItemList(restaurantId);
        RestaurantDto restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItemDto> foodItemList, RestaurantDto restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurantDto(restaurant);
        return foodCataloguePage;
    }

    private RestaurantDto fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
     return   restTemplate.getForObject("http://Restaurant/restaurant/fetchById/" + restaurantId, RestaurantDto.class);
    }

    private List<FoodItemDto> fetchFoodItemList(Integer restaurantId) {
      List<FoodItem> foodItem = foodItemRepository.findByRestaurantId(restaurantId);
        return foodItem
                  .stream()
                   .map(FoodItemMapper.INSTANCE::mapFoodItemToFoodItemDto)
                   .toList();

    }
}
