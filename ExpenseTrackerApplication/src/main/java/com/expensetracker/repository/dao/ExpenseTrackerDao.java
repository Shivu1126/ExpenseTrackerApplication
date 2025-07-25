package com.expensetracker.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.expensetracker.repository.db.DBConnection;
import com.expensetracker.repository.dto.Category;
import com.expensetracker.repository.dto.Expense;
import com.expensetracker.repository.dto.ResponseData;

public class ExpenseTrackerDao {
	public boolean addExpense(Expense expense) {
		try (Connection connection = DBConnection.getConnection()) {
			String sqlInsert = "INSERT INTO expenses (amount, date, categoryId, description) VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sqlInsert);
			stmt.setDouble(1, expense.getAmount());
			stmt.setString(2, expense.getDate());
			stmt.setInt(3, expense.getCategoryId());
			stmt.setString(4, expense.getDescription());
			int rows = stmt.executeUpdate();
			stmt.close();
			connection.close();
			return rows > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Expense> getExpenses() {
		try (Connection connection = DBConnection.getConnection()) {
			List<Expense> expenseList = new ArrayList<>();
//			String sqlGetQuery = "SELECT * FROM expenses";
			String sqlGetQuery = "select ex.expenseId, ex.amount, ex.date, ex.categoryId, ex.description, ca.categoryName "
					+ "from expenses ex join categories ca on ex.categoryId = ca.categoryId;";
			PreparedStatement stmt = connection.prepareStatement(sqlGetQuery);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Expense expense = new Expense(rs.getInt("expenseId"), rs.getDouble("amount"), rs.getString("date"),
						rs.getInt("categoryId"), rs.getString("description"));
				expense.setCategoryName(rs.getString("categoryName"));
				expenseList.add(expense);
			}
//            System.out.println(expenseList);
			rs.close();
			stmt.close();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			expenseList.sort(Comparator.comparing(exp -> LocalDate.parse(exp.getDate(), formatter)));

			return expenseList;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public ResponseData addCategory(String categoryName) {
		try (Connection connection = DBConnection.getConnection()) {
			String sqlQuery = "insert into categories (categoryName) values(?)";

			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, categoryName);
			statement.executeUpdate();
			statement.close();
			return new ResponseData(true, "Added successfully");
		} catch (SQLIntegrityConstraintViolationException sql) {
			//if it has duplicate value
			return new ResponseData(false, "Duplicate Entry");
		} catch (Exception e) {
			return new ResponseData(false, "Server Error !");
		}
	}

	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM categories")) {
			while (rs.next()) {
				categories.add(new Category(rs.getInt("categoryId"), rs.getString("categoryName")));
			}
			return categories;
		} catch (Exception e) {
			return categories;
		}
	}
	
	public ResponseData deleteExpense(int expenseId) {
		try (Connection con = DBConnection.getConnection()) {
			String sqlQuery = "DELETE FROM expenses WHERE expenseId = ? ";
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			statement.setInt(1, expenseId);
			int rows = statement.executeUpdate();
			statement.close();
			if(rows>0)
				return new ResponseData(true, "Deleted Successfully..");
			else
				return new ResponseData(false, "Invalid id..");
		}
		catch (Exception e) {
			return new ResponseData(false, "Deletion Failed");
		}
	}
}
