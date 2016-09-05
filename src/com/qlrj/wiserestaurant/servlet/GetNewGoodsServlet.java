package com.qlrj.wiserestaurant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qlrj.wiserestaurant.dao.GoodDao;
import com.qlrj.wiserestaurant.domain.Good;

@WebServlet("/GetNewGoodsServlet")
public class GetNewGoodsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Good> newGoods = new GoodDao().getNewGoods();
			String json = new Gson().toJson(newGoods);
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
