package com.user.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.item.model.Item;
import com.user.model.User;
import com.user.service.UserService;

public class UserServiceImpl implements UserService {
	
	
private DataSource dataSource ;
	

	public UserServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

		@Override
		public boolean signup(User user) {
			Connection connection = null ;
			Statement statement = null ;
			try {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				
				String sql = "INSERT INTO USERS (USERNAME, PASSWORD, FULL_NAME, EMAIL, AGE) VALUES ('"
				           + user.getUsername() + "', '"
				           + user.getPassword() + "', '"
				           + user.getFullName() + "', '"
				           + user.getEmail() + "', "
				           + user.getAge() + ")";
				           
				statement.executeUpdate(sql);
				
				return true ;
				
		   } catch (SQLException e) {
			e.printStackTrace();
		            }
		    finally {
		     try {
		        	if(connection != null )connection.close();
			        if(statement != null )statement.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		     }
		 
		}
			 return false ;
		}
		
		

		@Override
		public User login(String email, String password) {
		    Connection connection = null;
		    Statement statement = null;

		    try {
		        connection = dataSource.getConnection();
		        statement = connection.createStatement();

		        String sql = "SELECT * FROM USERS WHERE EMAIL = '" 
		                   + email + "' AND PASSWORD = '" 
		                   + password + "'";

		        ResultSet resultSet = statement.executeQuery(sql);

		        if (resultSet.next()) {
		            User user = new User();
		            user.setId(resultSet.getLong("id"));
		            user.setUsername(resultSet.getString("username"));
		            user.setPassword(resultSet.getString("password"));
		            user.setFullName(resultSet.getString("full_name"));
		            user.setEmail(resultSet.getString("email"));
		            user.setAge(resultSet.getInt("age"));

		            return user; 
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (statement != null) statement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return null; 
		}



}
