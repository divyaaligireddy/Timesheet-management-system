package com.prokarma.employee.details;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.prokarma.custom.components.RequiredTextField;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Role;
import com.prokarma.model.converters.EmployeeToEmployeeUIModelConverter;
import com.prokarma.model.converters.TMSFactory;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.ManagerService;
import com.prokarma.web.timesheet.MySession;
import com.prokarma.webmodel.EmployeeUIModel;

public class EditEmployee extends Panel {

	private static final long serialVersionUID = -3421828527584240148L;

	@SpringBean
	private ManagerService managerservice;
	
	@SpringBean
	private EmployeeService employeeService;


	public EditEmployee(String id) {
		super(id);
		buildPage();
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	private void buildPage() {

		Employee employee = ((MySession) getSession()).getEmployee();

		EmployeeToEmployeeUIModelConverter converter = TMSFactory
				.getEmployeeToEmployeeUIModelConverter();
		EmployeeUIModel empUI = converter.convert(employee);

		Form<EmployeeUIModel> editempform = new Form<EmployeeUIModel>(
				"editempform",
				new CompoundPropertyModel<EmployeeUIModel>(empUI));
		add(editempform);

		RequiredTextField uname = new RequiredTextField("username",
				new PropertyModel<String>(empUI, "username"));
		uname.setRequired(true);

		DropDownChoice<String> employeeType = new DropDownChoice<String>(
				"role", Arrays.asList(new String[] { Role.Manager.toString(), Role.Employee.toString() })) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(Object selected) {
				return "<option selected>" + Role.Employee.toString() + "</option>";
			}
		};

		final RequiredTextField mobile = new RequiredTextField("mobile",
				new PropertyModel<String>(empUI, "phoneNumber"));
		mobile.setRequired(true);

		RequiredTextField country = new RequiredTextField("country",
				new PropertyModel<String>(empUI, "country"));
		country.setRequired(true);

		RequiredTextField fname = new RequiredTextField("fname",
				new PropertyModel<String>(empUI, "firstname"));
		fname.setRequired(true);

		RequiredTextField lname = new RequiredTextField("lname",
				new PropertyModel<String>(empUI, "lastname"));

		PasswordTextField pwd = new PasswordTextField("password",
				new PropertyModel<String>(empUI, "password"));
		pwd.setRequired(true);

		PasswordTextField confirmpassword = new PasswordTextField(
				"confirmpassword", new PropertyModel<String>(empUI,
						"confirmPassword"));
		confirmpassword.setRequired(true);

		DateTextField doj = new DateTextField("doj", "dd-MM-yyyy");
		doj.setDefaultModel(new PropertyModel<String>(empUI, "doj"));
		doj.setRequired(true);
		doj.add(new DatePicker());

		RequiredTextField email = new RequiredTextField("email",
				new PropertyModel<String>(empUI, "email"));
		email.setRequired(true);
		email.add(EmailAddressValidator.getInstance());
		email.clearInput();
		email.fatal("Email is not valid");

		

		AjaxButton submitButton = createEditButton(empUI, mobile);
		AjaxButton resetButton = createResetButton();

		editempform.add(submitButton);
		editempform.add(resetButton);
		resetButton.setDefaultFormProcessing(false);

		editempform.add(uname, employeeType, mobile, country, fname, lname,
				pwd, confirmpassword, doj, email);

	}

	private AjaxButton createResetButton() {
		AjaxButton resetButton = new AjaxButton("resetButton") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				form.clearInput();
				target.addComponent(form);

			}
		};
		return resetButton;
	}

	private AjaxButton createEditButton(final EmployeeUIModel employeeUIModel, final RequiredTextField mobile) {
		AjaxButton submitButton = new AjaxButton("submitButton") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				target.appendJavascript("alert('inside edit employee');");
				
				Employee employeeToEdit = employeeService.getEmployee(employeeUIModel.getUsername());
				employeeToEdit.setCountry(employeeUIModel.getCountry());
				employeeToEdit.setDoj(employeeUIModel.getDoj());
				employeeToEdit.setEmail(employeeUIModel.getEmail());
				employeeToEdit.setFirstname(employeeUIModel.getFirstname());
				employeeToEdit.setPassword(employeeUIModel.getPassword());
				employeeToEdit.setLastname(employeeUIModel.getLastname());
				employeeToEdit.setPhoneNumber(employeeUIModel.getPhoneNumber());
				employeeToEdit.setUsername(employeeUIModel.getUsername());
				employeeToEdit.setRole(Role.valueOf(employeeUIModel.getRole()));


				if (!employeeUIModel.getConfirmPassword().equals(
						employeeToEdit.getPassword())) {
					target.appendJavascript("alert('password and confirm password should be same');");
					form.clearInput();
					target.addComponent(form);

				} else if (!(employeeToEdit.getPhoneNumber().matches("^[0-9]+") && employeeToEdit
						.getPhoneNumber().length() == 10)) {
					target.appendJavascript("alert('not a valid phone number');");
					mobile.clearInput();
					target.addComponent(form);
				}

				else {
					managerservice.editEmployee(employeeToEdit);
					setResponsePage(EmployeeDetails.class);
				}
			}
		};
		return submitButton;
	}

}
