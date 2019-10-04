package com.simple.QA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.simple.QA.model.App;
import com.simple.QA.model.Avg_userTime;
import com.simple.QA.model.FailTestByApp;
import com.simple.QA.model.SummaryTest;
import com.simple.QA.model.User;
import com.simple.QA.model.VersionApp;
import com.simple.QA.resourcesDB.ConnectionDB;
import com.simple.QA.resourcesDB.StatementDB;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class QAServiceImpl extends RemoteServiceServlet implements QAService {

	public LinkedHashMap<Integer, String> findAllApp() throws IllegalArgumentException {
		ResultSet resultSet = null;
		LinkedHashMap<Integer, String> valueApp = new LinkedHashMap<Integer, String>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findAllApp());

			while (resultSet.next()) {
				valueApp.put(resultSet.getInt(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		
		return valueApp;

	}

	public ArrayList<App> findAllAppInList() throws QA_SQLException {
		ResultSet resultSet = null;
		ArrayList<App> valueApp = new ArrayList<App>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findAllApp());

			while (resultSet.next()) {
				valueApp.add(new App(resultSet.getInt(1), resultSet.getString(2)));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

				return valueApp;

	}

	public String insertApp(String name_app) throws QA_SQLException {

		
		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(StatementDB.insertApp(name_app),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public String updateApp(String name_app, int id_app) throws QA_SQLException {
		
		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(StatementDB.updateApp(name_app, id_app),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public String deleteApp(int id_app) throws QA_SQLException {
		

		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(StatementDB.deleteApp(id_app),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public LinkedHashMap<Integer, String> findVersionByApp(int id_app) throws QA_SQLException {
		ResultSet resultSet = null;
		LinkedHashMap<Integer, String> valueVersion = new LinkedHashMap<Integer, String>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findVersionByApp(id_app));

			while (resultSet.next()) {
				valueVersion.put(resultSet.getInt(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		
		return valueVersion;
	}

	public ArrayList<VersionApp> findAllVersionInList() throws QA_SQLException {
		ResultSet resultSet = null;
		ArrayList<VersionApp> value = new ArrayList<VersionApp>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findAllVersion());

			while (resultSet.next()) {

				String name_app = resultSet.getString("name_app");
				String name_version = resultSet.getString("name_version");
				int id = resultSet.getInt("id");
				value.add(new VersionApp(name_app, name_version, id));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		

		return value;

	}

	public String insertVersion(int id_app, String name_version) throws QA_SQLException {
		ResultSet resultSet = null;

		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(
						StatementDB.insertVersion(id_app, name_version), Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

			resultSet = prepsInsert.getGeneratedKeys();

			while (resultSet.next()) {
				isOk = resultSet.getString(1);
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public String updateVersion(String name_version, int id_version) throws QA_SQLException {
		ResultSet resultSet = null;

		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(
						StatementDB.updateVersion(name_version, id_version), Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

			resultSet = prepsInsert.getGeneratedKeys();

			while (resultSet.next()) {
				isOk = resultSet.getString(1);
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public String deleteVersion(int id_version) throws QA_SQLException {
		
		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(StatementDB.deleteVersion(id_version),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public ArrayList<User> findAllUser() throws QA_SQLException {
		ResultSet resultSet = null;
		ArrayList<User> valueApp = new ArrayList<User>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findAllUser());

			while (resultSet.next()) {
				valueApp.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		
		return valueApp;
	}

	public String insertUser(String name_user, String password, int id_role) throws QA_SQLException {
		
		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(
						StatementDB.insertUser(name_user, password, id_role), Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public String updateUser(String name_user, String password, int id_role, int id_user) throws QA_SQLException {
		
		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(
						StatementDB.updateUser(name_user, password, id_role, id_user),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public String deleteUser(int id_user) throws QA_SQLException {
		
		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(StatementDB.deleteUser(id_user),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public User logIn(String name_user, String password) throws QA_SQLException {
		ResultSet resultSet = null;
		User user = new User();

		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.logIn(name_user, password));

			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setId_role(resultSet.getInt(2));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		if (user.getId_role() == 0)
			throw new QA_SQLException("Datos incorrectos");

		return user;
	}

	public String saveTest(int id_user, int id_version, String name_test, String result, long start_test, long end_test,
			int metric_A, int metric_B, int metric_C) throws QA_SQLException {

		String isOk = null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				PreparedStatement prepsInsert = connection.prepareStatement(StatementDB.saveTest(id_user, id_version,
						name_test, result, start_test, end_test, metric_A, metric_B, metric_C),
						Statement.RETURN_GENERATED_KEYS);) {

			prepsInsert.execute();

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		return isOk;
	}

	public ArrayList<String> findTitleMetrics() {
		ResultSet resultSet = null;
		ArrayList<String> titleMetrics = new ArrayList<String>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findTitleMetrics());

			while (resultSet.next()) {
				titleMetrics.add(resultSet.getString(2));
			}
		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		
		return titleMetrics;
	}

	public ArrayList<FailTestByApp> findMetricsTest() throws QA_SQLException {
		ResultSet resultSet = null;
		ArrayList<FailTestByApp> list = new ArrayList<FailTestByApp>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findMetricsTest());

			while (resultSet.next()) {
				FailTestByApp failTestByApp = new FailTestByApp(resultSet.getString(1), resultSet.getString(2),
						resultSet.getInt(3), resultSet.getInt(4),resultSet.getInt(5), resultSet.getInt(6));
				list.add(failTestByApp);
			}

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		
		return list;

	}

	public ArrayList<Avg_userTime> findMetricsUser() throws QA_SQLException {

		ResultSet resultSet = null;
		ArrayList<Avg_userTime> list = new ArrayList<Avg_userTime>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findMetricsUser());

			while (resultSet.next()) {
				Avg_userTime avg_userTime = new Avg_userTime(resultSet.getString(1), resultSet.getDouble(2));
				list.add(avg_userTime);
			}

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

		
		return list;
	}

	public ArrayList<SummaryTest> findSummaryTest() throws QA_SQLException {

		ResultSet resultSet = null;
		ArrayList<SummaryTest> list = new ArrayList<SummaryTest>();
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			throw new QA_SQLException("Error conexión JDBC");
		}

		try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrlDB());
				Statement statement = connection.createStatement();) {

			resultSet = statement.executeQuery(StatementDB.findSummaryTest());

			while (resultSet.next()) {
				SummaryTest summaryTest = new SummaryTest(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6));
				list.add(summaryTest);
			}

		} catch (SQLException e) {
			throw new QA_SQLException(e.getMessage());
		}

	
		return list;
	}

}
