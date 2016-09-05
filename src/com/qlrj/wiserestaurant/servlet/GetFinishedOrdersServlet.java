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
import com.qlrj.wiserestaurant.dao.FinishedOrderDao;
import com.qlrj.wiserestaurant.domain.OrderInfos;

@WebServlet("/GetFinishedOrdersServlet")
public class GetFinishedOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<OrderInfos> orderList = new FinishedOrderDao().getOrderList();
			String json = new Gson().toJson(orderList);
			System.out.println(json+"从数据库获取的数据");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(json.getBytes("utf-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
