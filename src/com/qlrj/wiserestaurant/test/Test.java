package com.qlrj.wiserestaurant.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.dao.FinishedOrderDao;
import com.qlrj.wiserestaurant.dao.GoodDao;
import com.qlrj.wiserestaurant.dao.PackageDao;
import com.qlrj.wiserestaurant.domain.Good;
import com.qlrj.wiserestaurant.domain.Packages;

public class Test {
	
	
	public static void main(String[] args) throws Exception {
		List<Good> packagesGoods = new GoodDao().getPackagesGoods(new String[]{"1","2","3"});
		String json = new Gson().toJson(packagesGoods);
		System.out.println(json);
	}
	
}
