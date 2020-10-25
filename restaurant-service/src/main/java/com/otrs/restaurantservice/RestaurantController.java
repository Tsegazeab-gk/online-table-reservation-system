package com.otrs.restaurantservice;


import com.otrs.restaurantservice.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("v1/restaurants")
public class RestaurantController {

    protected Logger logger = Logger.getLogger(RestController.class.getName());

    @Autowired
    protected RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Restaurant>> findByName(@RequestParam("name") String name) {

         logger.info(String.format("restaurant-service findByName() invoked:{} for {} ",
               restaurantService.getClass().getName() , name.trim().toLowerCase()));
        Collection<Restaurant> restaurants;

        try {
            restaurants = restaurantService.findByName(name);


        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<Collection<Restaurant>>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return restaurants.size() > 0 ? new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK)
                : new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/{restaurant_id",method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("restaurant_id") String id){

        logger.info(String.format("restaurant-service findById() invoked:{} for {} ", restaurantService.getClass(),
                id = id.trim()));
        Entity entity;

        try {
            entity=restaurantService.findById(id);
        }catch (Exception ex){
            logger.log(Level.WARNING,"Exception raised findById REST Call",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return entity!=null? new ResponseEntity<>(entity,HttpStatus.OK):new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Restaurant> add(@RequestBody RestaurantVO restaurantVO){
        logger.info(String.format("restaurant-service add() invoked: %s for %s",
                restaurantService.getClass().getName(),restaurantVO=restaurantVO));
        Restaurant restaurant;

        try {
           restaurant=restaurantService.add(restaurant);
        }catch (Exception ex){
            logger.log(Level.WARNING," Restaurant restaurant", ex);
            return new ResponseEntity<Restaurant>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<Restaurant>(HttpStatus.CREATED);
    }
}
