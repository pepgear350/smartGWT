package com.simple.QA;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.simple.QA.model.User;

public class QATest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.simple.QA.module";
	}

	public void testLogInQA() {

		QAServiceAsync qaServiceAsync = GWT.create(QAService.class);

		ServiceDefTarget target = (ServiceDefTarget) qaServiceAsync;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "app/QAService");

		delayTestFinish(10000);

		String name_user = "test";
		String password = "12345";
		qaServiceAsync.logIn(name_user, password, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				fail("Failed load =  " + caught.getMessage());
			}

			@Override
			public void onSuccess(User result) {
				assertEquals(1, result.getId_role());

				finishTest();
			}
		});
	}

	public void testLogInAdmin() {

		QAServiceAsync qaServiceAsync = GWT.create(QAService.class);
		ServiceDefTarget target = (ServiceDefTarget) qaServiceAsync;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "app/QAService");

		delayTestFinish(10000);

		String name_user = "admin";
		String password = "12345";
		qaServiceAsync.logIn(name_user, password, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				fail("Failed load =  " + caught.getMessage());
			}

			@Override
			public void onSuccess(User result) {
				assertEquals(2, result.getId_role());
				finishTest();
			}
		});
	}

}
