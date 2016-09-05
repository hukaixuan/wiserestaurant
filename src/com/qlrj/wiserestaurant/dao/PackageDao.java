package com.qlrj.wiserestaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qlrj.wiserestaurant.domain.Good;
import com.qlrj.wiserestaurant.domain.Packages;
import com.qlrj.wiserestaurant.util.JDBCUtil;

public class PackageDao {
	/**
	 * 获取所有的套餐列表
	 * @return
	 * @throws Exception
	 */
	public List<Packages> getPackageList() throws Exception{
		
		Connection conn=JDBCUtil.getConnection();
		String sql="select * from packages ";
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet res=stmt.executeQuery();
		GoodDao goodDao = new GoodDao();
		ArrayList<Packages> packagesList = new ArrayList<Packages>();
		while(res.next()){
			String id=res.getInt(1)+"";
			String goodId = res.getString(2);
			String name = res.getString(3);
			String detail = res.getString(4);
			String pic = res.getString(5);
			float discount = res.getFloat(6);
			Packages packages = new Packages(id,goodId, name, detail,pic,discount);
			packagesList.add(packages);
		}
		return packagesList;
		
	}
	/**
	 * 根据套餐ID得到套餐信息
	 * @return
	 * @throws Exception
	 */
	public Packages getPackagesById(String sid ) throws Exception{
		int id = Integer.parseInt(sid);
		Connection conn=JDBCUtil.getConnection();
		String sql="select * from packages where id=? ";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet res=stmt.executeQuery();
		res.next();
		String goodId = res.getString(2);
		String name = res.getString(3);
		String detail = res.getString(4);
		String pic = res.getString(5);
		float discount = res.getFloat(6);
		Packages packages = new Packages(sid,goodId, name, detail,pic,discount);
		return packages;
		
	}

}
