package com.prokarma.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.domainmodel.Project;


public interface ProjectService {

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteProject(Project project);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public List<String> getEmployees(Project project);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public Project getProject(String codename);
	
	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public String getProjectCode(int projectId);

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateProject(final Project p);

	
}
