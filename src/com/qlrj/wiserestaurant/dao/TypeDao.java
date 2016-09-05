package com.qlrj.wiserestaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlrj.wiserestaurant.domain.Good;
import com.qlrj.wiserestaurant.domain.Type;
import com.qlrj.wiserestaurant.util.JDBCUtil;

public class TypeDao {
	/**
	 *   
	 * @return  返回所有菜品的类别信息
	 * @throws Exception
	 */
	public List<Type>  getVagetableTypes() throws Exception{
		Connection conn=JDBCUtil.getConnection();
		String sql=" select * from type";
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet res=stmt.executeQuery();
		List<Type> types=new ArrayList<Type>();
		while(res.next()){
			int id=res.getInt(1);
			String name=res.getString(2);
			Type type = new Type(id, name);
			types.add(type);
			
		}
		
		return types;
		
	}

}
