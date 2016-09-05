package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.domain.ConfiremdOrderList;
import com.qlrj.wiserestaurant.domain.OrderInfos;

@WebServlet("/GetConfirmedOrders")
public class GetConfirmedOrders extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//在application域中存放用户已经确定的订单列表
		ServletContext servletContext = request.getServletContext();
		Map<String ,OrderInfos> ordersInfo= (Map<String ,OrderInfos>) servletContext.getAttribute("ordersInfo");
		if(ordersInfo==null){
			ordersInfo=new HashMap<String ,OrderInfos>();
			servletContext.setAttribute("ordersInfo", ordersInfo);
		}
		
		
		Set<String> orderNums = ordersInfo.keySet();
		if(orderNums.size()!=0){//已经有订单信息
			ConfiremdOrderList confiredOrderList = new ConfiremdOrderList();
			for (String orderNum : orderNums) {
				OrderInfos orderInfos = ordersInfo.get(orderNum);
				confiredOrderList.confirmedOrderList.add(orderInfos);
			}
			
			Gson gson = new Gson();
			String json = gson.toJson(confiredOrderList);
			System.out.println("confiredOrderList"+json);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(json.getBytes("utf-8"));
			outputStream.flush();
		}else{//没有订单信息
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write("0".getBytes("utf-8"));//0表示还没有订单
			outputStream.flush();
			
		}
		
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
