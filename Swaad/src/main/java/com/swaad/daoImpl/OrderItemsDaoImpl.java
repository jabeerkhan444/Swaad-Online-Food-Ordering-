package com.swaad.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swaad.OrderItems;
import com.swaad.dao.OrderItemsDao;

public class OrderItemsDaoImpl implements OrderItemsDao {
    
    final static String INSERT_QUERY = "INSERT into `orderitems` "
            + "(`orderItemId`, `userId`, `menuId`, `itemName`, `quantity`, `price`, `restaurantId`, `historyId`) "
            + "values (?,?,?,?,?,?,?,?)";
    
    final static String SELECT_QUERY = "SELECT * from `orderitems` WHERE `orderItemId` = ?";
    
    final static String UPDATE_QUERY = "UPDATE `orderitems` SET  `userId` = ?, `menuId` = ?, "
            + "`itemName` = ?, `quantity` = ?, `price` = ?, `restaurantId` = ?, `historyId` = ? WHERE `orderItemId` = ?";
    
    final static String DELETE_QUERY = "DELETE from `orderitems` WHERE `orderItemId` = ?";
    
    final static String SELECT_ALL_QUERY = "SELECT * FROM `orderitems`";
    
    final static String SELECT_ORDERITEMSHISTORY = "SELECT * FROM `orderitems`  WHERE `userId` = ?";
    
    private Connection connection;
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public OrderItemsDaoImpl() {
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
    public void addOrderItems(OrderItems orderitems) {
        PreparedStatement prepareStatement = null;
        
        try {
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            prepareStatement.setInt(1, orderitems.getOrderItemId());
            prepareStatement.setInt(2, orderitems.getUserId());
            prepareStatement.setInt(3, orderitems.getMenuId());
            prepareStatement.setString(4, orderitems.getName());
            prepareStatement.setInt(5, orderitems.getQuantity());
            prepareStatement.setInt(6, orderitems.getPrice());
            prepareStatement.setInt(7, orderitems.getRestaurantId());
            prepareStatement.setInt(8, orderitems.getHistoryId());
            
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public OrderItems getOrderItems(int orderItemId) {
        PreparedStatement statement = null;
        ResultSet res = null;
        OrderItems orderitems = null;
        
        try {
            statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, orderItemId);
            res = statement.executeQuery();
            
            if (res.next()) {
                int userid = res.getInt("userId");
                int menuid = res.getInt("menuId");
                String itemname = res.getString("itemName");
                int quantity = res.getInt("quantity");
                int price = res.getInt("price");
                int restaurantid = res.getInt("restaurantId");
                int historyid = res.getInt("historyId");
                orderitems = new OrderItems(orderItemId, userid, menuid, itemname, quantity, price, restaurantid, historyid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderitems;
    }
    
    @Override
    public void updateOrderItems(OrderItems orderitems) {
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setInt(1, orderitems.getUserId());
            statement.setInt(2, orderitems.getMenuId());
            statement.setString(3, orderitems.getName());
            statement.setInt(4, orderitems.getQuantity());
            statement.setInt(5, orderitems.getPrice());
            statement.setInt(6, orderitems.getRestaurantId());
            statement.setInt(7, orderitems.getHistoryId());
            statement.setInt(8, orderitems.getOrderItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteOrderItems(int orderItemId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, orderItemId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<OrderItems> getAllOrderItems() {
        Statement statement = null;
        ResultSet res = null;
        ArrayList<OrderItems> orderitemslist = new ArrayList<>();
        
        try {
            statement = connection.createStatement();
            res = statement.executeQuery(SELECT_ALL_QUERY);
            while (res.next()) {
                int orderitemid = res.getInt("orderItemId");
                int userid = res.getInt("userId");
                int menuid = res.getInt("menuId");
                String itemname = res.getString("itemName");
                int quantity = res.getInt("quantity");
                int price = res.getInt("price");
                int restaurantid = res.getInt("restaurantId");
                int historyid = res.getInt("historyId");
                OrderItems orderitems = new OrderItems(orderitemid, userid, menuid, itemname, quantity, price, restaurantid, historyid);
                orderitemslist.add(orderitems);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderitemslist;
    }
    
    public List<OrderItems> getOrderItemByUserId(int userId) {
        PreparedStatement preparestatement;
        ResultSet res;
        OrderItems orderitems = null;
        ArrayList<OrderItems> list = new ArrayList<OrderItems>();
        
        try {
            preparestatement = connection.prepareStatement(SELECT_ORDERITEMSHISTORY);
            preparestatement.setInt(1, userId);
            res = preparestatement.executeQuery();
            while (res.next()) {
                int orderitemid = res.getInt("orderItemId");
                int historyId = res.getInt("historyId");
                int menuid = res.getInt("menuId");
                String itemname = res.getString("itemName");
                int quantity = res.getInt("quantity");
                int price = res.getInt("price");
                int restaurantid = res.getInt("restaurantId");
                
                orderitems = new OrderItems(orderitemid, userId, menuid, itemname, quantity, price, restaurantid, historyId);
                list.add(orderitems);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
