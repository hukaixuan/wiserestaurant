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
import com.qlrj.wiserestaurant.dao.PackageDao;
import com.qlrj.wiserestaurant.domain.Packages;

@WebServlet("/GetPackagesListServlet")
public class GetPackagesListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Packages> packageList = new PackageDao().getPackageList();
			String json = new Gson().toJson(packageList);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(json.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
