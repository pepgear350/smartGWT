package com.simple.QA.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Avg_userTime implements Serializable {

	private String name_user;
	private Double Avg_duration_testInMin;

	public Avg_userTime() {
	}

	public Avg_userTime(String name_user, Double avg_duration_testInMin) {
		super();
		this.name_user = name_user;
		Avg_duration_testInMin = avg_duration_testInMin;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public Double getAvg_duration_testInMin() {
		return Avg_duration_testInMin;
	}

	public void setAvg_duration_testInMin(Double avg_duration_testInMin) {
		Avg_duration_testInMin = avg_duration_testInMin;
	}

	

}
