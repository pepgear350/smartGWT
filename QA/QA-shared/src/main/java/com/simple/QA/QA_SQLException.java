package com.simple.QA;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QA_SQLException extends IllegalArgumentException implements Serializable{

	private String message;

	public QA_SQLException() {
	}

	public QA_SQLException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
