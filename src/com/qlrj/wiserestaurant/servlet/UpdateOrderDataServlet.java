package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.dao.OrderDao;
import com.qlrj.wiserestaurant.domain.OrderItemInfo;
import com.qlrj.wiserestaurant.domain.OrderInfos;

@WebServlet("/UpdateOrderDataServlet")
public class UpdateOrderDataServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String updateData=request.getParameter("updateData");
		String isConfirm=request.getParameter("isConfirm");//0 是没有提交的，1 是提交了的订单
		String orderNum = request.getParameter("orderNum");//提交订单的订单号
		
		OrderInfos updateOrderItemInfo = new Gson().fromJson(updateData, OrderInfos.class);
		OrderDao orderDao = new OrderDao();
		orderDao.setOrderNum(orderNum);
		if(updateOrderItemInfo!=null){
			HashMap<String, String> goodInfo = orderDao.getGoodInfo();
			
			for (OrderItemInfo orderItem : updateOrderItemInfo.orderItems) {
				String goodId = orderItem.goodId+"";
				String num = orderItem.num+"";
				goodInfo.put(goodId, num);
			}
			
			request.getServletContext().setAttribute(orderNum, orderDao);//更新该订单号的orderDao的信息
		}
		
		if("1".equals(isConfirm)){//如果是用户已经确定的订单
			
			//在application域中存放用户已经确定的订单列表
			ServletContext servletContext = request.getServletContext();
			Map<String ,OrderInfos> ordersInfo= (Map<String ,OrderInfos>) servletContext.getAttribute("ordersInfo");
			if(ordersInfo==null){
				ordersInfo=new HashMap<String ,OrderInfos>();
				servletContext.setAttribute("ordersInfo", ordersInfo);
			}
			//保存客户端已经提交的订单完成的进度
			OrderInfos orderInfos = ordersInfo.get(orderNum);
			if(orderInfos!=null){
				
				for (OrderItemInfo orderInfo : orderInfos.orderItems) {
					if(orderInfo.status==1){
						for (OrderItemInfo newOrderInfos : updateOrderItemInfo.orderItems) {
							if(newOrderInfos.goodId==orderInfo.goodId){
								newOrderInfos.status=1;
							}
						}
					}
					
				}
				
			}
			
			ordersInfo.put(orderNum, updateOrderItemInfo);//用户已经确定的订单信息保存在application域中
			System.out.println(orderNum+"用户已经确定的订单信息保存在application域中");
			System.out.println("订单信息"+updateOrderItemInfo.toString());
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
