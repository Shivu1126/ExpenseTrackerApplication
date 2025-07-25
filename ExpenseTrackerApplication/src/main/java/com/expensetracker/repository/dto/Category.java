package com.expensetracker.repository.dto;

public class Category {
	private int categoryId;
    private String categoryName;

    // constructor, getters
    public Category(int id, String name) {
        this.categoryId = id;
        this.categoryName = name;
    }

    public int getId() { return categoryId; }
    public String getName() { return categoryName; }
}
