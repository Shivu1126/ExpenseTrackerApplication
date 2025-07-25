package com.expensetracker.repository.dto;

public class Expense {
	private int expenseId;
	private double amount;
	private String date;
	private int categoryId;
	private String categoryName;
	private String description;

	public Expense(int expenseId, double amount, String date, int categoryId, String description) {
		this.expenseId = expenseId;
		this.amount = amount;
		this.date = date;
		this.categoryId = categoryId;
		this.description = description;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
}
