package com.simple.QA;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.simple.QA.model.App;
import com.simple.QA.model.Avg_userTime;
import com.simple.QA.model.FailTestByApp;
import com.simple.QA.model.SummaryTest;
import com.simple.QA.model.User;
import com.simple.QA.model.VersionApp;

@RemoteServiceRelativePath("QAService")
public interface QAService extends RemoteService {

	LinkedHashMap<Integer, String> findAllApp() throws QA_SQLException;

	ArrayList<App> findAllAppInList() throws QA_SQLException;

	String insertApp(String name_app) throws QA_SQLException;

	String updateApp(String name_app, int id_app) throws QA_SQLException;

	String deleteApp(int id_app) throws QA_SQLException;

	LinkedHashMap<Integer, String> findVersionByApp(int id_app) throws QA_SQLException;

	ArrayList<VersionApp> findAllVersionInList() throws QA_SQLException;

	String insertVersion(int id_app, String name_version) throws QA_SQLException;

	String updateVersion(String name_version, int id_version) throws QA_SQLException;

	String deleteVersion(int id_version) throws QA_SQLException;

	ArrayList<User> findAllUser() throws QA_SQLException;

	String insertUser(String name_user, String password, int id_role) throws QA_SQLException;

	String updateUser(String name_user, String password, int id_role, int id_user) throws QA_SQLException;

	String deleteUser(int id_user) throws QA_SQLException;

	User logIn(String name_user, String password) throws QA_SQLException;

	String saveTest(int id_user, int id_version, String name_test, String result, long start_test, long end_test,
			int metric_A, int metric_B, int metric_C) throws QA_SQLException;

	ArrayList<String> findTitleMetrics() throws QA_SQLException;

	ArrayList<FailTestByApp> findMetricsTest() throws QA_SQLException;

	ArrayList<Avg_userTime> findMetricsUser() throws QA_SQLException;

	ArrayList<SummaryTest> findSummaryTest() throws QA_SQLException;

}
