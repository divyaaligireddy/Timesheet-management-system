package com.prokarma.web.timesheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.prokarma.approvetimesheets.ApproveTimesheets;
import com.prokarma.employee.details.EmployeeDetails;
import com.prokarma.menus.MenuItem;
import com.prokarma.project.details.ProjectDetails;
import com.prokarma.timesheets.AddTimesheet;
import com.prokarma.timesheets.UserTimesheets;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;
	private FeedbackPanel infoFeedback;
	private FeedbackPanel warnFeedback;
	private FeedbackPanel errorFeedback;
	private WebMarkupContainer feedbackWrapper;
	protected ListView<MenuItem> mgrMenuRepeater;
	protected ListView<MenuItem> empMenuRepeater;

	public BasePage() {

		createManagerMenus();
		createEmployeeMenus();
		createFeedBackPanel();

		add(new Label("heading", "Timesheet Management System"));
		add(new Label("role", ((MySession) getSession()).getRole() + ""));
		add(new Label("name", ((MySession) getSession()).getUsername()));

		enableMenus();

		AjaxLink<Void> logout = new AjaxLink<Void>("logout") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget arg0) {
				getSession().invalidate();
				setResponsePage(LoginPage.class);
			}
		};
		add(logout);

	}

	private void createFeedBackPanel() {
		feedbackWrapper = new WebMarkupContainer("feedbackWrapper");
		feedbackWrapper.setOutputMarkupId(true);

		final IFeedbackMessageFilter infoFeedbackFilter = new IFeedbackMessageFilter() {
			private static final long serialVersionUID = 1L;

			public boolean accept(FeedbackMessage message) {
				return message.getLevel() == FeedbackMessage.INFO;
			}
		};
		infoFeedback = new FeedbackPanel("infoFeedback", infoFeedbackFilter) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return Session.get().getFeedbackMessages()
						.hasMessage(infoFeedbackFilter);
			}
		};
		final IFeedbackMessageFilter warnFeedbackFilter = new IFeedbackMessageFilter() {
			private static final long serialVersionUID = 1L;

			public boolean accept(FeedbackMessage message) {
				return message.getLevel() == FeedbackMessage.WARNING;
			}
		};
		warnFeedback = new FeedbackPanel("warnFeedback", warnFeedbackFilter) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return Session.get().getFeedbackMessages()
						.hasMessage(warnFeedbackFilter);
			}
		};
		final IFeedbackMessageFilter errorFeedbackFilter = new IFeedbackMessageFilter() {
			private static final long serialVersionUID = 1L;

			public boolean accept(FeedbackMessage message) {
				return message.getLevel() == FeedbackMessage.ERROR;
			}
		};
		errorFeedback = new FeedbackPanel("errorFeedback", errorFeedbackFilter) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return Session.get().getFeedbackMessages()
						.hasMessage(errorFeedbackFilter);
			}
		};
		feedbackWrapper.add(infoFeedback, warnFeedback, errorFeedback);
		add(feedbackWrapper);
	}

	private void createManagerMenus() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		menuItemList
				.add(new MenuItem("Employee Details", EmployeeDetails.class));
		menuItemList.add(new MenuItem("Project Details", ProjectDetails.class));
		menuItemList.add(new MenuItem("Timesheets", AddTimesheet.class));
		menuItemList.add(new MenuItem("Report", UserTimesheets.class));
		menuItemList.add(new MenuItem("Approve Timesheets",
				ApproveTimesheets.class));

		createManagerMenus(menuItemList);
	}

	private void createEmployeeMenus() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		menuItemList.add(new MenuItem("Timesheets", AddTimesheet.class));
		menuItemList.add(new MenuItem("Report", UserTimesheets.class));

		createEmployeeMenus(menuItemList);
	}

	private void createManagerMenus(List<MenuItem> menuItemList) {
		mgrMenuRepeater = new ListView<MenuItem>("mgrMenuRepeater",
				menuItemList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<MenuItem> item) {
				Link<String> menuLink = new Link<String>("menuLink") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(item.getModelObject().getPageClass());
					}
				};
				menuLink.add(new Label("menuLabel", item.getModelObject()
						.getTitle()));
				item.add(menuLink);
			}
		};
		mgrMenuRepeater.setVisible(false);
		add(mgrMenuRepeater);
	}

	private void createEmployeeMenus(List<MenuItem> menuItemList) {
		empMenuRepeater = new ListView<MenuItem>("empMenuRepeater",
				menuItemList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<MenuItem> item) {
				Link<String> menuLink = new Link<String>("menuLink") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(item.getModelObject().getPageClass());
					}
				};
				menuLink.add(new Label("menuLabel", item.getModelObject()
						.getTitle()));
				item.add(menuLink);
			}
		};
		empMenuRepeater.setVisible(false);
		add(empMenuRepeater);
	}

	private void enableMenus() {

		if (((MySession) getSession()).getRole().equals("Manager")) {

			mgrMenuRepeater.setVisible(true);
		} else if (((MySession) getSession()).getRole().equals("Employee")) {

			empMenuRepeater.setVisible(true);
		} else {
			mgrMenuRepeater.setVisible(false);
			empMenuRepeater.setVisible(false);
		}

	}

	protected void refreshFeedbackPanel(AjaxRequestTarget target) {
		target.addComponent(feedbackWrapper);
	}
}
