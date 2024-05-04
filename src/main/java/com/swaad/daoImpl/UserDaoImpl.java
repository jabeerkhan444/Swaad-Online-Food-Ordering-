package com.swaad.daoImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import com.swaad.User;
import com.swaad.dao.UserDao;

public class UserDaoImpl implements UserDao {

	final static String INSERT_QUERY = "Insert into user (`userId`, `name`, `email`, `phone`, `address`, `username`, `password`,`role`) values (?, ?, ?, ?, ?, ?, ?, ?)";
	final static String SELECT_QUERY = "Select * from `user` WHERE `userId`=?";
	final static String UPDATE_QUERY = "UPDATE `user` SET `name`=?,`email`=?,`phone`=?,`address`=?,`username`=?,`password`=?,`role`=? WHERE `userId`=?";
	final static String DELETE_QUERY = "Delete from `user` WHERE `userId`=?";
	final static String SELECT_ALL_QUERY = "Select * from `user`";
	final static String SELECT_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";
	final static String SELECT_NAME_BY_EMAIL = "SELECT `name` FROM user where email=?";
	final static String query = "SELECT * FROM user WHERE email = ?";
	final static String HASHING_ALGORITHM = "SHA-256";
	final static String QUERY_BY_EMAIL = "SELECT userId FROM user WHERE email = ?";
	
	private String encryptPassword(String Password) {
		try {
			MessageDigest digest = MessageDigest.getInstance(HASHING_ALGORITHM);
			byte[] hashBytes = digest.digest(Password.getBytes());
			return Base64.getEncoder().encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Connection connection;

	public UserDaoImpl() {
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
	public int addUser(User user) {
		PreparedStatement prepareStatement;
		int rowsAffected = 0;
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, user.getUserId());
			prepareStatement.setString(2, user.getName());
			prepareStatement.setString(3, user.getEmail());
			prepareStatement.setInt(4, user.getPhone());
			prepareStatement.setString(5, user.getAddress());
			prepareStatement.setString(6, user.getUsername());
			
			String encryptedPassword = encryptPassword(user.getPassword());
			
			prepareStatement.setString(7, encryptedPassword);
			prepareStatement.setString(8, user.getRole());

			rowsAffected = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public User getUser(int userId) {
		PreparedStatement statement = null;
		ResultSet res = null;
		User user = null;
		try {
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setInt(1, userId);
			res = statement.executeQuery();
			if (res.next()) {

				String name = res.getString("name");
				String email = res.getString("email");
				int phone = res.getInt("phone");
				String address = res.getString("address");
				String username = res.getString("username");
				String password = res.getString("password");
				String role = res.getString("role");
				Date createdDate = res.getDate("createdDate");
				Date lastLogin = res.getDate("lastLogin");
				
				user = new User(userId, name, email, phone, address, username, password, role, createdDate, lastLogin);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getPhone());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getUsername());
			
			String encryptedPassword = encryptPassword(user.getPassword());
			
			statement.setString(6, encryptedPassword);
			statement.setString(7, user.getRole());
			
			statement.setInt(8, user.getUserId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(int userId) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_QUERY);

			statement.setInt(1, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getAllUsers() {
		Statement statement = null;
		ResultSet res = null;

		ArrayList<User> userList = new ArrayList<>();
		try {
			statement = connection.createStatement();

			res = statement.executeQuery(SELECT_ALL_QUERY);
			while (res.next()) {

				int userId = res.getInt("userId");
				String name = res.getString("name");
				String email = res.getString("email");
				int phone = res.getInt("phone");
				String address = res.getString("address");
				String username = res.getString("username");
				String password = res.getString("password");
				String role = res.getString("role");
				Date createdDate = res.getDate("createdDate");
				Date lastLogin = res.getDate("lastLogin");

				User user = new User(userId, name, email, phone, address, username, password, role, createdDate, lastLogin);

				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public boolean validateEmailPassword(String email, String password) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_EMAIL_PASSWORD);
			preparedStatement.setString(1, email);
			String encryptedPassword = encryptPassword(password);
			
			preparedStatement.setString(2, encryptedPassword);
			
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public String getNameByEmail(String email) {
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String name = null;
	    
	    try {
	        preparedStatement = connection.prepareStatement(SELECT_NAME_BY_EMAIL);
	        preparedStatement.setString(1, email);
	        
	        resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            name = resultSet.getString("name");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return name;
	}

	@Override
	public User getUserByEmail(String email) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				int phone = resultSet.getInt("phone");
				String address = resultSet.getString("address");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				Date createdDate = resultSet.getDate("createdDate");
				Date lastLogin = resultSet.getDate("lastLogin");

				user = new User(userId, name, email, phone, address, username, password, role, createdDate, lastLogin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	@Override
	public int getUserIdByEmail(String email) {

		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int userId = 0;
        
        try {
            preparedStatement = connection.prepareStatement(QUERY_BY_EMAIL);
            preparedStatement.setString(1, email);
            
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                userId = resultSet.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return userId;
		
	}
	public Connection getConnection() {
        return connection;
    }
}
