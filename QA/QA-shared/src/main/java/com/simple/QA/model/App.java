package com.simple.QA.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class App implements Serializable {

	private int id;
	private String name_app;

	public App() {
	}

	public App(int id, String name_app) {
		this.id = id;
		this.name_app = name_app;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_app() {
		return name_app;
	}

	public void setName_app(String name_app) {
		this.name_app = name_app;
	}

}
