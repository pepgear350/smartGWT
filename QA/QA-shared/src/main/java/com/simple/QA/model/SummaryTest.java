package com.simple.QA.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SummaryTest implements Serializable {

	
	private String name_app;
	private String name_version;
	private String name_test;
	private String result;
	private Double duration_test_inMin;
	private String name_user;

	public SummaryTest() {
	}

	public SummaryTest(String name_app, String name_version, String name_test, String result, Double duration_test_inMin,
			String name_user) {
		super();
		this.name_app = name_app;
		this.name_version = name_version;
		this.name_test = name_test;
		this.result = result;
		this.duration_test_inMin = duration_test_inMin;
		this.name_user = name_user;
	}

	public String getName_app() {
		return name_app;
	}

	public void setName_app(String name_app) {
		this.name_app = name_app;
	}

	public String getName_version() {
		return name_version;
	}

	public void setName_version(String name_version) {
		this.name_version = name_version;
	}

	public String getName_test() {
		return name_test;
	}

	public void setName_test(String name_test) {
		this.name_test = name_test;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Double getDuration_test_inMin() {
		return duration_test_inMin;
	}

	public void setDuration_test_inMin(Double duration_test_inMin) {
		this.duration_test_inMin = duration_test_inMin;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	
}
