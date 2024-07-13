package com.swaad.dao;

import java.util.List;

import com.swaad.OrderItems;

public interface OrderItemsDao {
	void addOrderItems(OrderItems orderItems);
	OrderItems getOrderItems(int orderItemsId);
	void updateOrderItems(OrderItems orderItems);
	void deleteOrderItems(int orderItemsId);
	List<OrderItems> getAllOrderItems();
	List<OrderItems> getOrderItemByUserId(int historyId);
}
