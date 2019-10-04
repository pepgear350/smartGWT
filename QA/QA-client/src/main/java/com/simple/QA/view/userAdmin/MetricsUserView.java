package com.simple.QA.view.userAdmin;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class MetricsUserView {

	private QAcontroller qAcontroller;
	private ListGrid listGrid;

	public MetricsUserView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public ListGrid getMetricsUserView() {

		listGrid = new ListGrid();
		listGrid.setWidth(900);
		listGrid.setHeight(500);

		listGrid.setVirtualScrolling(false);
		listGrid.setShowRecordComponents(true);
		listGrid.setShowRecordComponentsByCell(true);

		ListGridField name_user = new ListGridField("name_user", "Nombre Usuario");
		ListGridField avg_duration_testInMin = new ListGridField("avg_duration_testInMin", "Tiempo promedio por prueba (Min)");

		listGrid.setFields(name_user, avg_duration_testInMin);
		listGrid.setCanResizeFields(true);
		qAcontroller.findMetricsUser();
		return listGrid;
	}

	public void setFindMetricsUser(RecordList grid) {
		listGrid.setData(grid);
	}

}
