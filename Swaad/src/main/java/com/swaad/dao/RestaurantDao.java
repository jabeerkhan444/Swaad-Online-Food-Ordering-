package com.swaad.dao;

import java.util.List;

import com.swaad.Restaurant;

public interface RestaurantDao {
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
	List<Restaurant> getallRestaurants();
}
