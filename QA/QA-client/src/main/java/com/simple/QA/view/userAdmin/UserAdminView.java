package com.simple.QA.view.userAdmin;

import com.simple.QA.controller.QAcontroller;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class UserAdminView {

	private QAcontroller qAcontroller;
	private VersionView versionView;
	private SummaryTestView summaryTestView;
	private MetricsTestView metricsTestView;
	private MetricsUserView metricsUserView;
	private AppView appView;
	private UserView userView;
	private TabSet tabSet;

	public UserAdminView(QAcontroller qAcontroller, AppView appView, VersionView versionView,
			SummaryTestView summaryTestView, MetricsTestView metricsTestView, MetricsUserView metricsUserView,
			UserView userView) {
		this.appView = appView;
		this.versionView = versionView;
		this.summaryTestView = summaryTestView;
		this.metricsTestView = metricsTestView;
		this.metricsUserView = metricsUserView;
		this.userView = userView;
		this.qAcontroller = qAcontroller;
	}

	public TabSet getUserAdminView() {

		tabSet = new TabSet();
		tabSet.setTabBarPosition(Side.TOP);
		tabSet.setWidth100();
		tabSet.setHeight100();

		Tab tabApp = new Tab("Aplicaciones");
		tabApp.setPane(appView.getAppView());

		Tab tabVersion = new Tab("Versiones de Aplicación");
		tabVersion.setPane(versionView.getVersionView());

		Tab tabUser = new Tab("Usuarios");
		tabUser.setPane(userView.getUserView());

		Tab tabSummatyTest = new Tab("Resumen de pruebas");
		tabSummatyTest.setPane(summaryTestView.getSummaryTestView());

		Tab tabMetricsTest = new Tab("Metricas por Aplicacion");
		tabMetricsTest.setPane(metricsTestView.getMetricsTestView());

		Tab tabMetricsUser = new Tab("Metricas Usuarios");
		tabMetricsUser.setPane(metricsUserView.getMetricsUserView());

		tabSet.addTab(tabApp);
		tabSet.addTab(tabVersion);
		tabSet.addTab(tabUser);
		tabSet.addTab(tabSummatyTest);
		tabSet.addTab(tabMetricsTest);
		tabSet.addTab(tabMetricsUser);

		tabSet.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				int idTab = event.getTabNum();
				switch (idTab) {
				case 1:
					qAcontroller.getValueselectAppItem("versionView");
					break;

				default:
					break;
				}
			}
		});

		return tabSet;
	}

}
