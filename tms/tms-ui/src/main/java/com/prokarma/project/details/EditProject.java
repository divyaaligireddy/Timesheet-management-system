package com.prokarma.project.details;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.model.converters.ProjectToProjectUIModelConverter;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.ManagerService;
import com.prokarma.service.ProjectService;
import com.prokarma.web.timesheet.HomePage;
import com.prokarma.web.timesheet.MySession;
import com.prokarma.webmodel.ProjectUIModel;

public class EditProject extends Panel {

	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private ManagerService managerservice;
	@SpringBean
	private EmployeeService employeeservice;
	@SpringBean
	private ProjectService projectservice;

	ProjectUIModel projectModel;

	public EditProject(String id) {
		super(id);
		@SuppressWarnings("static-access")
		final Project project = ((MySession) getSession()).getProject();

		ProjectToProjectUIModelConverter converter = new ProjectToProjectUIModelConverter();
		projectModel = converter.convert(project);

		List<String> assgndemp = projectservice.getEmployees(project);

		
		Form<Project> editprojform = new Form<Project>("editprojform",
				new CompoundPropertyModel<Project>(project));
		add(editprojform);

		String managerName = "";
		
		if(project.getManager() != null) {
			managerName = project.getManager().getUsername();
		}
		
		Label manager = new Label("manager", managerName);

		List<Employee> empList = employeeservice.getEmployees();
		
		final List<String> usernames = new ArrayList<String>();
		
			for (Employee user : empList) {
				usernames.add(user.getUsername());
			}
			for (String s : assgndemp) {
				usernames.remove(s);
			}

			
		

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ListMultipleChoice<String> employees = new ListMultipleChoice(
				"asgndemployees", new PropertyModel<String>(projectModel,
						"assignedEmployees"), assgndemp);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ListMultipleChoice<String> newemp = new ListMultipleChoice(
				"newemp", new PropertyModel<String>(projectModel,
						"otherEmployees"), usernames);

		TextField<String> projectcode = new TextField<String>("projectcode",
				new PropertyModel<String>(projectModel, "codename"));
		TextField<String> projectname = new TextField<String>("projectname",
				new PropertyModel<String>(projectModel, "projectName"));
		TextField<String> technology = new TextField<String>("technology",
				new PropertyModel<String>(projectModel, "techUsed"));

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

		AjaxButton submitButton = new AjaxButton("submitButton",editprojform) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				Project project = projectservice.getProject(projectModel
						.getCodename());
				project.setDescription(projectModel.getDescription());
				project.setEnd_Date(projectModel.getEnd_Date());
				project.setStart_Date(projectModel.getStart_Date());
				project.setStatus(projectModel.getStatus());
				project.setTechUsed(projectModel.getTechUsed());
				project.setProjectName(projectModel.getProjectName());

			/*	// take list of selected AssigneedEmp
				final List<String> selectedOtherEmpList = projectModel.getOtherEmployees();
				projectModel.setAssignedEmployees(selectedOtherEmpList);
				final List<String> testEmpList = projectModel.getAssignedEmployees();
				List<String> test  = projectModel.getOtherEmployees();
				test.removeAll(testEmpList);*/
				
				
				for (String emp : projectModel.getOtherEmployees()) {
					Employee employee = employeeservice.getEmployee(emp);

					project.addEmployee(employee);

				}

				managerservice.editProject(project);
				setResponsePage(HomePage.class);
			}

		};
		editprojform.add(submitButton);
		editprojform.add(manager, employees, newemp, projectcode, projectname,
				technology, start_Date, end_Date);

	}

}
