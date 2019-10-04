package com.simple.QA.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VersionApp implements Serializable {

	private String name_app;
	private String name_version;
	private int id;
	

	public VersionApp() {
	}


	public VersionApp(String name_app, String name_version, int id) {
		super();
		this.name_app = name_app;
		this.name_version = name_version;
		this.id = id;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	

	

}
