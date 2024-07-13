package com.swaad;

public class Restaurant {
	
	private int restaurantId;
	private String name;
	private String imagePath;
	private float rating;
	private int eta;
	private String cuisineType;
	private String address;
	private boolean isActive;
	private int restuarantAdminId;
	
	public Restaurant() {
	
	}

	public Restaurant(int restaurantId, String name, String imagePath, float rating, int eta, String cuisineType, String address,
			boolean isActive, int restuarantAdminId) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.imagePath = imagePath;
		this.rating = rating;
		this.eta = eta;
		this.cuisineType = cuisineType;
		this.address = address;
		this.isActive = isActive;
		this.restuarantAdminId = restuarantAdminId;
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getisActive() {
		return isActive;
	}

	public void setisActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getRestuarantAdminId() {
		return restuarantAdminId;
	}

	public void setRestuarantAdminId(int restuarantAdminId) {
		this.restuarantAdminId = restuarantAdminId;
	}
	
	
}
