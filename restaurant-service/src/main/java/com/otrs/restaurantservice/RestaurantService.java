package com.otrs.restaurantservice;

import com.otrs.restaurantservice.entity.Entity;
import com.otrs.restaurantservice.entity.Restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface RestaurantService {

    public void add(Restaurant restaurant) throws Exception;
    public void update(Restaurant restaurant) throws Exception;
    public  void delete(String id) throws Exception;

    public Entity findById(String restaurantId) throws Exception;

    public Collection<Restaurant> findByName(String name) throws Exception;
    public  Collection<Restaurant> findByCriteria(Map<String, ArrayList<Restaurant>> name) throws Exception;

}
