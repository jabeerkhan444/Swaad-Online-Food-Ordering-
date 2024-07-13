package com.swaad.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swaad.Order;
import com.swaad.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
	
	final static String INSERT_QUERY = "Insert into `order` (orderId, restaurantId, userId, totalAmount, modeOfPayment, status, historyId) values (?, ?, ?, ?, ?, ?, ?)";
	final static String SELECT_QUERY = "Select * from `order` WHERE orderId=?";
	final static String UPDATE_QUERY = "UPDATE `order` SET restaurantId=?, userId=?, totalAmount=?, modeOfPayment=?, status=?, historyId=? WHERE `orderId`=?";
	final static String DELETE_QUERY = "Delete from `order` WHERE orderId=?";
	final static String SELECT_ALL_QUERY = "Select * from `order`";
	
	private Connection connection;

	public OrderDaoImpl() {
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
	public void addOrder(Order order) {
		
		PreparedStatement prepareStatement;

		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setString(1, order.getOrderId());
			prepareStatement.setInt(2, order.getRestaurantId());
			prepareStatement.setInt(3, order.getUserId());
			prepareStatement.setInt(4, order.getTotalAmount());
			prepareStatement.setString(5, order.getModeOfPayment());
			prepareStatement.setString(6, order.getStatus());
			prepareStatement.setInt(7, order.getHistoryId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Order getOrder(String orderId) {
		
		PreparedStatement statement = null;
		ResultSet res = null;
		Order order = null;
		try {
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setString(1, orderId);
			res=statement.executeQuery();
			if (res.next()) {

				int restaurantId = res.getInt("restaurantId");
				int userId = res.getInt("userId");
				int totalAmount = res.getInt("totalAmount");
				String modeOfPayment = res.getString("modeOfPayment");
				String status = res.getString("status");
				int historyId = res.getInt("historyId");
				
				order = new Order(orderId, restaurantId, userId, totalAmount, modeOfPayment, status, historyId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		PreparedStatement prepareStatement;

		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, order.getRestaurantId());
			prepareStatement.setInt(2, order.getUserId());
			prepareStatement.setInt(3, order.getTotalAmount());
			prepareStatement.setString(4, order.getModeOfPayment());
			prepareStatement.setString(5, order.getStatus());
			prepareStatement.setInt(6, order.getHistoryId());

			prepareStatement.setString(7, order.getOrderId());
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(String orderId) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_QUERY);

			statement.setString(1, orderId);
			statement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getallOrders() {
		Statement statement = null;
		ResultSet res = null;
		Order order=null;
		ArrayList<Order> orderList = new ArrayList<Order>();
		try {
			statement = connection.createStatement();
			res=statement.executeQuery(SELECT_QUERY);
			if (res.next()) {

				String orderId = res.getString("orderId");
				int restaurantId = res.getInt("restaurantId");
				int userId = res.getInt("userId");
				int totalAmount = res.getInt("totalAmount");
				String modeOfPayment = res.getString("modeOfPayment");
				String status = res.getString("status");
				int historyId = res.getInt("historyId");
				
				order = new Order(orderId, restaurantId, userId, totalAmount, modeOfPayment, status, historyId);
				
				orderList.add(order);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return orderList;
	}

}
