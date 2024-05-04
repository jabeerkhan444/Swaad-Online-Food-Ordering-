package com.swaad.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.swaad.Restaurant;

import com.swaad.dao.RestaurantDao;

public class RestaurantDaoImpl implements RestaurantDao{
	final static String INSERT_QUERY = "INSERT INTO restaurant (restaurantId, name, imagePath, rating, eta, cuisineType, address, isActive, restaurantAdminId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	final static String SELECT_QUERY = "Select * from restaurant WHERE restaurantId=?";
	final static String UPDATE_QUERY = "UPDATE restaurant SET name=?,imagePath=?,rating=?,eta=?,cuisineType=?,address=?,isActive=?,restaurantAdminId=? WHERE restaurantId=?";
	final static String DELETE_QUERY = "Delete from restaurant WHERE restaurantId=?";
	final static String SELECT_ALL_QUERY = "Select * from restaurant";

	
	private Connection connection;

	public RestaurantDaoImpl() {
		String url = "jdbc:mysql://localhost:3306/swaad";
		String username = "root";
		String password = "Jabeer@2000";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		PreparedStatement prepareStatement=null;

		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, restaurant.getRestaurantId());
			prepareStatement.setString(2, restaurant.getName());
			prepareStatement.setString(3, restaurant.getImagePath());
			prepareStatement.setFloat(4, restaurant.getRating());
			prepareStatement.setInt(5, restaurant.getEta());
			prepareStatement.setString(6, restaurant.getCuisineType());
			prepareStatement.setString(7, restaurant.getAddress());
			prepareStatement.setBoolean(8, restaurant.getisActive());
			prepareStatement.setInt(9, restaurant.getRestuarantAdminId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		PreparedStatement statement = null;
		ResultSet res = null;
		Restaurant restaurant = null;
		try {
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setInt(1, restaurantId);
			res=statement.executeQuery();
			if (res.next()) {

				String name = res.getString("name");
				String imagePath = res.getString("imagePath");
				Float rating = res.getFloat("rating");
				int eta = res.getInt("eta");
				String cuisineType = res.getString("cuisineType");
				String address = res.getString("address");
				Boolean isActive = res.getBoolean("isActive");
				int restaurantAdminId = res.getInt("restaurantAdminId");	
				
				restaurant = new Restaurant(restaurantId, name, imagePath, rating, eta, cuisineType, address, isActive, restaurantAdminId);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return restaurant;
	}

	
	
	
	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getImagePath());
			prepareStatement.setFloat(3, restaurant.getRating());
			prepareStatement.setInt(4, restaurant.getEta());
			prepareStatement.setString(5, restaurant.getCuisineType());
			prepareStatement.setString(6, restaurant.getAddress());
			prepareStatement.setBoolean(7, restaurant.getisActive());
			prepareStatement.setInt(8, restaurant.getRestuarantAdminId());

			prepareStatement.setInt(9, restaurant.getRestaurantId());
			
			prepareStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_QUERY);

			statement.setInt(1, restaurantId );
			statement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public List<Restaurant> getallRestaurants() {
		
		Statement statement = null;
		ResultSet res = null;
		Restaurant restaurant;
		
		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		try {
			statement = connection.createStatement();

			res = statement.executeQuery(SELECT_ALL_QUERY);
			while (res.next()) {

				int restaurantId = res.getInt("restaurantId");
				String name = res.getString("name");
				String imagePath = res.getString("imagePath");
				Float rating = res.getFloat("rating");
				int eta = res.getInt("eta");
				String cuisineType = res.getString("cuisineType");
				String address = res.getString("address");
				Boolean isActive = res.getBoolean("isActive");
				int restaurantAdminId = res.getInt("restaurantAdminId");	
				
				restaurant = new Restaurant(restaurantId, name, imagePath, rating, eta, cuisineType, address, isActive, restaurantAdminId);
				restaurantList.add(restaurant);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return restaurantList;
	}

}
