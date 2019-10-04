package com.simple.QA.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FailTestByApp implements Serializable {

	private String name_app;
	private String name_version;
	private int total_Tests;
	private int Usability_failures_percent;
	private int App_failures_percent;
	private int Sum_failures;

	public FailTestByApp() {
		
	}

	public FailTestByApp(String name_app, String name_version, int total_Tests, int usability_failures_percent,
			int app_failures_percent, int sum_failures) {
		super();
		this.name_app = name_app;
		this.name_version = name_version;
		this.total_Tests = total_Tests;
		Usability_failures_percent = usability_failures_percent;
		App_failures_percent = app_failures_percent;
		Sum_failures = sum_failures;
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

	public int getTotal_Tests() {
		return total_Tests;
	}

	public void setTotal_Tests(int total_Tests) {
		this.total_Tests = total_Tests;
	}

	public int getUsability_failures_percent() {
		return Usability_failures_percent;
	}

	public void setUsability_failures_percent(int usability_failures_percent) {
		Usability_failures_percent = usability_failures_percent;
	}

	public int getApp_failures_percent() {
		return App_failures_percent;
	}

	public void setApp_failures_percent(int app_failures_percent) {
		App_failures_percent = app_failures_percent;
	}

	public int getSum_failures() {
		return Sum_failures;
	}

	public void setSum_failures(int sum_failures) {
		Sum_failures = sum_failures;
	}

		

}
