package com.simple.QA.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.simple.QA.handler.CallbackDB;
import com.simple.QA.model.App;
import com.simple.QA.model.Avg_userTime;
import com.simple.QA.model.FailTestByApp;
import com.simple.QA.model.SummaryTest;
import com.simple.QA.model.User;
import com.simple.QA.model.VersionApp;
import com.simple.QA.view.login.LoginView;
import com.simple.QA.view.userAdmin.AppView;
import com.simple.QA.view.userAdmin.MetricsTestView;
import com.simple.QA.view.userAdmin.MetricsUserView;
import com.simple.QA.view.userAdmin.SummaryTestView;
import com.simple.QA.view.userAdmin.UserAdminView;
import com.simple.QA.view.userAdmin.UserView;
import com.simple.QA.view.userAdmin.VersionView;
import com.simple.QA.view.userQA.UserQAView;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class QAcontroller {

	private CallbackDB callbackDB;
	private LoginView loginView;
	private UserQAView userQAView;
	private AppView appView;
	private VersionView versionView;
	private SummaryTestView summaryTestView;
	private MetricsTestView metricsTestView;
	private MetricsUserView metricsUserView;
	private UserView userView;
	private UserAdminView userAdminView;

	public QAcontroller() {
		this.callbackDB = new CallbackDB(this);
		this.loginView = new LoginView(this);
		this.userQAView = new UserQAView(this);
		this.appView = new AppView(this);
		this.versionView = new VersionView(this);
		this.summaryTestView = new SummaryTestView(this);
		this.metricsTestView = new MetricsTestView(this);
		this.metricsUserView = new MetricsUserView(this);
		this.userView = new UserView(this);
		this.userAdminView = new UserAdminView(this, appView, versionView, summaryTestView, metricsTestView,
				metricsUserView, userView);

	}

	public DynamicForm getLoginView() {
		return loginView.getLoginView();
	}

	public void checkUser(String name_user, String password) {
		callbackDB.logIn(name_user, password);
	}

	public void chooseView(User user) {
		Window window = new Window();
		window.setWidth100();
		window.setHeight100();

		if (user.getId_role() == 1) {
			window.setTitle("Usuario QA");
			userQAView.setId_user(user.getId());
			window.addItem(userQAView.getUserQAView());
			window.show();
		}

		if (user.getId_role() == 2) {
			window.setTitle("Usuario Administrador");
			window.addItem(userAdminView.getUserAdminView());
			window.show();
		}
	}

	public void findTitleMetrics() {
		callbackDB.findTitleMetrics();
	}

	public void setTitleMetrics(ArrayList<String> titleMestrics) {
		userQAView.setTitleMetrcis(titleMestrics);
	}

	public void saveTest(int id_user, int id_version, String name_test, String result, long start_test, long end_test,
			int metric_A, int metric_B, int metric_C) {
		callbackDB.saveTest(id_user, id_version, name_test, result, start_test, end_test, metric_A, metric_B, metric_C);
	}

	public void setValueselectAppItem(LinkedHashMap<Integer, String> valueApp, String view) {
		switch (view) {
		case "userQAView":
			userQAView.setValueselectAppItem(valueApp);
			break;

		case "versionView":
			versionView.setValueselectAppItem(valueApp);
			break;

		default:
			break;
		}
	}

	public void setValueselectVersionItem(LinkedHashMap<Integer, String> valueVersion) {
		userQAView.setValueselectVersionItem(valueVersion);
	}

	public void getValueselectAppItem(String view) {
		callbackDB.findAllApp(view);
	}

	public void getValueselectVersionItem(int id_app) {
		callbackDB.findVersionByApp(id_app);
	}

	public void findAllAppInList() {
		callbackDB.findAllAppInList();
	}

	public void setfindAllAppInList(ArrayList<App> valueApp) {
		RecordList grid = new RecordList();

		for (App app : valueApp) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id_app", app.getId());
			record.setAttribute("nameApp", app.getName_app());
			grid.add(record);
		}
		appView.setFindAllAppInList(grid);
	}

	public void insertApp(String name_app) {
		callbackDB.insertApp(name_app);
	}

	public void updateApp(String name_app, int id_app) {
		callbackDB.updateApp(name_app, id_app);
	}

	public void deleteApp(int id_app) {
		callbackDB.deleteApp(id_app);
	}

	public void findAllUserInList() {
		callbackDB.findAllUser();
	}

	public void setFindAllUserInList(ArrayList<User> valueUser) {
		RecordList grid = new RecordList();

		for (User user : valueUser) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id_user", user.getId());
			record.setAttribute("nameUser", user.getName_user());
			record.setAttribute("typeRole", user.getType_role());
			grid.add(record);
		}
		userView.setFindAllUserInList(grid);
	}

	public void insertUser(String name_user, String password, int id_role) {
		callbackDB.insertUser(name_user, password, id_role);
	}

	public void updateUser(String name_user, String password, int id_role, int id_user) {
		callbackDB.updateUser(name_user, password, id_role, id_user);
	}

	public void deleteUser(int id_user) {
		callbackDB.deleteUser(id_user);
	}

	public void findAllVersionInList() {
		callbackDB.findAllVersionInList();
	}

	public void setFindAllVersionInList(ArrayList<VersionApp> valueVersion) {
		RecordList grid = new RecordList();

		for (VersionApp version : valueVersion) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id_version", version.getId());
			record.setAttribute("nameApp", version.getName_app());
			record.setAttribute("nameVersion", version.getName_version());
			grid.add(record);
		}
		versionView.setFindAllVersionInList(grid);
	}

	public void insertVersion(int id_app, String name_version) {
		callbackDB.insertVersion(id_app, name_version);
	}

	public void updateVersion(String name_version, int id_version) {
		callbackDB.updateVersion(name_version, id_version);
	}

	public void deleteVersion(int id_version) {
		callbackDB.deleteVersion(id_version);
	}

	public void findMetricsTest() {
		callbackDB.findMetricsTest();
	}

	public void setFindMetricsTest(ArrayList<FailTestByApp> values) {
		RecordList grid = new RecordList();

		for (FailTestByApp failTestByApp : values) {

			ListGridRecord record = new ListGridRecord();

			record.setAttribute("name_app", failTestByApp.getName_app());
			record.setAttribute("name_version", failTestByApp.getName_version());
			record.setAttribute("Total_Tests", failTestByApp.getTotal_Tests());
			record.setAttribute("usability_failures_percent", failTestByApp.getUsability_failures_percent());
			record.setAttribute("app_failures_percent", failTestByApp.getApp_failures_percent());
			record.setAttribute("sum_failures", failTestByApp.getSum_failures());
			grid.add(record);
		}
		metricsTestView.setFindMetricsTest(grid);
	}

	public void findMetricsUser() {
		callbackDB.findMetricsUser();
	}

	public void setFindMetricsUser(ArrayList<Avg_userTime> values) {
		RecordList grid = new RecordList();

		for (Avg_userTime avg_userTime : values) {

			ListGridRecord record = new ListGridRecord();

			record.setAttribute("name_user", avg_userTime.getName_user());
			record.setAttribute("avg_duration_testInMin", avg_userTime.getAvg_duration_testInMin());
			grid.add(record);
		}
		metricsUserView.setFindMetricsUser(grid);
	}

	public void findSummaryTest() {
		callbackDB.findSummaryTest();
	}

	public void setFindSummaryTest(ArrayList<SummaryTest> values) {
		RecordList grid = new RecordList();

		for (SummaryTest summaryTest : values) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("name_app", summaryTest.getName_app());
			record.setAttribute("name_version", summaryTest.getName_version());
			record.setAttribute("name_test", summaryTest.getName_test());
			record.setAttribute("result", summaryTest.getResult());
			record.setAttribute("duration_test_inMin", summaryTest.getDuration_test_inMin());
			record.setAttribute("name_user", summaryTest.getName_user());

			grid.add(record);
		}
		summaryTestView.setFindSummaryTest(grid);
	}

	public synchronized void insertSuccessful(String table) {

		switch (table) {
		case "test":
			userQAView.hideFormCycleTest();
			break;

		case "app":
			callbackDB.findAllAppInList();
			enableSaveApp();
			break;

		case "version":
			callbackDB.findAllVersionInList();
			enableSaveVersion();
			break;

		case "user":
			callbackDB.findAllUser();
			enableSaveUser();
			break;

		default:
			break;
		}

		showMessaje("Guardado Exitoso");
	}

	public synchronized void updateSuccessful(String table) {

		switch (table) {

		case "app":
			callbackDB.findAllAppInList();
			enableSaveApp();
			break;

		case "version":
			callbackDB.findAllVersionInList();
			enableSaveVersion();
			break;

		case "user":
			callbackDB.findAllUser();
			enableSaveUser();
			break;

		default:
			break;
		}

		showMessaje("Actualizado Exitoso");
	}

	public synchronized void deleteSuccessful(String table) {

		switch (table) {

		case "app":
			callbackDB.findAllAppInList();
			enableSaveApp();
			break;

		case "version":
			callbackDB.findAllVersionInList();
			enableSaveVersion();
			break;

		case "user":
			callbackDB.findAllUser();
			enableSaveUser();
			break;

		default:
			break;
		}

		showMessaje("Borrado Exitoso");
	}

	public void enableSaveApp() {
		appView.enableSaveApp();
	}

	public void enableSaveVersion() {
		versionView.enableSaveVersion();
	}

	public void enableSaveUser() {
		userView.enableSaveUser();
	}

	public void showMessaje(String message) {
		SC.say(message);
	}

}
