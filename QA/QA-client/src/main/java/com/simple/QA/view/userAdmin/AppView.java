package com.simple.QA.view.userAdmin;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class AppView {

	private QAcontroller qAcontroller;
	private DynamicForm formAddApp;
	private ListGrid appGrid;
	private int id_app;

	public AppView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public HLayout getAppView() {
		HLayout layout = new HLayout();
		formAddApp = getFormAddApp();
		layout.setWidth100();
		layout.addMember(getListGrid());
		layout.addMember(formAddApp);
		return layout;
	}

	public ListGrid getListGrid() {

		appGrid = new ListGrid() {
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
							int id_app = record.getAttributeAsInt("id_app");
							formAddApp.getField("buttonSaveApp").disable();
							qAcontroller.deleteApp(id_app);
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
							id_app = record.getAttributeAsInt("id_app");
							formAddApp.getField("buttonCancelEditApp").show();
							formAddApp.setValue("nameApp", record.getAttribute("nameApp"));
						}
					});
					return buttonEdit;
				} else {
					return null;
				}

			}
		};
		appGrid.setWidth(600);
		appGrid.setHeight(400);

		appGrid.setVirtualScrolling(false);
		appGrid.setShowRecordComponents(true);
		appGrid.setShowRecordComponentsByCell(true);

		ListGridField nameApp = new ListGridField("nameApp", "Nombre Aplicación");
		ListGridField buttonDelete = new ListGridField("buttonDelete", "-",80);
		buttonDelete.setAlign(Alignment.CENTER);
		ListGridField buttonEdit = new ListGridField("buttonEdit", "-",80);
		buttonEdit.setAlign(Alignment.CENTER);

		appGrid.setFields(nameApp, buttonDelete, buttonEdit);
		appGrid.setCanResizeFields(true);
		qAcontroller.findAllAppInList();
		return appGrid;
	}

	private DynamicForm getFormAddApp() {

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Cree y edite Aplicaciones");
		form.setIsGroup(true);
		form.setWidth(350);
		form.setHeight(100);

		TextItem nameApp = new TextItem();
		nameApp.setRequired(true);
		nameApp.setName("nameApp");
		nameApp.setTitle("Nombre de Aplicación");
		nameApp.setWidth("*");

		ButtonItem buttonSaveApp = new ButtonItem();
		buttonSaveApp.setName("buttonSaveApp");
		buttonSaveApp.setTitle("Guardar");
		buttonSaveApp.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				if (form.validate(false)) {
					String name_app = form.getValueAsString("nameApp");
					buttonSaveApp.disable();
					
					if(id_app == 0) {
						qAcontroller.insertApp(name_app);
					}else {
						qAcontroller.updateApp(name_app, id_app);
					}

				} else {
					qAcontroller.showMessaje("Llene todos los campos");
				}
			}
		});

		ButtonItem buttonCancelEditApp = new ButtonItem();
		buttonCancelEditApp.setName("buttonCancelEditApp");
		buttonCancelEditApp.setTitle("Cancelar Edición");
		buttonCancelEditApp.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				formAddApp.getField("buttonCancelEditApp").hide();
				nameApp.setValue("");
				id_app = 0;
			}
		});

		buttonCancelEditApp.hide();
		form.setFields(nameApp, buttonSaveApp, buttonCancelEditApp);

		return form;
	}

	public void setFindAllAppInList(RecordList grid) {
		appGrid.setData(grid);
	}

	public void enableSaveApp() {
		id_app = 0;
		formAddApp.setValue("nameApp", "");
		formAddApp.getField("buttonSaveApp").enable();
		formAddApp.getField("buttonCancelEditApp").hide();
	}
}
