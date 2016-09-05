package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.dao.CategoryDao;
import com.qlrj.wiserestaurant.dao.OrderDao;
import com.qlrj.wiserestaurant.dao.TypeDao;
import com.qlrj.wiserestaurant.domain.Category;
import com.qlrj.wiserestaurant.domain.Type;
import com.qlrj.wiserestaurant.domain.TypeInfo;

@WebServlet("/GetTypeServlet")
public class GetTypeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		TypeDao typeDao = new TypeDao();
		CategoryDao categoryDao = new CategoryDao();
		List<Type> vagetableTypes=null;
		List<Category> categories=null;
		try {
			vagetableTypes = typeDao.getVagetableTypes();
			categories=categoryDao.getGoodTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TypeInfo typeInfo = new TypeInfo();
		typeInfo.categories=categories;
		Gson gson = new Gson();
		String json = gson.toJson(typeInfo);
		System.out.println(json);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(json.getBytes("utf-8"));
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
