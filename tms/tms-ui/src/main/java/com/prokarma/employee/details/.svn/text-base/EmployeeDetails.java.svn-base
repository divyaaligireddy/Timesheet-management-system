package com.prokarma.employee.details;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.domainmodel.Employee;

import com.prokarma.service.ManagerService;
import com.prokarma.web.timesheet.BasePage;
import com.prokarma.web.timesheet.MySession;

public class EmployeeDetails extends BasePage {

	@SpringBean
	private ManagerService managerservice;
	
	private Form<Object> empDetailsForm = new Form<Object>("empDetails");
	ModalWindow empmodel = null;
	ModalWindow deleteModalWindow = null;

	public EmployeeDetails() {
		buildPage();
		listView();
	}

	private void listView() {

		add(empDetailsForm);

		empDetailsForm.add(empmodel = new ModalWindow("empmodel"));

		empmodel.setTitle("Edit Employee");
		empmodel.setInitialWidth(500);
		empmodel.setInitialHeight(500);
		empmodel.setWidthUnit("px");
		empmodel.setHeightUnit("px");
		empmodel.setResizable(false);

		empmodel.setCookieName("modal-2");

		empmodel.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {

			private static final long serialVersionUID = 1L;

			public boolean onCloseButtonClicked(final AjaxRequestTarget target) {
				// setResult("closed");
				return true;
			}
		});

		empmodel.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = 1L;

			public void onClose(final AjaxRequestTarget target) {
				// target.addComponent(result);
			}
		});

		List<Employee> empList = managerservice.getEmployees();
		

		Set<Employee> employees = new HashSet<Employee>();
		for (Employee employee : empList) {
			employees.add(employee);
		}
		empList.clear();
		for (Employee employee : employees) {
			empList.add(employee);
		}
		final ListView<Employee> viewlist = new ListView<Employee>(
				"emplistview", empList) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Employee> item) {

				final Employee employee = item.getModelObject();

				item.add(new Label("username", employee.getUsername()));
				item.add(new Label("email", employee.getEmail()));

				AjaxLink<String> editlink = new AjaxLink<String>("editlink") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						((MySession) getSession()).setEmployee(employee);

						empmodel.setContent(new EditEmployee(empmodel
								.getContentId()));
						empmodel.show(target);

					}

				};
				item.add(editlink);

				AjaxLink<String> deletelink = new AjaxLink<String>("deletelink") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(final AjaxRequestTarget target) {
						target.appendJavascript("alert('confirm delete???');");
						managerservice.deleteEmployee(employee);
						target.addComponent(empDetailsForm);

					}
				};
				item.add(deletelink);

			}
		};
		viewlist.setOutputMarkupId(true);
		empDetailsForm.add(viewlist);
	}

	private void buildPage() {
		AjaxLink<Void> addEmp = new AjaxLink<Void>("addEmp") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget arg0) {
				setResponsePage(new AddEmployee());
			}
		};
		empDetailsForm.add(addEmp);

	}

}
