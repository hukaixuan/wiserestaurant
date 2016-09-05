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

@WebServlet("/GetPackagesGoodsServlet")
public class GetPackagesGoodsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodIds=request.getParameter("goodIds");
		String[] ids = goodIds.split("_");
		try {
			List<Good> packagesGoods = new GoodDao().getPackagesGoods(ids);
			String json = new Gson().toJson(packagesGoods);
			System.out.println(json);
			response.getOutputStream().write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
