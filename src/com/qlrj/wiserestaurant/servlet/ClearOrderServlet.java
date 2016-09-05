package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlrj.wiserestaurant.dao.OrderDao;


@WebServlet("/ClearOrderServlet")
public class ClearOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNum = request.getParameter("orderNum");
		System.out.println(orderNum+"清除订单信息");
		OrderDao order=(OrderDao) request.getServletContext().getAttribute(orderNum);
		order.setGoodInfo(new HashMap<String ,String>());
		System.out.println(order.getGoodInfo());
		
		response.getOutputStream().write("0".getBytes());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
