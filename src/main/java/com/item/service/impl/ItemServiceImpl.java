package com.item.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.ItemService;

public class ItemServiceImpl implements ItemService {
	
	private DataSource dataSource ;
	

	public ItemServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	
	@Override
	public boolean addItems(Item item) {
		Connection connection = null ;
		Statement statement = null ;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String sql = "INSERT INTO ITEM (NAME, PRICE, TOTAL_NUMBER) VALUES ('" 
			           + item.getName() + "', " 
			           + item.getPrice() + ", " 
			           + item.getTotalNumber() + ")";

			statement.execute(sql);
			
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
	public boolean deleteItem(Long id) {
		Connection connection = null ;
		Statement statement = null ;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String sql = "DELETE FROM item WHERE id = " + id ;
			statement.execute(sql);
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
	public boolean editItem(Item item) {
		Connection connection = null ;
		Statement statement = null ;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String sql = "UPDATE item SET name = '" + item.getName() + 
		             "', price = " + item.getPrice() + 
		             ", total_number = " + item.getTotalNumber() + 
		             " WHERE id = " + item.getId();
			
			statement.execute(sql);
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
	public Item getItem(Long id) {
		Connection connection = null ;
		Statement statement = null ;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM ITEM WHERE id = " + id ;
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
			
			Item item = new Item() ;
			item.setId(resultSet.getLong("id"));
			item.setName(resultSet.getString("name"));
			item.setPrice(resultSet.getDouble("price"));
			item.setTotalNumber(resultSet.getLong("total_number"));
			
			return item ;
			}
			
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
		 return null ;
	
}

		

	

	@Override
	public List<Item> getItems() {
		Connection connection = null ;
		Statement statement = null ;
	
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM ITEM";
			ResultSet resultSet = statement.executeQuery(sql);
			
			List<Item> items = new ArrayList<>();
			while(resultSet.next()) {
				
				Item item = new Item() ;
				item.setId(resultSet.getLong("id"));
				item.setName(resultSet.getString("name"));
				item.setPrice(resultSet.getDouble("price"));
				item.setTotalNumber(resultSet.getLong("total_number"));
				items.add(item);
				
			}
			
			return items ;
					
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
		return new ArrayList<>() ;
	}
	
	

	

}
