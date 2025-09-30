package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;

public class ItemDetailsServiceImpl implements ItemDetailsService {
	
	
	private DataSource dataSource ;
	
	
	

	public ItemDetailsServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

	 @Override
	    public boolean addItemDetails(ItemDetails itemDetails) {
	        String sql = "INSERT INTO item_details (issueDate, expireDate, description, id) VALUES (?, ?, ?, ?)";
	        try (
	            Connection connection = dataSource.getConnection();
	            PreparedStatement ps = connection.prepareStatement(sql)
	        ) {
	            ps.setDate(1, itemDetails.getIssueDate());
	            ps.setDate(2, itemDetails.getExpireDate());
	            ps.setString(3, itemDetails.getDescription());
	            ps.setLong(4, itemDetails.getId());

	            int rows = ps.executeUpdate();
	            return rows > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	 @Override
	 public boolean editItemDetails(ItemDetails itemDetails) {
	     String sql = "UPDATE item_details SET issueDate = ?, expireDate = ?, description = ? WHERE detail_id = ? AND id = ?";

	     try (Connection conn = dataSource.getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql)) {

	         ps.setDate(1, itemDetails.getIssueDate());
	         ps.setDate(2, itemDetails.getExpireDate());
	         ps.setString(3, itemDetails.getDescription());
	         ps.setLong(4, itemDetails.getDetailId());
	         ps.setLong(5, itemDetails.getId());

	         int rowsUpdated = ps.executeUpdate();
	         return rowsUpdated > 0;

	     } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	     }
	 }



	 @Override
	 public ItemDetails getItemDetails(Long id) {
	     String sql = "SELECT * FROM item_details WHERE id = ?";
	     try (Connection connection = dataSource.getConnection();
	          PreparedStatement ps = connection.prepareStatement(sql)) {
	         ps.setLong(1, id);
	         ResultSet resultSet = ps.executeQuery();
	         if (resultSet.next()) {
	             ItemDetails itemDetails = new ItemDetails();
	             itemDetails.setId(resultSet.getLong("id"));
	             itemDetails.setDetailId(resultSet.getLong("detail_id"));
	             itemDetails.setIssueDate(resultSet.getDate("issueDate"));
	             itemDetails.setExpireDate(resultSet.getDate("expireDate"));
	             itemDetails.setDescription(resultSet.getString("description"));
	             return itemDetails;
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
	     return null;
	 }

	
}
