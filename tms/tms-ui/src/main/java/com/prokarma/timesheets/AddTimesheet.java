package com.prokarma.timesheets;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.custom.components.RequiredTextField;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;
import com.prokarma.model.converters.TimesheetUIModelToTimesheetConverter;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.ProjectService;
import com.prokarma.service.TimesheetService;
import com.prokarma.service.UserContext;
import com.prokarma.web.timesheet.BasePage;
import com.prokarma.web.timesheet.HomePage;
import com.prokarma.web.timesheet.MySession;
import com.prokarma.webmodel.TimesheetUIModel;

public class AddTimesheet extends BasePage {

	public AddTimesheet() {
		buildComponentsPage();
	}

	@SpringBean
	private TimesheetService timesheetService;

	@SpringBean
	private ProjectService projectService;
	
	@SpringBean
	EmployeeService employeeService;

	private int totalHours = 0;

	private void buildComponentsPage() {
		final TimesheetUIModel time = new TimesheetUIModel();
		Form<AddTimesheet> timesheetForm = new Form<AddTimesheet>(
				"timesheetForm");
		timesheetForm.setOutputMarkupId(true);
		add(timesheetForm);

		Label employeename = new Label("employeename",
				UserContext.getUserName());
		
		Employee employee = employeeService.getEmployee(UserContext.getUserName());

		DateTextField startdate = new DateTextField("startdate", "dd-MM-yyyy");
		startdate
				.setDefaultModel(new PropertyModel<String>(time, "begin_Date"));
		startdate.setRequired(true);
		startdate.add(new DatePicker());

		DateTextField enddate = new DateTextField("enddate", "dd-MM-yyyy");
		enddate.setDefaultModel(new PropertyModel<String>(time, "end_Date"));
		enddate.setRequired(true);
		enddate.add(new DatePicker());

		final TextArea<String> description = new TextArea<String>(
				"description", new PropertyModel<String>(time, "description"));
		description.setRequired(true);
		
		time.setProjectCode(projectService.getProjectCode(employee.getProjectId()));
		
		Label projectcode = new Label("projectcode",
				time.getProjectCode());
		
		final RequiredTextField sunday = new RequiredTextField("sunday",
				new PropertyModel<Integer>(time, "sunday"));
		sunday.setOutputMarkupId(true);
		sunday.setRequired(true);
		sunday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getSunday();
			}
		});

		final RequiredTextField monday = new RequiredTextField("monday",
				new PropertyModel<Integer>(time, "monday"));
		monday.setOutputMarkupId(true);
		monday.setRequired(true);
		monday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getMonday();

			}
		});

		final RequiredTextField tuesday = new RequiredTextField("tuesday",
				new PropertyModel<Integer>(time, "tuesday"));
		tuesday.setOutputMarkupId(true);
		tuesday.setRequired(true);
		tuesday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getTuesday();
			}
		});

		final RequiredTextField wednesday = new RequiredTextField("wednesday",
				new PropertyModel<Integer>(time, "wednesday"));
		wednesday.setOutputMarkupId(true);
		wednesday.setRequired(true);
		wednesday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getWednesday();
			}
		});
		final RequiredTextField thursday = new RequiredTextField("thursday",
				new PropertyModel<Integer>(time, "thursday"));
		thursday.setOutputMarkupId(true);
		thursday.setRequired(true);
		thursday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getThursday();
			}
		});
		final RequiredTextField friday = new RequiredTextField("friday",
				new PropertyModel<Integer>(time, "friday"));
		friday.setOutputMarkupId(true);
		friday.setRequired(true);
		friday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getFriday();
			}
		});
		final RequiredTextField total = new RequiredTextField("total",
				new PropertyModel<Integer>(time, "totalHours"));
		total.setOutputMarkupId(true);

		final RequiredTextField saturday = new RequiredTextField("saturday",
				new PropertyModel<Integer>(time, "saturday"));
		saturday.setOutputMarkupId(true);
		saturday.setRequired(true);
		saturday.add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				totalHours += time.getSaturday();
				time.setTotalHours(totalHours);
				target.addComponent(total);
			}
		});

		AjaxButton submit = new AjaxButton("submit") {

			private static final long serialVersionUID = 1L;
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				refreshFeedbackPanel(target);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				time.setStatus("Submitted");
				TimesheetUIModelToTimesheetConverter converter = new TimesheetUIModelToTimesheetConverter();
				Timesheet timesheet = converter.convert(time);
				Project project = projectService.getProject(time
						.getProjectCode());

				timesheet.setManagerId(project.getManagerId());
				timesheetService.addTimesheet(timesheet,
						UserContext.getUserName(), time.getProjectCode());
				setResponsePage(HomePage.class);
			}
		};
		AjaxButton cancel = new AjaxButton("cancel") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				((MySession) getSession()).setProject(new Project());
				form.clearInput();
				target.addComponent(form);
			}
		};

		timesheetForm.add(employeename, startdate, enddate, description,
				projectcode, monday, tuesday, wednesday, thursday, friday,
				saturday, sunday, total, submit, cancel);

	}

}
