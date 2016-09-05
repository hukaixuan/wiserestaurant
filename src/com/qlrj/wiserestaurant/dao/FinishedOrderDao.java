package com.qlrj.wiserestaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.domain.OrderInfos;
import com.qlrj.wiserestaurant.util.JDBCUtil;

public class FinishedOrderDao {
	/**
	 * 
	 * @return  返回所有的完成的订单
	 * @throws Exception
	 */
	public List<OrderInfos> getOrderList() throws Exception{
		Connection conn=JDBCUtil.getConnection();
		String sql=" select * from orders" ;
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet res=stmt.executeQuery();
		ArrayList<OrderInfos> result = new ArrayList<OrderInfos>();
		Gson gson = new Gson();
		while(res.next()){
			String json = res.getString(2);
			OrderInfos order = gson.fromJson(json, OrderInfos.class);
			result.add(order);
			
		}
		return result;
		
	}
	/**
	 *   添加一条完成的订单
	 * @param json
	 * @throws Exception 
	 */
	public void  addFinishedOrder(String json) throws Exception{
		Connection conn=JDBCUtil.getConnection();
		String sql=" insert into orders(json) values (?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, json);
		if(stmt.executeUpdate()==1){
			
		}else{
			throw new RuntimeException("插入数据库失败");
		}
	}

}
