package com.simple.QA.view.userAdmin;

import java.util.LinkedHashMap;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class UserView {

	private QAcontroller qAcontroller;
	private DynamicForm formAddUser;
	protected int id_user;
	private ListGrid userGrid;

	public UserView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public HLayout getUserView() {
		HLayout layout = new HLayout();
		formAddUser = getFormAddUser();
		setValueselectRoleItem();
		layout.setWidth100();
		layout.addMember(getListGrid());
		layout.addMember(formAddUser);
		return layout;
	}

	private ListGrid getListGrid() {

		userGrid = new ListGrid() {
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {

				String fieldName = this.getFieldName(colNum);

				if (fieldName.equals("buttonDelete")) {
					IButton buttonDelete = new IButton();
					buttonDelete.setHeight(26);
					buttonDelete.setWidth(70);
					buttonDelete.setIcon("[SKIN]/actions/cancel.png");
					buttonDelete.setTitle("Eliminar");
					buttonDelete.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							int id_user = record.getAttributeAsInt("id_user");
							formAddUser.getField("buttonSaveUser").disable();
							qAcontroller.deleteUser(id_user);
						}
					});
					return buttonDelete;
				}
				if (fieldName.equals("buttonEdit")) {
					IButton buttonEdit = new IButton();
					buttonEdit.setHeight(26);
					buttonEdit.setWidth(70);
					buttonEdit.setIcon("[SKIN]/actions/edit.png");
					buttonEdit.setTitle("Editar");
					buttonEdit.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							id_user = record.getAttributeAsInt("id_user");
							formAddUser.getField("buttonCancelEditUser").show();
							formAddUser.setValue("nameUser", record.getAttribute("nameUser"));
						}
					});
					return buttonEdit;
				} else {
					return null;
				}

			}
		};
		userGrid.setWidth(600);
		userGrid.setHeight(400);

		userGrid.setVirtualScrolling(false);
		userGrid.setShowRecordComponents(true);
		userGrid.setShowRecordComponentsByCell(true);

		ListGridField nameUser = new ListGridField("nameUser", "Nombre Usuario");
		ListGridField typeRole = new ListGridField("typeRole", "Tipo de rol");

		ListGridField buttonDelete = new ListGridField("buttonDelete", "-", 80);
		buttonDelete.setAlign(Alignment.CENTER);
		ListGridField buttonEdit = new ListGridField("buttonEdit", "-", 80);
		buttonEdit.setAlign(Alignment.CENTER);

		userGrid.setFields(nameUser, typeRole, buttonDelete, buttonEdit);
		userGrid.setCanResizeFields(true);
		qAcontroller.findAllUserInList();
		return userGrid;
	}

	private DynamicForm getFormAddUser() {

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Cree y edite Usuarios");
		form.setIsGroup(true);
		form.setWidth(350);
		form.setHeight(200);

		TextItem nameUser = new TextItem();
		nameUser.setRequired(true);
		nameUser.setName("nameUser");
		nameUser.setTitle("Nombre de Usuario");
		nameUser.setWidth(200);

		ComboBoxItem selectRoleItem = new ComboBoxItem();
		selectRoleItem.setName("rol");
		selectRoleItem.setTitle("Seleccione Rol");
		selectRoleItem.setShowTitle(true);
		selectRoleItem.setRequired(true);
		selectRoleItem.setWidth(200);

		PasswordItem passwordItem = new PasswordItem();
		passwordItem.setName("password");
		passwordItem.setTitle("Escriba una contraseña");
		passwordItem.setLength(20);

		PasswordItem passwordAgain = new PasswordItem();
		passwordAgain.setName("passwordAgain");
		passwordAgain.setTitle("Repita la contraseña");
		passwordAgain.setWrapTitle(false);
		passwordAgain.setRequired(true);
		passwordAgain.setLength(20);

		MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
		matchesValidator.setOtherField("password");
		matchesValidator.setErrorMessage("Las contraseñas deben ser iguales");
		passwordAgain.setValidators(matchesValidator);

		ButtonItem buttonSaveUser = new ButtonItem();
		buttonSaveUser.setName("buttonSaveUser");
		buttonSaveUser.setTitle("Guardar");
		buttonSaveUser.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				if (form.validate(false)) {
					String name_user = nameUser.getValueAsString();
					String password = passwordAgain.getValueAsString();
					int id_role = Integer.valueOf(selectRoleItem.getValueAsString());
					buttonSaveUser.disable();

					if (id_user == 0) {
						qAcontroller.insertUser(name_user, password, id_role);
					} else {
						qAcontroller.updateUser(name_user, password, id_role, id_user);
					}

				} else {
					qAcontroller.showMessaje("Llene todos los campos");
				}
			}
		});

		ButtonItem buttonCancelEditUser = new ButtonItem();
		buttonCancelEditUser.setName("buttonCancelEditUser");
		buttonCancelEditUser.setTitle("Cancelar Edición");
		buttonCancelEditUser.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				formAddUser.getField("buttonCancelEditUser").hide();
				nameUser.setValue("");
				id_user = 0;
			}
		});

		buttonCancelEditUser.hide();
		form.setFields(nameUser, selectRoleItem, passwordItem, passwordAgain, buttonSaveUser, buttonCancelEditUser);
		return form;
	}

	public void setFindAllUserInList(RecordList grid) {
		userGrid.setData(grid);
	}

	private void setValueselectRoleItem() {
		LinkedHashMap<Integer, String> valueRole = new LinkedHashMap<Integer, String>();
		valueRole.put(1, "Usuario QA");
		valueRole.put(2, "Usuario Admin");
		formAddUser.getField("rol").setValueMap(valueRole);
	}

	public void enableSaveUser() {
		id_user = 0;
		formAddUser.setValue("nameUser", "");
		formAddUser.setValue("password", "");
		formAddUser.setValue("passwordAgain", "");
		formAddUser.getField("buttonSaveUser").enable();
		formAddUser.getField("buttonCancelEditUser").hide();
	}

}
