package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlrj.wiserestaurant.dao.GoodDao;
import com.qlrj.wiserestaurant.dao.OrderDao;
import com.qlrj.wiserestaurant.domain.OrderInfos;
import com.qlrj.wiserestaurant.domain.OrderItemInfo;

@WebServlet("/JoinOrderServlet")
public class JoinOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("调用了joinOrderServlet");
		request.setCharacterEncoding("utf-8");
		String goodId = request.getParameter("goodId");
		String orderNum = request.getParameter("orderNum");
		
//		OrderDao myOrder = BaseServlet.orderDaos.get(orderNum);
		OrderDao myOrder=(OrderDao)request.getServletContext().getAttribute(orderNum);//将操作订单的到保存到application域中
		if(myOrder!=null){
			if(myOrder.getKeySet().contains(goodId)){//如果商品已经存在，则数量加1 
				System.out.println("菜品数量加1");
				String goodNUm=(Integer.parseInt(myOrder.getNumById(goodId))+1)+"";
				myOrder.addGood(goodId, goodNUm);
			}else{
				myOrder.addGood(goodId, "1");//该菜品首次加入
			}
		}else{
			myOrder=new OrderDao();
			myOrder.setOrderNum(orderNum);
			myOrder.addGood(goodId, "1");//第一次添加
			request.getServletContext().setAttribute(orderNum, myOrder);
			System.out.println("第一次创建order并且放到了application中");
		}
		System.out.println(myOrder);
		//如果是添加到已经提交了的订单中，则要跟新application中的orderedList 中的数据
		//TODO
		Map<String ,OrderInfos> ordersInfo= (Map<String ,OrderInfos>)request.getServletContext().getAttribute("ordersInfo");
		
		if(ordersInfo!=null){
			
			OrderInfos orderInfos = ordersInfo.get(orderNum);
			if(orderInfos!=null){
				ArrayList<OrderItemInfo> orderItems = orderInfos.orderItems;
				boolean isNewGood=true;
				for (OrderItemInfo orderItemInfo : orderItems) {
					if((orderItemInfo.goodId+"").equals(goodId)){
						//数量加一
						orderItemInfo.num++;
						orderInfos.totalMoney+=orderItemInfo.price;
						isNewGood=false;
						break;
					}
					
				}
				
				if(isNewGood){
					try {
						OrderItemInfo orderItem = new GoodDao().getOrderItemByGoodId(goodId);
						orderItems.add(orderItem);
						orderInfos.totalMoney+=orderItem.price;//更新订单的总价
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
