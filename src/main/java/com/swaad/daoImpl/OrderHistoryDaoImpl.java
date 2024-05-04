package com.swaad.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swaad.OrderHistory;
import com.swaad.dao.OrderHistoryDao;

public class OrderHistoryDaoImpl implements OrderHistoryDao{
	final static String INSERT_QUERY = "Insert into `orderhistory` (orderHistoryId, orderId, userId) values (?, ?, ?)";
	final static String SELECT_QUERY = "Select * from orderhistory WHERE orderHistoryId=?";
	final static String UPDATE_QUERY = "UPDATE orderhistory SET orderId=?, userId=? WHERE `orderHistoryId`=?";
	final static String DELETE_QUERY = "Delete from orderhistory WHERE orderHistoryId=?";
	final static String SELECT_ALL_QUERY = "Select * from orderhistory";
	
	private Connection connection;

	public OrderHistoryDaoImpl() {
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
	public void addOrderHistory(OrderHistory orderHistory) {
		
		PreparedStatement prepareStatement;

		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, orderHistory.getOrderHistoryId());
			prepareStatement.setInt(2, orderHistory.getOrderId());
			prepareStatement.setInt(3, orderHistory.getUserId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {

		PreparedStatement statement = null;
		ResultSet res = null;
		OrderHistory orderHistory = null;
		try {
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setInt(1, orderHistoryId);
			res=statement.executeQuery();
			if (res.next()) {

				int orderId = res.getInt("orderId");
				int userId = res.getInt("userId"); 
				
				orderHistory = new OrderHistory(orderHistoryId,orderId, userId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return orderHistory;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		
		PreparedStatement prepareStatement;

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setInt(1, orderHistory.getOrderId());
			prepareStatement.setInt(2, orderHistory.getUserId());
			prepareStatement.setInt(3, orderHistory.getOrderHistoryId());

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_QUERY);

			statement.setInt(1, orderHistoryId);
			statement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<OrderHistory> getallOrderHistorys() {
		
		Statement statement = null;
		ResultSet res = null;
		OrderHistory orderHistory=null;

		ArrayList<OrderHistory> orderItemsList = new ArrayList<OrderHistory>();
		try {
			statement = connection.createStatement();

			res = statement.executeQuery(SELECT_ALL_QUERY);
			while (res.next()) {
				
				int orderHistoryId = res.getInt("orderHistoryId");
				int orderId = res.getInt("orderId");
				int userId = res.getInt("userId"); 
				
				orderHistory = new OrderHistory(orderHistoryId,orderId, userId);
				orderItemsList.add(orderHistory);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return orderItemsList;
	
	}	

	

}
