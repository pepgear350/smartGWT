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
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class VersionView {

	private QAcontroller qAcontroller;
	private DynamicForm formAddVersion;
	protected int id_version;
	private ListGrid versionGrid;

	public VersionView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public HLayout getVersionView() {
		HLayout layout = new HLayout();
		formAddVersion = getFormAddVersion();
		layout.setWidth100();
		layout.addMember(getListGrid());
		layout.addMember(formAddVersion);
		return layout;
	}

	private ListGrid getListGrid() {

		versionGrid = new ListGrid(){
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
							int id_version = record.getAttributeAsInt("id_version");
							formAddVersion.getField("buttonSaveVersion").disable();
							qAcontroller.deleteVersion(id_version);
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
							id_version = record.getAttributeAsInt("id_version");
							formAddVersion.getField("buttonCancelEditVersion").show();
							formAddVersion.setValue("nameVersion", record.getAttribute("nameVersion"));
						}
					});
					return buttonEdit;
				} else {
					return null;
				}

			}
		};
		versionGrid.setWidth(700);
		versionGrid.setHeight(400);

		versionGrid.setVirtualScrolling(false);
		versionGrid.setShowRecordComponents(true);
		versionGrid.setShowRecordComponentsByCell(true);

		ListGridField nameVersion = new ListGridField("nameVersion", "Nombre Versión");
		ListGridField nameApp = new ListGridField("nameApp", "Nombre Aplicación");

		ListGridField buttonDelete = new ListGridField("buttonDelete", "-", 80);
		buttonDelete.setAlign(Alignment.CENTER);
		ListGridField buttonEdit = new ListGridField("buttonEdit", "-", 80);
		buttonEdit.setAlign(Alignment.CENTER);

		versionGrid.setFields( nameVersion, nameApp,buttonDelete,buttonEdit);
		versionGrid.setCanResizeFields(true);
		qAcontroller.findAllVersionInList();
				
		return versionGrid;
	}

	private DynamicForm getFormAddVersion() {

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Cree y edite Versiones de Aplicación");
		form.setIsGroup(true);
		form.setWidth(350);
		form.setHeight(200);

		TextItem nameVersion = new TextItem();
		nameVersion.setRequired(true);
		nameVersion.setName("nameVersion");
		nameVersion.setTitle("Nombre de Versión");
		nameVersion.setWidth("*");

		ComboBoxItem selectAppItem = new ComboBoxItem();
		selectAppItem.setName("selectAppItem");
		selectAppItem.setTitle("Seleccione una Aplicación");
		selectAppItem.setShowTitle(true);
		selectAppItem.setRequired(true);
		selectAppItem.setWidth("*");

		ButtonItem buttonSaveVersion = new ButtonItem();
		buttonSaveVersion.setName("buttonSaveVersion");
		buttonSaveVersion.setTitle("Guardar");
		buttonSaveVersion.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				if (form.validate(false)) {
					String name_version = form.getValueAsString("nameVersion");
					int id_app = Integer.valueOf(form.getValueAsString("selectAppItem"));
					buttonSaveVersion.disable();

					if (id_version == 0) {
						qAcontroller.insertVersion(id_app, name_version);
					} else {
						qAcontroller.updateVersion(name_version, id_version);
					}

				} else {
					qAcontroller.showMessaje("Llene todos los campos");
				}
			}
		});

		ButtonItem buttonCancelEditVersion = new ButtonItem();
		buttonCancelEditVersion.setName("buttonCancelEditVersion");
		buttonCancelEditVersion.setTitle("Cancelar Edición");
		buttonCancelEditVersion.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				formAddVersion.getField("buttonCancelEditVersion").hide();
				nameVersion.setValue("");
				id_version = 0;
			}
		});

		buttonCancelEditVersion.hide();
		form.setFields(selectAppItem,nameVersion, buttonSaveVersion, buttonCancelEditVersion);
		qAcontroller.getValueselectAppItem("versionView");
		return form;
	}

	public void setFindAllVersionInList(RecordList grid) {
		versionGrid.setData(grid);
	}

	public void setValueselectAppItem(LinkedHashMap<Integer, String> valueMap) {
		formAddVersion.getField("selectAppItem").setValueMap(valueMap);
	}

	public void enableSaveVersion() {
		id_version = 0;
		formAddVersion.setValue("nameVersion", "");
		formAddVersion.getField("buttonSaveVersion").enable();
		formAddVersion.getField("buttonCancelEditVersion").hide();
	}

}
