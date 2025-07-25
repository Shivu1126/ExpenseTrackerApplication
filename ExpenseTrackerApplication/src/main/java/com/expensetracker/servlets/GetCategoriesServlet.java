package com.expensetracker.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.expensetracker.repository.dao.ExpenseTrackerDao;
import com.expensetracker.repository.db.DBConnection;
import com.expensetracker.repository.dto.Category;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCategoriesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categories = new ExpenseTrackerDao().getCategories();
		String json = new Gson().toJson(categories);
//	            System.out.println(json);
		resp.setContentType("application/json");
		resp.getWriter().write(json);
	}
}
