package com.simple.QA.view.userAdmin;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SummaryTestView {

	private QAcontroller qAcontroller;
	private ListGrid listGrid;

	public SummaryTestView(QAcontroller qAcontroller) {
		this.qAcontroller = qAcontroller;
	}

	public ListGrid getSummaryTestView() {

		listGrid = new ListGrid();
		listGrid.setWidth(900);
		listGrid.setHeight(500);

		listGrid.setVirtualScrolling(false);
		listGrid.setShowRecordComponents(true);
		listGrid.setShowRecordComponentsByCell(true);

		
		ListGridField name_app = new ListGridField("name_app", "Nombre Aplicación");
		ListGridField name_version = new ListGridField("name_version", "Nombre Versión");
		ListGridField name_test = new ListGridField("name_test", "Nombre Prueba");
		ListGridField result = new ListGridField("result", "Resultado");
		ListGridField duration_test_inMin = new ListGridField("duration_test_inMin", "Duración prueba (Minutos)");
		ListGridField name_user = new ListGridField("name_user", "Nombre Usuario");

				
		listGrid.setFields(name_app,name_version,name_test,result,duration_test_inMin,name_user);
		listGrid.setCanResizeFields(true);
		qAcontroller.findSummaryTest();
		return listGrid;
	}
	
	public void setFindSummaryTest(RecordList grid) {
		listGrid.setData(grid);
	}

}
