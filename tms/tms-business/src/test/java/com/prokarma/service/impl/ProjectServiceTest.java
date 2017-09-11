package com.prokarma.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.prokarma.dao.ProjectDao;
import com.prokarma.domainmodel.Project;

public class ProjectServiceTest {
	private ProjectDao projectDao;
	private ProjectServiceImpl project = new ProjectServiceImpl();
	private Project project1 = new Project();

	@Test
	public void testUpdateProject() {

		project1.setProjectId(1);
		project1.setCodename("tms");
		project1.setProjectName("timesheet");

		projectDao = Mockito.mock(ProjectDao.class);
		project.setProjectdao(projectDao);

		project.updateProject(project1);

		Project project11 = new Project();
		project11.setProjectId(project1.getProjectId());
		project11.setCodename(project1.getCodename());
		project11.setProjectName(project1.getProjectName());

		ArgumentCaptor<Project> captor = ArgumentCaptor.forClass(Project.class);
		Mockito.verify(projectDao).updateProject(captor.capture());
		assertEquals(project1.getProjectId(), 1);
	}

	@Test
	public void testDeleteEmployee() {

		project1.setProjectId(1);
		project1.setCodename("tms");
		project1.setProjectName("timesheet");

		projectDao = Mockito.mock(ProjectDao.class);
		project.setProjectdao(projectDao);

		project.deleteProject(project1);

		Project project11 = new Project();
		project11.setProjectId(project1.getProjectId());
		project11.setCodename(project1.getCodename());
		project11.setProjectName(project1.getProjectName());

		ArgumentCaptor<Project> captor = ArgumentCaptor.forClass(Project.class);
		Mockito.verify(projectDao).deleteProject(captor.capture());
		assertEquals(project1.getProjectId(), 1);
	}

	@Test
	public void getProject() {

		projectDao = Mockito.mock(ProjectDao.class);
		project.setProjectdao(projectDao);
		Project project12 = new Project();
		project12.setCodename("tms");
		Mockito.when(projectDao.getProject(Matchers.anyString())).thenReturn(
				project12);
		Assert.assertEquals(project.getProject("tms").getCodename(), "tms");

	}

}