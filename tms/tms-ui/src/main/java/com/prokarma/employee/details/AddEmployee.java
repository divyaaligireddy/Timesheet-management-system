package com.prokarma.employee.details;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.prokarma.custom.components.RequiredTextField;
import com.prokarma.domainmodel.Employee;
import com.prokarma.model.converters.EmployeeUIModelToEmployeeConverter;
import com.prokarma.model.converters.TMSFactory;
import com.prokarma.service.ManagerService;
import com.prokarma.web.timesheet.BasePage;
import com.prokarma.web.timesheet.HomePage;
import com.prokarma.webmodel.EmployeeUIModel;

public class AddEmployee extends BasePage {

	@SpringBean
	private ManagerService managerService;
	
	RequiredTextField	lname;
	
	public AddEmployee() {
		buildPage();
	}

	@SuppressWarnings("unchecked")
	private void buildPage() {

		final Logger logger = Logger.getLogger(AddEmployee.class);
		final EmployeeUIModel employee = new EmployeeUIModel();

		Form<EmployeeUIModel> regForm = new Form<EmployeeUIModel>("regForm",
				new CompoundPropertyModel<EmployeeUIModel>(employee));
		add(regForm);

		RequiredTextField uname = new RequiredTextField("username",
				new PropertyModel<String>(employee, "username"));
		uname.setRequired(true);

		DropDownChoice<String> employeeType = new DropDownChoice<String>(
				"role", Arrays.asList(new String[] { "Manager", "Employee" })) {
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getDefaultChoice(Object selected) {
				return "<option selected>type</option>";
			}
		};

		final RequiredTextField mobile = new RequiredTextField("mobile",
				new PropertyModel<String>(employee, "phoneNumber"));
		mobile.setRequired(true);

		RequiredTextField country = new RequiredTextField("country",
				new PropertyModel<String>(employee, "country"));
		country.setRequired(true);

		RequiredTextField fname = new RequiredTextField("fname",
				new PropertyModel<String>(employee, "firstname"));
	

		lname = new RequiredTextField("lname", new PropertyModel<String>(
				employee, "lastname"));
	
		PasswordTextField pwd = new PasswordTextField("password", new PropertyModel<String>(
				employee, "password"));
		pwd.setRequired(true);

		PasswordTextField confirmpassword = new PasswordTextField(
				"confirmpassword", new PropertyModel<String>(employee,
						"confirmPassword"));
		confirmpassword.setRequired(true);

		DateTextField doj = new DateTextField("doj", "dd-MM-yyyy");
		doj.setDefaultModel(new PropertyModel<String>(employee, "doj"));
		doj.setRequired(true);
		doj.add(new DatePicker());

		RequiredTextField email = new RequiredTextField("email",
				new PropertyModel<String>(employee, "email"));
		email.setRequired(true);
		email.add(EmailAddressValidator.getInstance());
		email.clearInput();
		email.fatal("Email is not valid");

		AjaxButton submitButton = new AjaxButton("submitButton",regForm) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				refreshFeedbackPanel(target);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				EmployeeUIModelToEmployeeConverter converter = TMSFactory
						.getEmployeeUIModelToEmployeeConverter();
				Employee emp = converter.convert(employee);

				if (!employee.getConfirmPassword().equals(
						employee.getPassword())) {
					target.appendJavascript("alert('password and confirm password should be same');");
					form.clearInput();
					target.addComponent(form);

				} else if (!(employee.getPhoneNumber().matches("^[0-9]+") && employee
						.getPhoneNumber().length() == 10)) {
					target.appendJavascript("alert('not a valid phone number');");
					mobile.clearInput();
					target.addComponent(form);
				}

				else {
					logger.info("calling the addEmployee method in service");
					managerService.addEmployee(emp);
					setResponsePage(HomePage.class);
				}
			}
		};
		AjaxButton resetButton = new AjaxButton("resetButton") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				form.clearInput();
				target.addComponent(form);

			}
		};

		regForm.add(submitButton);
		regForm.add(resetButton);
		resetButton.setDefaultFormProcessing(false);

		regForm.add(uname, employeeType, mobile, country, fname, lname, pwd,
				confirmpassword, doj, email);

	}

}
