package com.qlrj.wiserestaurant.domain;

public class Packages {
	public  String id;
	public String goodIds;
	public String name;
	public String detail;
	public String pic;
	public float discount;
	public Packages(String id,String goodIds, String name, String detail,String pic,float discount) {
		super();
		this.id=id;
		this.goodIds = goodIds;
		this.name = name;
		this.detail = detail;
		this.pic=pic;
		this.discount=discount;
	}
	public Packages() {
		// TODO Auto-generated constructor stub
	}

}
