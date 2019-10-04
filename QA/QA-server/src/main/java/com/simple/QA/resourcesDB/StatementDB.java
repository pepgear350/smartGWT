package com.simple.QA.resourcesDB;

public class StatementDB {

	public static String findAllApp() {
		String query = "SELECT * FROM App";
		return query;
	}

	public static String insertApp(String name_app) {
		String query = "INSERT INTO App VALUES ('%s')";
		return String.format(query, name_app);
	}

	public static String updateApp(String name_app, int id_app) {
		String query = "UPDATE App  SET name_app = '%s' WHERE id = %d";
		return String.format(query, name_app, id_app);
	}

	public static String deleteApp(int id_app) {
		String query = "DELETE FROM App WHERE id = %d";
		return String.format(query, id_app);
	}

	public static String findAllVersion() {
		String query = "SELECT  App.name_app, Version.name_version, Version.id FROM App INNER JOIN\r\n"
				+ "Version ON App.id = Version.id_app";
		return query;
	}

	public static String findVersionByApp(int id_app) {
		String query = "SELECT  Version.id , Version.name_version FROM Version WHERE id_app = %d";
		return String.format(query, id_app);
	}

	public static String insertVersion(int id_app, String name_version) {
		String query = "EXEC SaveVersion @id_app = %d , @name_version = '%s';";
		return String.format(query, id_app, name_version);
	}

	public static String updateVersion(String name_version, int id_version) {
		String query = "UPDATE Version  SET name_version = '%s' WHERE id = %d";
		return String.format(query, name_version, id_version);
	}

	public static String deleteVersion(int id_version) {
		String query = "DELETE FROM Version WHERE id = %d";
		return String.format(query, id_version);
	}

	public static String findAllUser() {
		String query = "SELECT UserData.id, UserData.name_user, RoleUser.type_role  FROM UserData\r\n"
				+ "INNER JOIN RoleUser ON UserData.id_role = RoleUser.id";
		return query;
	}

	public static String insertUser(String name_user, String password, int id_role) {
		String query = "INSERT INTO UserData VALUES ('%s', '%s', %d)";
		return String.format(query, name_user, password, id_role);
	}

	public static String updateUser(String name_user, String password, int id_role, int id_user) {
		String query = "UPDATE UserData  SET  name_user = '%s', password = '%s', id_role = %d WHERE id = %d";
		return String.format(query, name_user, password, id_role, id_user);
	}

	public static String deleteUser(int id_user) {
		String query = "DELETE FROM UserData WHERE id = %d";
		return String.format(query, id_user);
	}

	public static String logIn(String name_user, String password) {
		String query = "SELECT UserData.id, UserData.id_role FROM UserData WHERE name_user = '%s' AND password ='%s'";
		return String.format(query, name_user, password);
	}

	public static String saveTest(int id_user, int id_version, String name_test, String result, long start_test,
			long end_test, int metric_A, int metric_B, int metric_C) {
		String query = "EXEC SaveTest " + "@id_user = %d," + "@id_version = %d," + "@name_test = '%s',"
				+ "@result = '%s'," + "@start_test = '%d'," + "@end_test = '%d'," + "@metric_A = %d,"
				+ "@metric_B = %d," + "@metric_C = %d;";
		return String.format(query, id_user, id_version, name_test, result, start_test, end_test, metric_A, metric_B,
				metric_C);
	}

	public static String findTitleMetrics() {
		String query = "SELECT * FROM MetricsData";
		return query;
	}
	
	
	public static String findMetricsTest() {
		String query = "SELECT * FROM FailTestByApp";
		return query;
	}
	
	public static String findMetricsUser() {
		String query = "SELECT * FROM Avg_userTime";
		return query;
	}
	
	public static String findSummaryTest() {
		String query = "SELECT * FROM SummaryTest";
		return query;
	}
	
	

}
