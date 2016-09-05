package com.qlrj.wiserestaurant.domain;

public class Good {
	
	private int id;
	private String name;
	private int type;
	private int category;
	private int isNew;
	private String detail;
	private String pic;
	private float  price;
	private String detailPics;
	
	public String getDetailPics() {
		return detailPics;
	}
	public void setDetailPics(String detailPics) {
		this.detailPics = detailPics;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Good(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	public Good() {
	}
	public Good(int id,String name, int type, int category, int isNew, float price,String pic,String detail,String detailPics) {
		super();
		this.id=id;
		this.name = name;
		this.type = type;
		this.category = category;
		this.isNew = isNew;
		this.price = price;
		this.pic=pic;
		this.detail=detail;
		this.detailPics=detailPics;
	}
	
	
	

}
