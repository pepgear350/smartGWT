package com.simple.QA.resourcesDB;

public class ConnectionDB {

	public static String getUrlDB() {
		StringBuilder url = new StringBuilder();
		url.append("jdbc:sqlserver://localhost:1433;");
		url.append("database=QA;");
		url.append("user=sa;"); //Ingrese su usuario sq_server
		url.append("password=123456789;");//Ingrese su contraseña sq_server
		return url.toString();
	}

}
