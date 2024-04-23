package com.codedecode.restaurantlisting.service;

import com.codedecode.restaurantlisting.dto.RestaurantDto;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RestaurantService  {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<RestaurantDto> findAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDto).toList();
    }

    public RestaurantDto addRestaurantInDB (RestaurantDto restaurantDto){
       Restaurant restaurant =  RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDto);
       Restaurant restaurant1 = restaurantRepository.save(restaurant);
       return  RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant1);

    }


    public RestaurantDto fetchRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        return  RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant);
    }
}
