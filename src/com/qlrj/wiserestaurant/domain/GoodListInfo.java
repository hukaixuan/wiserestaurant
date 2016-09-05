package com.qlrj.wiserestaurant.domain;

import java.util.List;

public class GoodListInfo {
	
	public List<Good> goodinfos;
	public List<Good> topGoodinfos;
	public String moreUrl;
	
	public class GoodInfo{
		public int id;
		public String name;
		public int type;
		public int category;
		public int isNew;
		public String detail;
		public String pic;
		public String detailPics;
		
		
	}

}
