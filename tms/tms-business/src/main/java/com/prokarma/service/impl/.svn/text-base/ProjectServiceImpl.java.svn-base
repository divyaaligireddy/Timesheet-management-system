package com.prokarma.service.impl;

import java.util.List;

import com.prokarma.dao.ProjectDao;
import com.prokarma.domainmodel.Project;
import com.prokarma.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDao projectdao;


	public void deleteProject(Project project) {
		projectdao.deleteProject(project);
	}

	public List<String> getEmployees(Project project) {
		return projectdao.getEmployees(project);
	}

	public Project getProject(String codename) {
		return projectdao.getProject(codename);
	}

	public void updateProject(final Project p) {
		projectdao.updateProject(p);
	}
	
	public ProjectDao getProjectdao() {
		return projectdao;
	}

	public void setProjectdao(ProjectDao projectdao) {
		this.projectdao = projectdao;
	}

	public String getProjectCode(int projectId) {
		return projectdao.getProjectCode(projectId);
	}
}
