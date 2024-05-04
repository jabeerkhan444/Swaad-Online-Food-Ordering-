package com.swaad;

public class OrderItems {

	

	private int orderItemId;

	private int userId;

	private int menuId;

	private String name;

	private int quantity;

	private int price;

	private int restaurantId;

	private int historyId;

	

	public OrderItems() {

		

	}



	public OrderItems(int orderItemId, int userId, int menuId, String name, int quantity, int price, int restaurantId,

			int historyId) {

		super();

		this.orderItemId = orderItemId;

		this.userId = userId;

		this.menuId = menuId;

		this.name = name;

		this.quantity = quantity;

		this.price = price;

		this.restaurantId = restaurantId;

		this.historyId = historyId;

	}



	public int getOrderItemId() {

		return orderItemId;

	}



	public void setOrderItemId(int orderItemId) {

		this.orderItemId = orderItemId;

	}



	public int getUserId() {

		return userId;

	}



	public void setUserId(int userId) {

		this.userId = userId;

	}



	public int getMenuId() {

		return menuId;

	}



	public void setMenuId(int menuId) {

		this.menuId = menuId;

	}



	public String getName() {

		return name;

	}



	public void setName(String name) {

		this.name = name;

	}



	public int getQuantity() {

		return quantity;

	}



	public void setQuantity(int quantity) {

		this.quantity = quantity;

	}



	public int getPrice() {

		return price;

	}



	public void setPrice(int price) {

		this.price = price;

	}



	public int getRestaurantId() {

		return restaurantId;

	}



	public void setRestaurantId(int restaurantId) {

		this.restaurantId = restaurantId;

	}



	public int getHistoryId() {

		return historyId;

	}



	public void setHistoryId(int historyId) {

		this.historyId = historyId;

	}


}
