package com.swaad.dao;

import java.util.List;

import com.swaad.OrderHistory;


public interface OrderHistoryDao {

	void addOrderHistory(OrderHistory orderHistory);
	OrderHistory getOrderHistory(int orderHistoryId);
	void updateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	List<OrderHistory> getallOrderHistorys();
}
