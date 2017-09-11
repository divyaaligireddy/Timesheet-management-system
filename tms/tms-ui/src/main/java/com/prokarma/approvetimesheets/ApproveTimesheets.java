package com.prokarma.approvetimesheets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Timesheet;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.ManagerService;
import com.prokarma.service.UserContext;
import com.prokarma.web.timesheet.BasePage;

public class ApproveTimesheets extends BasePage {

	public ApproveTimesheets() {
		buildListViewPage();
	}

	@SpringBean
	private ManagerService managerservice;
	
	@SpringBean
	private EmployeeService employeeService;

	private void buildListViewPage() {
		final List<Timesheet> timesheet = preparePersonList();

		ListView<Timesheet> timesheetView = new ListView<Timesheet>(
				"timesheet", timesheet) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Timesheet> item) {
				final Timesheet time = item.getModelObject();

				Employee emp=employeeService.getEmployeeById(time.getOwner().getUserId());
				
				item.add(new Label("EmpId", time.getOwner().getUserId() + ""));
				item.add(new Label("EmpName", emp.getUsername()));

				item.add(new Label("BeginDate", time.getBegin_Date().toString()));
				item.add(new Label("EndDate", time.getEnd_Date().toString()));
				item.add(new Label("Status", time.getStatus()));
				AjaxLink<String> approve = new AjaxLink<String>("Approve") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {

						managerservice.approveTimesheet(time.getTimesheetId());

						target.appendJavascript("alert('Timesheet approved');");

					}

				};

				AjaxLink<String> decline = new AjaxLink<String>("Decline") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {

						managerservice.declineTimesheet(time.getTimesheetId());

						target.appendJavascript("alert('Timesheet declined');");

					}

				};
				item.add(approve, decline);
			}
		};
		add(timesheetView);
	}

	private List<Timesheet> preparePersonList() {
		List<Timesheet> timesheet = new ArrayList<Timesheet>();

		timesheet = managerservice.manageTimesheets(UserContext.getUserId());
		Set<Timesheet> timesheets=new HashSet<Timesheet>();
		for (Timesheet time : timesheet) {
			timesheets.add(time);
		}
		timesheet.clear();
		for (Timesheet time : timesheets) {
			timesheet.add(time);
		}

		return timesheet;

	}
}
