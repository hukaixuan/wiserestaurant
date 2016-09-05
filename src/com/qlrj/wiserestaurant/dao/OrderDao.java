package com.qlrj.wiserestaurant.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.qlrj.wiserestaurant.domain.Good;
import com.qlrj.wiserestaurant.domain.GoodListInfo;
import com.qlrj.wiserestaurant.domain.OrderItemInfo;
import com.qlrj.wiserestaurant.domain.OrderInfos;
import com.qlrj.wiserestaurant.util.JDBCUtil;

public class OrderDao implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用商品的id和商品的数量来保存商品
	private HashMap<String ,String> goodInfo=new HashMap<String ,String>();
	public boolean isPackages=false;
	public float discount=1.0f;
	public HashMap<String, String> getGoodInfo() {
		return goodInfo;
	}
	public void setGoodInfo(HashMap<String, String> goodInfo) {
		this.goodInfo = goodInfo;
	}

	//添加计算购物车商品总价的属性
	private float totalPrice=0.0f;
	private String orderNum;
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public float getTotalPrice(){
		return this.totalPrice;
	}
	//根据GoodId 返回goodNum
	public String getNumById(String goodId){
		return this.goodInfo.get(goodId);
	}
	
	public Set<String> getKeySet(){
		return goodInfo.keySet();
	}
	
	
	//加入商品
	public void addGood(String goodId,String goodNum){
		goodInfo.put(goodId, goodNum);
	}
	//删除商品
	public void removeGood(String goodId){
		goodInfo.remove(goodId);
	}
	//修改商品数量
	public void updateGoodNum(String goodId,String goodNum){
		goodInfo.put(goodId, goodNum);
	}
	//清空购物车
	public void clearCart(){
		goodInfo.clear();
	}
	//显示购物车的所有商品,在从数据库取得购物车商品信息的同时计算购物车商品的总价，节约数据库连接资源
	public OrderInfos showAllGoods() throws Exception{
		//每次显示购物车商品均刷新总价信息
		this.totalPrice=0.0f;
		OrderInfos orderItemInfos = new OrderInfos();
		if(this.size()!=0){
			Set<String> goodIds=goodInfo.keySet();
			Iterator it=goodIds.iterator();
			String sql="select * from goods where id in ";
			
			//获得所有商品的id 并拼接sql字符串
			StringBuffer sb=new StringBuffer();
			sb.append("(");
			while(it.hasNext()){
				String id=(String) it.next();
				//判断是不是最后一件商品的goodId
				if(it.hasNext()){
					sb.append(id+",");
				}else{
					sb.append(id);
				}
			}
			sb.append(")");
			sql=sql+sb.toString();
			System.out.println(sql);
			Connection conn=JDBCUtil.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql);
			ResultSet res=stmt.executeQuery();
			float totalMoney=0;
			while(res.next()){
				int goodId=res.getInt(1);
				String name=res.getString(2);
				String pic=res.getString(7);
				float price =res.getFloat(8);
				int num=Integer.parseInt(getNumById(goodId+""));
				OrderItemInfo orderItemInfo = new OrderItemInfo(goodId,name,price,0,pic,num);
				orderItemInfos.orderItems.add(orderItemInfo);
				totalMoney+=num*price;
				
			}
			if(isPackages){
				totalMoney*=discount;//是套餐则打折
			}
			orderItemInfos.totalMoney=totalMoney;//订单的总价赋值
			orderItemInfos.orderNum=getOrderNum();
			orderItemInfos.isPackages=isPackages;
		}else{
			orderItemInfos=null;
		}
		return orderItemInfos;
	}
	
	public int size(){
		return this.goodInfo.size();
	}

}
