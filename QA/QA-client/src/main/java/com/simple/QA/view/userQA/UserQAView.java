package com.simple.QA.view.userQA;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class UserQAView {

	private QAcontroller qAcontroller;
	private DynamicForm formHead;
	private DynamicForm formCycleTest;
	private RadioGroupItem metric_A;
	private RadioGroupItem metric_B;
	private TextItem metric_C;
	private Long start_test_QA;
	private int id_user_QA;

	public UserQAView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public VLayout getUserQAView() {

		VLayout vLayout = new VLayout();
		vLayout.setWidth100();
		vLayout.setHeight100();

		formCycleTest = getformCycleTest();
		setValuesMetric_A_B();
		formCycleTest.hide();

		formHead = new DynamicForm();
		formHead.setAlign(Alignment.CENTER);

		ComboBoxItem selectAppItem = new ComboBoxItem();
		selectAppItem.setName("selectAppItem");
		selectAppItem.setTitle("Seleccione una Aplicación");
		selectAppItem.setTitleAlign(Alignment.CENTER);
		selectAppItem.setTitleOrientation(TitleOrientation.TOP);
		selectAppItem.setShowTitle(true);
		selectAppItem.setRequired(true);
		selectAppItem.setWidth(200);
		selectAppItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				int id_app = Integer.valueOf(selectAppItem.getValueAsString());
				qAcontroller.getValueselectVersionItem(id_app);
			}
		});

		ComboBoxItem selectVersionItem = new ComboBoxItem();
		selectVersionItem.setName("selectVersionItem");
		selectVersionItem.setTitle("Seleccione una versión");
		selectVersionItem.setTitleOrientation(TitleOrientation.TOP);
		selectVersionItem.setTitleAlign(Alignment.CENTER);
		selectVersionItem.setShowTitle(true);
		selectVersionItem.setRequired(true);
		selectVersionItem.setWidth(200);

		ButtonItem startTestButton = new ButtonItem();
		startTestButton.setName("startTestButton");
		startTestButton.setTitle("Crear ciclo de pruebas");
		startTestButton.setEndRow(true);
		startTestButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (formHead.validate(false)) {
					showFormCycleTest();
				} else {
					qAcontroller.showMessaje("Seleccione una Aplicación y versión");
				}
			}
		});

		formHead.setFields(selectAppItem, selectVersionItem, startTestButton);
		qAcontroller.getValueselectAppItem("userQAView");

		vLayout.addMember(formHead);
		vLayout.addMember(formCycleTest);
		return vLayout;
	}

	private DynamicForm getformCycleTest() {

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Resgistre los datos del ciclo de prueba ");
		form.setIsGroup(true);
		form.setWidth100();

		TextItem nameCase = new TextItem();
		nameCase.setRequired(true);
		nameCase.setName("nameCase");
		nameCase.setTitle("Nombre del Ciclo");
		nameCase.setWidth(900);

		TextAreaItem descriptionResult = new TextAreaItem();
		descriptionResult.setRequired(true);
		descriptionResult.setName("descriptionResult");
		descriptionResult.setTitle("Descripción de resultado");
		descriptionResult.setWidth(900);
		descriptionResult.setHeight(200);

		metric_A = new RadioGroupItem();
		metric_A.setRequired(true);

		metric_B = new RadioGroupItem();
		metric_B.setRequired(true);

		metric_C = new TextItem();
		metric_C.setRequired(true);

		ButtonItem buttonSaveCycle = new ButtonItem();
		buttonSaveCycle.setTitle("Guardar y Terminar");
		buttonSaveCycle.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (formCycleTest.validate(false)) {

					int id_user = id_user_QA;
					int id_version = Integer.valueOf(formHead.getValueAsString("selectVersionItem"));
					String name_test = form.getValueAsString("nameCase");
					String result = form.getValueAsString("descriptionResult");
					long start_test = start_test_QA;
					long end_test = System.currentTimeMillis();
					int metric_a = Integer.valueOf(metric_A.getValueAsString());
					int metric_b = Integer.valueOf(metric_B.getValueAsString());
					int metric_c = Integer.valueOf(metric_C.getValueAsString());

					qAcontroller.saveTest(id_user, id_version, name_test, result, start_test, end_test, metric_a,
							metric_b, metric_c);
				}

			}
		});

		form.setFields(nameCase, descriptionResult, metric_A, metric_B, metric_C, buttonSaveCycle);

		return form;
	}

	public void setValueselectAppItem(LinkedHashMap<Integer, String> valueMap) {
		formHead.getField("selectAppItem").setValueMap(valueMap);
	}

	public void setValueselectVersionItem(LinkedHashMap<Integer, String> valueMap) {
		formHead.getField("selectVersionItem").setValueMap(valueMap);
	}

	public void setTitleMetrcis(ArrayList<String> titleMetrics) {
		metric_A.setTitle(titleMetrics.get(0));
		metric_B.setTitle(titleMetrics.get(1));
		metric_C.setTitle(titleMetrics.get(2));
		metric_A.redraw();
		metric_B.redraw();
		metric_C.redraw();
	}

	public void setValuesMetric_A_B() {

		LinkedHashMap<Integer, String> valueMetric_A = new LinkedHashMap<Integer, String>();
		valueMetric_A.put(1, "Nada satisfecho");
		valueMetric_A.put(2, "No tan satisfecho");
		valueMetric_A.put(3, "Algo satisfecho");
		valueMetric_A.put(4, "Muy satisfecho");
		valueMetric_A.put(5, "Extremadamente satisfecho");
		metric_A.setValueMap(valueMetric_A);

		LinkedHashMap<Integer, String> valueMapMetric_B = new LinkedHashMap<Integer, String>();
		valueMapMetric_B.put(1, "Nada exitosa");
		valueMapMetric_B.put(2, "No tan exitosa");
		valueMapMetric_B.put(3, "Algo exitosa");
		valueMapMetric_B.put(4, "Muy exitosa");
		valueMapMetric_B.put(5, "Extremadamente exitosa");
		metric_B.setValueMap(valueMapMetric_B);

	}

	public void setId_user(int id_user) {
		this.id_user_QA = id_user;
	}

	private void showFormCycleTest() {
		formHead.getField("startTestButton").disable();
		start_test_QA = System.currentTimeMillis();
		formCycleTest.show();
		qAcontroller.findTitleMetrics();
	}

	public void hideFormCycleTest() {
		start_test_QA = null;
		formCycleTest.setValue("descriptionResult", "");
		formCycleTest.setValue("nameCase", "");
		metric_A.clearValue();
		metric_B.clearValue();
		metric_C.clearValue();
		formCycleTest.hide();
		formHead.getField("startTestButton").enable();
	}

}
