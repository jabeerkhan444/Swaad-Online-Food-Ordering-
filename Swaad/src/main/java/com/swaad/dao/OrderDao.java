package com.swaad.dao;

import java.util.List;

import com.swaad.Order;

public interface OrderDao {
	void addOrder(Order order);
	Order getOrder(String orderId);
	void updateOrder(Order order);
	void deleteOrder(String orderId);
	List<Order> getallOrders();
}
