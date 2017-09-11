package com.prokarma.project.details;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.custom.components.RequiredTextField;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.model.converters.ProjectUIModelToProjectConverter;
import com.prokarma.model.converters.TMSFactory;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.ManagerService;
import com.prokarma.service.UserContext;
import com.prokarma.web.timesheet.BasePage;
import com.prokarma.web.timesheet.HomePage;
import com.prokarma.webmodel.ProjectUIModel;

public class AddProject extends BasePage {
	@SpringBean
	private ManagerService managerservice;
	@SpringBean
	private EmployeeService employeeservice;

	Employee employee = new Employee();

	public AddProject() {
		buildPage();
	}

	@SuppressWarnings("rawtypes")
	private void buildPage() {
		final ProjectUIModel projectModel = new ProjectUIModel();
		Form<Project> addProj = new Form<Project>("addProj",
				new CompoundPropertyModel<Project>(projectModel));
		addProj.setOutputMarkupId(true);
		add(addProj);

		List<Employee> empList = employeeservice.getEmployees();
		List<String> usernames = new ArrayList<String>();
		for (Employee user : empList) {
			usernames.add(user.getUsername());
		}

		@SuppressWarnings("unchecked")
		final ListMultipleChoice<String> employees = new ListMultipleChoice(
				"employees", new PropertyModel<String>(projectModel,
						"assignedEmployees"), usernames);

		RequiredTextField projectcode = new RequiredTextField("projectcode",
				new PropertyModel<String>(projectModel, "codename"));
		RequiredTextField projectname = new RequiredTextField("projectname",
				new PropertyModel<String>(projectModel, "projectName"));
		RequiredTextField technology = new RequiredTextField("technology",
				new PropertyModel<String>(projectModel, "techUsed"));

		final TextArea<String> description = new TextArea<String>(
				"description", new PropertyModel<String>(projectModel,
						"description"));

		DateTextField start_Date = new DateTextField("start_Date", "dd-MM-yyyy");
		start_Date.setDefaultModel(new PropertyModel<String>(projectModel,
				"start_Date"));
		start_Date.setRequired(true);
		start_Date.add(new DatePicker());

		DateTextField end_Date = new DateTextField("end_Date", "dd-MM-yyyy");
		end_Date.setDefaultModel(new PropertyModel<String>(projectModel,
				"end_Date"));
		end_Date.setRequired(true);
		end_Date.add(new DatePicker());

		projectModel.setStatus("new");
		projectModel.setManagerId(UserContext.getUserId());
		

		AjaxButton submitButton = new AjaxButton("submitButton") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
						
				
				ProjectUIModelToProjectConverter converter = TMSFactory.getProjectUIModelToProjectConverter();
				Project project = converter.convert(projectModel);
				
				for (String emp : projectModel.getAssignedEmployees()) {
					employee = employeeservice.getEmployee(emp);
					project.addEmployee(employee);
				}

				managerservice.addProject(project);
				setResponsePage(HomePage.class);
			}

		};

		addProj.add(submitButton);
		addProj.add(employees, projectcode, projectname, technology, description,
				start_Date, end_Date);

	}
}