package com.swaad.dao;

import java.util.List;

import com.swaad.User;

public interface UserDao {
	int addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
	boolean validateEmailPassword(String Email,String Password);
	String getNameByEmail(String email);
	User getUserByEmail(String email);
	int getUserIdByEmail(String email);
}
