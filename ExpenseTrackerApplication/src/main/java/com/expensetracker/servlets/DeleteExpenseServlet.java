package com.expensetracker.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;

import com.expensetracker.repository.dao.ExpenseTrackerDao;
import com.expensetracker.repository.dto.ResponseData;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteExpenseServlet extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		JSONObject json = new JSONObject(sb.toString());
		int expenseId = json.getInt("expenseId");

		ResponseData responseData = new ExpenseTrackerDao().deleteExpense(expenseId);
		response.setContentType("application/json");
		if (responseData.isSuccess())
			response.setStatus(HttpServletResponse.SC_OK);
		else
			response.setStatus(HttpServletResponse.SC_CONFLICT);

		response.getWriter().write(new Gson().toJson(responseData));
	}
}
