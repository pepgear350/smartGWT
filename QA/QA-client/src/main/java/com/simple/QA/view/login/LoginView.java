package com.simple.QA.view.login;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class LoginView {

	private QAcontroller qAcontroller;

	public LoginView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public DynamicForm getLoginView() {

		final DynamicForm form = new DynamicForm();

		HeaderItem header = new HeaderItem();
		header.setDefaultValue("Ingrese usuario y contraseña");

		TextItem nameUser = new TextItem();
		nameUser.setName("nameUser");
		nameUser.setTitle("Usuario");
		nameUser.setWrapTitle(false);
		nameUser.setRequired(true);
		nameUser.setLength(20);

		PasswordItem passwordUser = new PasswordItem();
		passwordUser.setName("passwordUser");
		passwordUser.setTitle("Contraseña");
		passwordUser.setWrapTitle(false);
		passwordUser.setRequired(true);
		passwordUser.setLength(20);

		ButtonItem buttonLogin = new ButtonItem();
		buttonLogin.setTitle("Ingresar");
		buttonLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate(false)) {
					String name_user = form.getValueAsString("nameUser");
					String password = form.getValueAsString("passwordUser");
					qAcontroller.checkUser(name_user, password);
				}
			}
		});

		form.setFields(header, nameUser, passwordUser, buttonLogin);

		form.setValue("nameUser", "admin");
		form.setValue("passwordUser", "12345");

		return form;
	}

}
