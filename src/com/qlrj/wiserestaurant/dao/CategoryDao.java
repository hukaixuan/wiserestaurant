package com.qlrj.wiserestaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qlrj.wiserestaurant.domain.Category;
import com.qlrj.wiserestaurant.domain.Type;
import com.qlrj.wiserestaurant.util.JDBCUtil;

public class CategoryDao {
	
	/**
	 *   
	 * @return  返回所有商品的类别信息
	 * @throws Exception
	 */
	public List<Category>  getGoodTypes() throws Exception{
		Connection conn=JDBCUtil.getConnection();
		String sql=" select * from category ";
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet res=stmt.executeQuery();
		List<Category> categories=new ArrayList<Category>();
		while(res.next()){
			int id=res.getInt(1);
			String name=res.getString(2);
			Category category = new Category(id, name);
			if("菜品".equals(name)){//给菜品的子分类初始化数据
				List<Type> types = new TypeDao().getVagetableTypes();
				category.types=types;
			}
			
			categories.add(category);
			
		}
		
		return categories;
		
	}

}
