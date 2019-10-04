package com.simple.QA;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.simple.QA.controller.QAcontroller;


public class MainQA implements EntryPoint {

	private final QAcontroller qAcontroller = new QAcontroller();

	@Override
	public void onModuleLoad() {
		RootPanel.get("LoginViewHtml").add(qAcontroller.getLoginView());
	}

}
