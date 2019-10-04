package com.simple.QA;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.simple.QA.model.App;
import com.simple.QA.model.Avg_userTime;
import com.simple.QA.model.FailTestByApp;
import com.simple.QA.model.SummaryTest;
import com.simple.QA.model.User;
import com.simple.QA.model.VersionApp;

public interface QAServiceAsync {

	void findAllApp(AsyncCallback<LinkedHashMap<Integer, String>> callback) throws QA_SQLException;

	void findAllAppInList(AsyncCallback<ArrayList<App>> callback) throws QA_SQLException;

	void insertApp(String name_app, AsyncCallback<String> callback) throws QA_SQLException;

	void updateApp(String name_app, int id_app, AsyncCallback<String> callback) throws QA_SQLException;

	void deleteApp(int id_app, AsyncCallback<String> callback) throws QA_SQLException;

	void findVersionByApp(int id_app, AsyncCallback<LinkedHashMap<Integer, String>> callback) throws QA_SQLException;

	void findAllVersionInList(AsyncCallback<ArrayList<VersionApp>> callback) throws QA_SQLException;

	void insertVersion(int id_app, String name_version, AsyncCallback<String> callback) throws QA_SQLException;

	void updateVersion(String name_version, int id_version, AsyncCallback<String> callback) throws QA_SQLException;

	void deleteVersion(int id_version, AsyncCallback<String> callback) throws QA_SQLException;

	void findAllUser(AsyncCallback<ArrayList<User>> callback) throws QA_SQLException;

	void insertUser(String name_user, String password, int id_role, AsyncCallback<String> callback)
			throws QA_SQLException;

	void updateUser(String name_user, String password, int id_role, int id_user, AsyncCallback<String> callback)
			throws QA_SQLException;

	void deleteUser(int id_user, AsyncCallback<String> callback) throws QA_SQLException;

	void logIn(String name_user, String password, AsyncCallback<User> callback) throws QA_SQLException;

	void saveTest(int id_user, int id_version, String name_test, String result, long start_test, long end_test,
			int metric_A, int metric_B, int metric_C, AsyncCallback<String> callback) throws QA_SQLException;

	void findTitleMetrics(AsyncCallback<ArrayList<String>> callback) throws QA_SQLException;
	
	void findMetricsTest(AsyncCallback<ArrayList<FailTestByApp>> callback) throws QA_SQLException;

	void findMetricsUser(AsyncCallback<ArrayList<Avg_userTime>> callback) throws QA_SQLException;

	void findSummaryTest(AsyncCallback<ArrayList<SummaryTest>> callback) throws QA_SQLException;

}
