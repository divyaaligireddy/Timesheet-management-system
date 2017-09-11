package com.prokarma.web.timesheet;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.custom.components.RequiredTextField;
import com.prokarma.domainmodel.Employee;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.UserContext;
import com.prokarma.service.business.exceptions.TmsBusinessException;

public class LoginPage extends BasePage {

	@SpringBean
	private EmployeeService employeeservice;

	private FeedbackPanel errorFeedback;
	
	public LoginPage() {

		buildPage();
		mgrMenuRepeater.setVisible(false);
		empMenuRepeater.setVisible(false);

	}

	private void buildPage() {
		errorFeedback=new FeedbackPanel("errorFeedback");
		final Employee user = new Employee();
		Form<Employee> loginForm = new Form<Employee>("loginForm",
				new CompoundPropertyModel<Employee>(user));
		loginForm.setOutputMarkupId(true);
		add(loginForm);

		final RequiredTextField username = new RequiredTextField("username",
				new PropertyModel<String>(user, "username"));

		PasswordTextField password = new PasswordTextField("password",
				new PropertyModel<String>(user, "password"));

		loginForm.add(new Label("loginUsername", "Username"));
		loginForm.add(username);
		loginForm.add(new Label("loginPassword", "Password"));
		loginForm.add(password);

		AjaxButton submitButton = new AjaxButton("submitButton") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				refreshFeedbackPanel(target);
				Employee emp = null;
				try{
					emp = employeeservice.validateUser(user);
					if (emp.getUserId() != 0) {

						user.setUserId(emp.getUserId());
						user.setRole(emp.getRole());

						UserContext.setUserName(user.getUsername());
						UserContext.setRole(emp.getRole());
						UserContext.setUserId(emp.getUserId());

						((MySession) getSession()).setEmployee(user);
						((MySession) getSession()).setRole(emp.getRole().name());
						((MySession) getSession()).setUsername(user.getUsername());
						((MySession) getSession()).setUserId(user.getUserId());

						setResponsePage(HomePage.class);
					}
					
				}catch(final TmsBusinessException tx){
					
					errorFeedback.error(tx.getMessage());
					System.out.println("-----"+tx.getMessage());
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

		loginForm.add(submitButton, resetButton, errorFeedback);
		errorFeedback.setOutputMarkupId(true);
		resetButton.setDefaultFormProcessing(false);
	}

}
