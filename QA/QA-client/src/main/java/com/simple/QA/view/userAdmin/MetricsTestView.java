package com.simple.QA.view.userAdmin;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class MetricsTestView {

	private QAcontroller qAcontroller;
	private ListGrid listGrid;

	public MetricsTestView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public ListGrid getMetricsTestView() {

		listGrid = new ListGrid();
		listGrid.setWidth(900);
		listGrid.setHeight(500);

		listGrid.setVirtualScrolling(false);
		listGrid.setShowRecordComponents(true);
		listGrid.setShowRecordComponentsByCell(true);

		ListGridField name_app = new ListGridField("name_app", "Nombre Aplicación");
		ListGridField name_version = new ListGridField("name_version", "Nombre versión");
		ListGridField total_Tests = new ListGridField("Total_Tests", "Numero de pruebas");
		ListGridField usability_failures_percent = new ListGridField("usability_failures_percent",
				"Pruebas fallidas usabilidad (%)");
		ListGridField app_failures_percent = new ListGridField("app_failures_percent", "Pruebas fallidas (%)");
		ListGridField sum_failures = new ListGridField("sum_failures", "Total errores encontrados");

		listGrid.setFields(name_app, name_version, total_Tests, usability_failures_percent, app_failures_percent,
				sum_failures);
		listGrid.setCanResizeFields(true);
		qAcontroller.findMetricsTest();
		return listGrid;
	}

	public void setFindMetricsTest(RecordList grid) {
		listGrid.setData(grid);
	}

}
