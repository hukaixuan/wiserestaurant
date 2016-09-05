package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.dao.OrderDao;
import com.qlrj.wiserestaurant.domain.OrderInfos;

@WebServlet("/ShowOrderInfoServlet")
public class ShowOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNum = request.getParameter("orderNum");
		
		System.out.println("orderNum"+orderNum);
		if(!"".equals(orderNum)){//在有订单号的情况下
			OrderDao myOrder=(OrderDao)request.getServletContext().getAttribute(orderNum);
			if(myOrder!=null){
				OrderInfos orderItemInfos = null;
				try {
					orderItemInfos = myOrder.showAllGoods();
					//将已提交的订单信息传递到客户端
					ServletContext servletContext = request.getServletContext();
					Map<String ,OrderInfos> ordersInfo= (Map<String ,OrderInfos>) servletContext.getAttribute("ordersInfo");
					if(ordersInfo!=null){
						OrderInfos orderInfos = ordersInfo.get(orderNum);
						if(orderInfos!=null){
							orderItemInfos.hasPay=orderInfos.hasPay;
							orderItemInfos.commitTime=orderInfos.commitTime;
							orderItemInfos.hasCommited=orderInfos.hasCommited;
							orderItemInfos.orderNum=orderInfos.orderNum;
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ServletOutputStream outputStream = response.getOutputStream();
				if(orderItemInfos==null){
					System.out.println("没有订单信息");
					outputStream.write("0".getBytes());
				}else{
					Gson gson = new Gson();
					String json = gson.toJson(orderItemInfos);
					System.out.println(json+"*************************");
					outputStream.write(json.getBytes("utf-8"));
				}

				
			}else{
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("0".getBytes());//返回0则表示还没有创建订单对象。
				outputStream.flush();
			}
		}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
