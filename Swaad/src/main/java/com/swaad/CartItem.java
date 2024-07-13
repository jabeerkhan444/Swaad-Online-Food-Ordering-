package com.swaad;

public class CartItem {
	private int menuId;
	private int restaurantId;
	private String name;
	private String imagePath;
	private int price;
	private int quantity;
	
	public CartItem() {

	}

	
	public CartItem(int menuId, int restaurantId, String name, String imagePath, int price, int quantity) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.imagePath = imagePath;
		this.price = price;
		this.quantity = quantity;
	}


	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "CartItem[menuId="+menuId+", restaurantId="+restaurantId+",name="+name+", imagePath"+ imagePath+", price="+price+", quantity="+quantity+"]";
	}
	
	
}
