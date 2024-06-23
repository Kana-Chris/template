package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.template.dto.MyPageDTO;
import com.diworksdev.template.util.DBConnector;

public class MyPageDAO {
	
	public MyPageDTO getMyPageUserInfo(String item_transaction_id,String user_master_id)throws SQLException {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		MyPageDTO myPageDTO = new MyPageDTO();
		
		String sql = "SELECT item_name,total_price,total_count,pay FROM user_buy_item_transaction LEFT JOIN item_info_transaction ON item_transaction_id WHERE item_transaction_id=? AND user_master_id=? ORDER BY user_buy_item_transaction.insert_date DESC";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2,user_master_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				myPageDTO.setItemName(resultSet.getString("item_name"));
				myPageDTO.setTotalPrice(resultSet.getString("total_price"));
				myPageDTO.setTotalCount(resultSet.getString("total_count"));
				myPageDTO.setPayment(resultSet.getString("pay"));
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}finally {
				connection.close();
		}
		return myPageDTO;
	}
	
		public int buyItemHistoryDelete(String item_transaction_id,String user_master_id)throws SQLException{
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			
			String sql = "DELETE FROM user_buy_item_transaction WHERE item_transaction_id=? AND user_master_id=?";
			PreparedStatement preparedStatement;
			int result = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);
			
			result = preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return result;
		
	}

}

