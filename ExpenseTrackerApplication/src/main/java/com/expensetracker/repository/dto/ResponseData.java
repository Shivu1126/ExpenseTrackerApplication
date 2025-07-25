package com.expensetracker.repository.dto;

public class ResponseData {
	private final boolean success;
	private final String message;

	public ResponseData(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

}
