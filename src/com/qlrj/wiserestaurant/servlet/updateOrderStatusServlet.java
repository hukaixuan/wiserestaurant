package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.domain.OrderInfos;

@WebServlet("/updateOrderStatusServlet")
public class updateOrderStatusServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("向客户端更新了订单信息");
		String orderNum = request.getParameter("orderNum");
		ServletContext servletContext = request.getServletContext();
		Map<String ,OrderInfos> ordersInfo= (Map<String ,OrderInfos>) servletContext.getAttribute("ordersInfo");
		OrderInfos orderInfos = ordersInfo.get(orderNum);
		String json = new Gson().toJson(orderInfos);
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
