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
import com.qlrj.wiserestaurant.dao.FinishedOrderDao;
import com.qlrj.wiserestaurant.domain.OrderInfos;

@WebServlet("/FinishOrderServlet")
public class FinishOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNum = request.getParameter("orderNum");
		ServletContext servletContext = request.getServletContext();
		Map<String ,OrderInfos> ordersInfo= (Map<String ,OrderInfos>) servletContext.getAttribute("ordersInfo");
		OrderInfos orderInfos = ordersInfo.get(orderNum);
		String json = new Gson().toJson(orderInfos);
		System.out.println("更新完成订单的数据"+json);
		//存到数据库中
		try {
			new FinishedOrderDao().addFinishedOrder(json);
		} catch (Exception e) {
			System.out.println("插入完成订单失败");
			e.printStackTrace();
		}
		//清除存放在application中的订单数据
		ordersInfo.remove(orderNum);
		request.getServletContext().removeAttribute(orderNum);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
