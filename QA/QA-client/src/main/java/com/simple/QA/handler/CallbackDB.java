package com.simple.QA.handler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.simple.QA.QAService;
import com.simple.QA.QAServiceAsync;
import com.simple.QA.QA_SQLException;
import com.simple.QA.controller.QAcontroller;
import com.simple.QA.model.App;
import com.simple.QA.model.Avg_userTime;
import com.simple.QA.model.FailTestByApp;
import com.simple.QA.model.SummaryTest;
import com.simple.QA.model.User;
import com.simple.QA.model.VersionApp;

public class CallbackDB {

	private QAcontroller qAcontroller;
	private final QAServiceAsync qaServiceAsync = GWT.create(QAService.class);

	public CallbackDB(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public void findAllApp(final String view) {
		qaServiceAsync.findAllApp(new AsyncCallback<LinkedHashMap<Integer, String>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(LinkedHashMap<Integer, String> valueApp) {
				qAcontroller.setValueselectAppItem(valueApp, view);
			}
		});
	}

	public void findAllAppInList() {
		qaServiceAsync.findAllAppInList(new AsyncCallback<ArrayList<App>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());

			}

			@Override
			public void onSuccess(ArrayList<App> valueApp) {
				qAcontroller.setfindAllAppInList(valueApp);
			}
		});

	}

	public void insertApp(String name_app) {
		qaServiceAsync.insertApp(name_app, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al guardar :\n" + caught.getMessage());
				qAcontroller.enableSaveApp();
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.insertSuccessful("app");
			}
		});

	}

	public void updateApp(String name_app, int id_app) {
		qaServiceAsync.updateApp(name_app, id_app, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al actualizar :\n" + caught.getMessage());
				qAcontroller.enableSaveApp();
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.updateSuccessful("app");
				qAcontroller.enableSaveApp();
			}
		});
	}

	public void deleteApp(int id_app) {
		qaServiceAsync.deleteApp(id_app, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al Borrar :\n" + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.deleteSuccessful("app");
			}
		});
	}

	public void findVersionByApp(int id_app) {
		qaServiceAsync.findVersionByApp(id_app, new AsyncCallback<LinkedHashMap<Integer, String>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(LinkedHashMap<Integer, String> valueVersion) {
				qAcontroller.setValueselectVersionItem(valueVersion);
			}
		});
	}

	public void findAllVersionInList() {
		qaServiceAsync.findAllVersionInList(new AsyncCallback<ArrayList<VersionApp>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());

			}

			@Override
			public void onSuccess(ArrayList<VersionApp> valueVersion) {
				qAcontroller.setFindAllVersionInList(valueVersion);
			}
		});

	}

	public void insertVersion(int id_app, String name_version) {
		qaServiceAsync.insertVersion(id_app, name_version, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al guardar :\n" + caught.getMessage());
				qAcontroller.enableSaveVersion();
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.insertSuccessful("version");
			}
		});
	}

	public void updateVersion(String name_version, int id_version) {
		qaServiceAsync.updateVersion(name_version, id_version, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al actualizar :\n" + caught.getMessage());
				qAcontroller.enableSaveVersion();
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.updateSuccessful("version");
				qAcontroller.enableSaveVersion();
			}
		});
	}

	public void deleteVersion(int id_version) {
		qaServiceAsync.deleteVersion(id_version, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al Borrar :\n" + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.deleteSuccessful("version");
			}
		});
	}

	public void findAllUser() {
		qaServiceAsync.findAllUser(new AsyncCallback<ArrayList<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());

			}

			@Override
			public void onSuccess(ArrayList<User> valueApp) {
				qAcontroller.setFindAllUserInList(valueApp);
			}
		});
	}

	public void insertUser(String name_user, String password, int id_role) {
		qaServiceAsync.insertUser(name_user, password, id_role, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al guardar :\n" + caught.getMessage());
				qAcontroller.enableSaveUser();
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.insertSuccessful("user");
			}
		});
	}

	public void updateUser(String name_user, String password, int id_role, int id_user) {
		qaServiceAsync.updateUser(name_user, password, id_role, id_user, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al actualizar :\n" + caught.getMessage());
				qAcontroller.enableSaveUser();
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.updateSuccessful("user");
				qAcontroller.enableSaveUser();
			}
		});
	}

	public void deleteUser(int id_user) {
		qaServiceAsync.deleteUser(id_user, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje("Error al Borrar :\n" + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				qAcontroller.deleteSuccessful("user");
			}
		});
	}

	public void logIn(String name_user, String password) {
		qaServiceAsync.logIn(name_user, password, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(User user) {
				qAcontroller.chooseView(user);
			}
		});
	}

	public void findTitleMetrics() {
		qaServiceAsync.findTitleMetrics(new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<String> titleMetrics) {
				qAcontroller.setTitleMetrics(titleMetrics);
			}
		});
	}

	public void saveTest(int id_user, int id_version, String name_test, String result, long start_test, long end_test,
			int metric_A, int metric_B, int metric_C) {
		qaServiceAsync.saveTest(id_user, id_version, name_test, result, start_test, end_test, metric_A, metric_B,
				metric_C, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						qAcontroller.showMessaje(caught.getMessage());
					}

					@Override
					public void onSuccess(String result) {
						qAcontroller.insertSuccessful("test");
					}
				});
	}

	public void findMetricsTest() throws QA_SQLException {

		qaServiceAsync.findMetricsTest(new AsyncCallback<ArrayList<FailTestByApp>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<FailTestByApp> values) {
				qAcontroller.setFindMetricsTest(values);
			}
		});
	}

	public void findMetricsUser() throws QA_SQLException {

		qaServiceAsync.findMetricsUser(new AsyncCallback<ArrayList<Avg_userTime>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<Avg_userTime> values) {
				qAcontroller.setFindMetricsUser(values);
			}
		});
	}

	public void findSummaryTest() throws QA_SQLException {
		qaServiceAsync.findSummaryTest(new AsyncCallback<ArrayList<SummaryTest>>() {

			@Override
			public void onFailure(Throwable caught) {
				qAcontroller.showMessaje(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<SummaryTest> values) {
				qAcontroller.setFindSummaryTest(values);
			}
		});
	}

}
