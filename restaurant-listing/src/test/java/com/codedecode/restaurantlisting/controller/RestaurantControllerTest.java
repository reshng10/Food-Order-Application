package com.codedecode.restaurantlisting.controller;

import com.codedecode.restaurantlisting.dto.RestaurantDto;
import com.codedecode.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this); // in order to make
      // mocks and InjectMocks take effect, we need to call MockitoAnnotations.openMocks

    }



@Test
    public void testFetchAllRestaurants() {
        List<RestaurantDto> mockRestaurantDtoList = Arrays.asList(
       new RestaurantDto(1L,"Restaurant 1","Address 1","City 1","desc"),
        new RestaurantDto(2L,"Restaurant 2","Address 2","City 2","desc2")
        );

        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurantDtoList);

        // Call controller method as you did in main controller class
        ResponseEntity<List<RestaurantDto>> response  =  restaurantController.fetchAllRestaurants();

        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurantDtoList, response.getBody());

        // verify service method is called
    verify(restaurantService, times(1)).findAllRestaurants();

}
@Test
   public void testSaveRestaurant(){
        // create mock restaurantDto to be saved
    RestaurantDto restaurantDto = new RestaurantDto(1L,"res 1","address1","Baku","desc1");
    // Mock service behaviour
    when(restaurantService.addRestaurantInDB(restaurantDto)).thenReturn(restaurantDto);

    // call method as in controller class
      ResponseEntity<RestaurantDto> response =  restaurantController.saveRestaurant(restaurantDto);

      // verify response
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(restaurantDto,response.getBody());

    //verify service method called
    verify(restaurantService,times(1)).addRestaurantInDB(restaurantDto);

   }

   @Test
    public void testFindRestaurantById(){
        Long mockLongID =1L;

        // create mock restaurantDto be returned
 RestaurantDto restaurantDto = new RestaurantDto(1L,"res 1","address1","Baku","desc1");

    when(restaurantService.fetchRestaurantById(mockLongID)).thenReturn(restaurantDto);

     ResponseEntity<RestaurantDto>  response = restaurantController.findRestaurantById(mockLongID);

     assertEquals(HttpStatus.OK,response.getStatusCode());
     assertEquals(restaurantDto, response.getBody());

     verify(restaurantService,times(1)).fetchRestaurantById(mockLongID);
   }


}
