package com.codedecode.restaurantlisting.service;


import com.codedecode.restaurantlisting.dto.RestaurantDto;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants() {
       // create mock data
        List<Restaurant> mockRestaurantList = Arrays.asList(
  new Restaurant(1L,"restaurant","dohany street","budapest","desc")
        );

        // mock repository method
        when(restaurantRepository.findAll()).thenReturn(mockRestaurantList);

      List<RestaurantDto> mockRestaurantListDto = mockRestaurantList.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDto).toList();

         // call restaurantService as it was in service class
        List<RestaurantDto> response = restaurantService.findAllRestaurants();

        // verify the response
        assertEquals(mockRestaurantListDto,response);

        // verify restaurant repository called
        verify(restaurantRepository,times(1)).findAll();
    }

    @Test
    public void testAddRestaurantInDB() {
       // create mock object
       RestaurantDto mockRestaurantDto = new RestaurantDto(1L,"rest","dohany","budapest","desc");
       Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(mockRestaurantDto);

       // mock repository method
       when(restaurantRepository.save(mockRestaurant)).thenReturn(mockRestaurant);

        // call real service method as in service class
       RestaurantDto response = restaurantService.addRestaurantInDB(mockRestaurantDto);

       // verify the results
        assertEquals(mockRestaurantDto,response);
        verify(restaurantRepository,times(1)).save(mockRestaurant);
    }

    @Test
    public void testFetchRestaurantById(){
        // Mock long parameneter
  Long mockLong = 1L;

  // mock restaurant and restaurentDto object
  Restaurant mockRestaurant = new Restaurant(1L,"rest","address","budapest","desc");
       when(restaurantRepository.findById(mockLong)).thenReturn(Optional.of(mockRestaurant));
       RestaurantDto mockRestaurantDto = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(mockRestaurant);

       // call real service method
       RestaurantDto response =  restaurantService.fetchRestaurantById(mockLong);

       // verify result
        assertEquals(mockRestaurantDto,response);
        verify(restaurantRepository,times(1)).findById(mockLong);
    }

//    @Test
//    public void testFetchRestaurantById_byNonExistingId(){
//        Long mockLong = 1L;
//        Restaurant mockRestaurant = null;
//        when(restaurantRepository.findById(mockLong)).thenReturn(Optional.empty());
//
//        //call method itself
//        RestaurantDto response = restaurantService.fetchRestaurantById(mockLong);
//
//        assertNull(response);
//        verify(restaurantRepository,times(1)).findById(mockLong);
//    }
}
