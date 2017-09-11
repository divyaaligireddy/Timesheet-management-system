package com.prokarma.project.details;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.prokarma.domainmodel.Project;
import com.prokarma.service.ManagerService;
import com.prokarma.service.ProjectService;
import com.prokarma.web.timesheet.BasePage;
import com.prokarma.web.timesheet.MySession;

public class ProjectDetails extends BasePage {
	@SpringBean
	private ManagerService managerservice;
	@SpringBean
	private ProjectService projectservice;

	private Form<Object> projDetails = new Form<Object>("projDetails");

	ModalWindow projmodel = null;

	public ProjectDetails() {

		buildPage();
		listView();

	}

	private void buildPage() {
		add(projDetails);
		AjaxLink<Void> editproject = new AjaxLink<Void>("editproject") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget arg0) {

				setResponsePage(new AddProject());
			}
		};
		projDetails.add(editproject);

	}

	private void listView() {

		projDetails.add(projmodel = new ModalWindow("projmodel"));

		projmodel.setTitle("Edit Employee");
		projmodel.setInitialWidth(500);
		projmodel.setInitialHeight(500);
		projmodel.setWidthUnit("px");
		projmodel.setHeightUnit("px");
		projmodel.setResizable(false);

		projmodel.setCookieName("modal-2");

		projmodel.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {

			private static final long serialVersionUID = 1L;

			public boolean onCloseButtonClicked(final AjaxRequestTarget target) {
				// setResult("closed");
				return true;
			}
		});

		projmodel
				.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
					private static final long serialVersionUID = 1L;

					public void onClose(final AjaxRequestTarget target) {
						// target.addComponent(result);
					}
				});

		List<Project> projList = managerservice.getProjectDetails();

		final ListView<Project> viewlist = new ListView<Project>(
				"projlistview", projList) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Project> item) {

				final Project project = item.getModelObject();

				item.add(new Label("codename", project.getCodename()));
				item.add(new Label("projname", project.getProjectName()));

				AjaxLink<String> editlink = buildEditLink(project);
				item.add(editlink);

				AjaxLink<String> deletelink = buildDeleteLink(project);
				item.add(deletelink);
			}
		};
		viewlist.setOutputMarkupId(true);
		projDetails.add(viewlist);
	}

	private AjaxLink<String> buildEditLink(final Project project) {
		AjaxLink<String> editlink = new AjaxLink<String>("editlink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				((MySession) getSession()).setProject(project);

				projmodel.setContent(new EditProject(projmodel.getContentId()));
				projmodel.show(target);

			}

		};
		return editlink;
	}

	private AjaxLink<String> buildDeleteLink(final Project project) {
		AjaxLink<String> deletelink = new AjaxLink<String>("deletelink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {

				projectservice.deleteProject(project);

			}

		};
		return deletelink;
	}

}
