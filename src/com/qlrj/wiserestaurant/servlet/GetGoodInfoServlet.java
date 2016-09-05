package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.dao.GoodDao;
import com.qlrj.wiserestaurant.domain.Good;

@WebServlet("/GetGoodInfoServlet")
public class GetGoodInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Good goodInfo =null;
		try {
			goodInfo= new GoodDao().getGoodById(22+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ServletOutputStream outputStream = response.getOutputStream();
		Gson gson = new Gson();
		String goodStr = gson.toJson(goodInfo);
		System.out.println(goodStr);
		outputStream.write(goodStr.getBytes("utf-8"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
