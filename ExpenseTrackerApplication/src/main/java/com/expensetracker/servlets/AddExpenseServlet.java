package com.expensetracker.servlets;

import java.io.IOException;

import com.expensetracker.repository.dao.ExpenseTrackerDao;
import com.expensetracker.repository.dto.Expense;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddExpenseServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double amount = Double.parseDouble(request.getParameter("amount"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
//		System.out.println(request.getParameter("categoryId"));
		String description = request.getParameter("description");
        String date = request.getParameter("date");
        Expense e = new Expense(0,amount, date, categoryId, description);
        new ExpenseTrackerDao().addExpense(e);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        String json = new Gson().toJson(e);
        response.getWriter().write(json);
	}
}
