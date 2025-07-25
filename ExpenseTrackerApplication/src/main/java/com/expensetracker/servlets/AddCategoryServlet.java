package com.expensetracker.servlets;

import java.io.IOException;

import com.expensetracker.repository.dao.ExpenseTrackerDao;
import com.expensetracker.repository.dto.ResponseData;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCategoryServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName");
        response.setContentType("application/json");
        String json;
		if(new ExpenseTrackerDao().addCategory(categoryName).isSuccess()) {
			response.setStatus(HttpServletResponse.SC_OK);
	        json = new Gson().toJson(new ResponseData(true, "Category added successfully..!"));
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			json = new Gson().toJson(new ResponseData(false, "Failed to added..!"));
		}
		response.getWriter().write(json);
	}
}
