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
import com.qlrj.wiserestaurant.dao.GoodDao;
import com.qlrj.wiserestaurant.domain.Good;
import com.qlrj.wiserestaurant.domain.GoodListInfo;

@WebServlet("/GetVagetableListServlet")
public class GetVagetableListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");//根据type的类型到数据库中取指定类型的菜品
		System.out.println("type"+type);
		GoodDao goodDao = new GoodDao();
		List<Good> goodList=null;
		List<Good> goodTopList=null;
		GoodListInfo goodListInfo = new GoodListInfo();
		try {
			goodList = goodDao.getGoodListByType(type);
			goodTopList = goodDao.getGoodTopList(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		goodListInfo.topGoodinfos=goodTopList;
		goodListInfo.goodinfos=goodList;
		
		String json = new Gson().toJson(goodListInfo);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(json.getBytes("utf-8"));
		System.out.println(json);
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
