package com.qlrj.wiserestaurant.domain;

public class OrderItemInfo{
	public int goodId;
	public String name;
	public float price;
	public int status;
	public String pic;
	public int num;
	public OrderItemInfo(int goodId, String name, float price, int status, String pic, int num) {
		super();
		this.goodId = goodId;
		this.name = name;
		this.price = price;
		this.status = status;
		this.pic = pic;
		this.num = num;
	}
	@Override
	public String toString() {
		return "OrderItemInfo [goodId=" + goodId + ", name=" + name + ", price=" + price + ", status=" + status
				+ ", pic=" + pic + ", num=" + num + "]";
	}
	
	
}
