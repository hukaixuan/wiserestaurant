package com.qlrj.wiserestaurant.domain;

public class Type {
	
	public int id;
	public String name;
	public String url="/GetVagetableListServlet";//默认的url ;
	public Type(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
