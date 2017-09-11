package com.prokarma.timesheets;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.domainmodel.Timesheet;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.UserContext;
import com.prokarma.web.timesheet.BasePage;

public class UserTimesheets extends BasePage {

	public UserTimesheets() {
		buildListViewPage();
	}

	@SpringBean
	private EmployeeService employeeservice;

	private void buildListViewPage() {
		List<Timesheet> timesheet = preparePersonList();

		ListView<Timesheet> timesheetView = new ListView<Timesheet>(
				"timesheet", timesheet) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Timesheet> item) {

			
				final Timesheet time = item.getModelObject();
				
				item.add(new Label("EmpId", time.getOwner().getUserId() + ""));

				item.add(new Label("EmpName", time.getOwner().getUsername()+ ""));

				item.add(new Label("BeginDate", time.getBegin_Date().toString()));
				
				item.add(new Label("EndDate", time.getEnd_Date().toString()));
				
				item.add(new Label("Status", time.getStatus()));
			}
		};
		add(timesheetView);
	}

	private List<Timesheet> preparePersonList() {
	
		List<Timesheet> timesheet = employeeservice.viewTimesheet(UserContext.getUserId(),UserContext.getUserName());

		return timesheet;

	}
}
