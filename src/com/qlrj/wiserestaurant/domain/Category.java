package com.qlrj.wiserestaurant.domain;

import java.util.List;

public class Category {
	
	public int id;
	public String name;
	public List<Type> types;
	
	
	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", types=" + types + "]";
	}


	

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}




	public Category() {
		// TODO Auto-generated constructor stub
	}
	

}
