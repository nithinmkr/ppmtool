package io.nit.ppmtool.services;

import io.nit.ppmtool.domain.Project;
import io.nit.ppmtool.exceptions.ProjectIdException;
import io.nit.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project)
    {
        try {
            return  projectRepository.save(project);
        }catch (Exception e)
        {
           throw new ProjectIdException("Project ID "+project.getProjectIdentifier()+" is already exist");
        }


    }
    public Project findProjectByIdentifier(String ProjectId)
    {
        Project project = projectRepository.findByProjectIdentifier(ProjectId);

        if(project==null)
        {
            throw new ProjectIdException("Project ID "+ProjectId+"  does not  exist");
        }
        return project;
    }
    public Iterable<Project> findAllProject()
    {
        return projectRepository.findAll();
    }
    public void deleteProjectById(String ProjectId)
    {
        Project project = projectRepository.findByProjectIdentifier(ProjectId);

        if(project==null)
        {
            throw new ProjectIdException("Cant delete project. Project ID "+ProjectId+"  does not  exist");
        }

        projectRepository.delete(project);
    }
}
