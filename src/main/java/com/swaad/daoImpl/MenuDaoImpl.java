package com.swaad.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swaad.Menu;
import com.swaad.dao.MenuDao;

public class MenuDaoImpl implements MenuDao {
	
	final static String INSERT_QUERY = "INSERT INTO menu (menuId, name, price, description, imagePath, isAvailable, restaurantId, ratings) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	final static String SELECT_QUERY = "Select * from menu WHERE menuId=?";
	final static String UPDATE_QUERY = "UPDATE menu SET name=?, price=?, description=?, imagePath=?, isAvailable=?, restaurantId=?, ratings=? WHERE menuId=?";
	final static String DELETE_QUERY = "Delete from menu WHERE menuId=?";
	final static String SELECT_ALL_QUERY = "Select * from menu";
	final static String SELECT_MENU = "Select * from menu where restaurantId=?";

	
	private Connection connection;
	
	
	public MenuDaoImpl() {
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
	public void addMenu(Menu menu) {
	
		PreparedStatement prepareStatement;

		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, menu.getMenuId());
			prepareStatement.setString(2, menu.getName());
			prepareStatement.setInt(3, menu.getPrice());
			prepareStatement.setString(4, menu.getDescription());
			prepareStatement.setString(5, menu.getImagePath());
			prepareStatement.setBoolean(6, menu.getIsAvailable());
			prepareStatement.setInt(7, menu.getRestaurantId());
			prepareStatement.setFloat(8, menu.getRatings());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {

		PreparedStatement statement = null;
		ResultSet res = null;
		Menu menu = null;
		try {
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setInt(1, menuId);
			res=statement.executeQuery();
			if (res.next()) {

				String name = res.getString("name");
				int price = res.getInt("price"); 
				String description = res.getString("description"); 
				String imagePath = res.getString("imagePath");
				Boolean isAvailable = res.getBoolean("isAvailable");
				int restaurantId = res.getInt("restaurantId");
				Float ratings = res.getFloat("ratings");
				
				menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId, ratings);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		
		PreparedStatement prepareStatement;

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, menu.getName());
			prepareStatement.setInt(2, menu.getPrice());
			prepareStatement.setString(3, menu.getDescription());
			prepareStatement.setString(4, menu.getImagePath());
			prepareStatement.setBoolean(5, menu.getIsAvailable());
			prepareStatement.setInt(6, menu.getRestaurantId());
			prepareStatement.setFloat(7, menu.getRatings());

			prepareStatement.setInt(8, menu.getMenuId());
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {
		
		PreparedStatement preparedstatement = null;

		try {
			preparedstatement = connection.prepareStatement(DELETE_QUERY);

			preparedstatement.setInt(1, menuId);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public List<Menu> getAllMenu() {
		Statement statement = null;
		ResultSet res = null;
		Menu menu=null;

		ArrayList<Menu> menuList = new ArrayList<Menu>();
		try {
			statement = connection.createStatement();

			res = statement.executeQuery(SELECT_ALL_QUERY);
			while (res.next()) {

				int menuId = res.getInt("menuId");
				String name = res.getString("name");
				int price = res.getInt("price"); 
				String description = res.getString("description"); 
				String imagePath = res.getString("imagePath");
				Boolean isAvailable = res.getBoolean("isAvailable");
				int restaurantId = res.getInt("restaurantId");
				Float ratings = res.getFloat("ratings");
				
				menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId, ratings);
				menuList.add(menu);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return menuList;
	
	}

	@Override
	public List<Menu> getMenuByRestaurant(int restaurantId) {
		PreparedStatement preparedstatement;
		ResultSet res;
		Menu menu=null;

		ArrayList<Menu> list = new ArrayList<Menu>();
		try {
			preparedstatement = connection.prepareStatement(SELECT_MENU);

			preparedstatement.setInt(1,restaurantId );
			res=preparedstatement.executeQuery();
			while(res.next()) {
				int menuId = res.getInt("menuId");
				String name = res.getString("name");
				int price = res.getInt("price"); 
				String description = res.getString("description"); 
				String imagePath = res.getString("imagePath");
				Boolean isAvailable = res.getBoolean("isAvailable");
				Float ratings = res.getFloat("ratings");
				
				menu = new Menu(menuId, name, price, description, imagePath, isAvailable, restaurantId, ratings);
				list.add(menu);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
