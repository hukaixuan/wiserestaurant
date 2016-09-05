package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlrj.wiserestaurant.dao.OrderDao;
import com.qlrj.wiserestaurant.dao.PackageDao;
import com.qlrj.wiserestaurant.domain.Packages;

@WebServlet("/PackagesJoinOrderServlet")
public class PackagesJoinOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNum = request.getParameter("orderNum");
		String packagesId = request.getParameter("packagesId");
		try {
			Packages packages = new PackageDao().getPackagesById(packagesId);
			OrderDao myOrder=(OrderDao)request.getServletContext().getAttribute(orderNum);
			String[] ids = packages.goodIds.split("_");
			if(myOrder==null){
				myOrder=new OrderDao();
				myOrder.setOrderNum(orderNum);
				request.getServletContext().setAttribute(orderNum, myOrder);
			}
			myOrder.isPackages=true;
			myOrder.discount=packages.discount;
			for (String id : ids) {
				myOrder.addGood(id, "1");
			}
			
			System.out.println(((OrderDao)request.getServletContext().getAttribute(orderNum)).isPackages);
			System.out.println(((OrderDao)request.getServletContext().getAttribute(orderNum)).discount);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
