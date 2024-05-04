package com.swaad.daoImpl;

import com.swaad.Menu;
import com.swaad.Order;
import com.swaad.OrderHistory;
import com.swaad.OrderItems;
import com.swaad.Restaurant;
import com.swaad.User;

public class Demo {

	public static void main(String[] args) {
//		UserDaoImpl u=new UserDaoImpl();
//		User u1=new User(3, "marshal", "marsh@gmail.com", 65475, "ght", "marshmill", "marsh134", "RestaurantAdmin", null, null);
//		u.updateUser(u1);
//		UserDaoImpl u=new UserDaoImpl();
//		User u1=new User(1, "marshal", "marsh@gmail.com", 65475, "ght", "marshmill", "marsh134", "RestaurantAdmin", null, null);
//		u.addUser(u1);
		
		
		UserDaoImpl u=new UserDaoImpl();
		String a="jabeerkhan924@gmail.com";
		u.getUserByEmail(a);
		
		
		//RestaurantDaoImpl r=new RestaurantDaoImpl();
		//Restaurant r1=new Restaurant(0,"TAJ","https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/mpxqoksbeig38xs1wkgm",4.5f,20,"pizza","blg",true,4);
//		int r1=1;
//		r.getRestaurant(r1);
		//r.addRestaurant(r1);

//		MenuDaoImpl md = new MenuDaoImpl();
//		int a=1;
//		System.out.println(md.getMenuByRestaurant(a));
//		Menu m = new Menu(0, "kfc", 999, "high", "kgf", false, 1, 5.1f);
//		md.addMenu(m);
		
//		OrderDaoImpl oi=new OrderDaoImpl();
//		Order o=new Order(0, 1, 3, "Cash", "Delivered");
//		oi.addOrder(o);
		
//		OrderDaoImpl od=new OrderDaoImpl();
//		int a=1;
//		od.getOrder(a);
//		
//		
//		OrderItemsDaoImpl oi = new OrderItemsDaoImpl();
//		OrderItems o = new OrderItems(0, 4, 1, "pizza", 7, 2.8f, 129);
//		oi.addOrderItems(o);
		
//		OrderHistoryDaoImpl oh=new OrderHistoryDaoImpl();
//		OrderHistory o=new OrderHistory(1, 1, 3);
//		oh.addOrderHistory(o);
	}

}
