package com.expensetracker.servlets;

import java.io.IOException;
import java.util.List;

import com.expensetracker.repository.dao.ExpenseTrackerDao;
import com.expensetracker.repository.dto.Expense;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetExpensesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Expense> list = new ExpenseTrackerDao().getExpenses();
        String json = new Gson().toJson(list);
//        System.out.println(json);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
	}
}
