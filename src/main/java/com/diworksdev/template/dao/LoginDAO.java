package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.template.dto.LoginDTO;
import com.diworksdev.template.util.DBConnector;

public class LoginDAO {
	
	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword) {
		
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();
		LoginDTO loginDTO = new LoginDTO();
		String sql = "SELECT * FROM login_user_transaction where login_id=? and login_pass=?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1,loginUserId);
			preparedStatement.setString(2,loginPassword);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				loginDTO.setLoginId(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));
				
				if(resultSet.getString("login_id")!=null) {
					loginDTO.setLoginFlg(true);
				}
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}
		
		return loginDTO;
	}
}
