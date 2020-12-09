package io.nit.ppmtool.web;

import io.nit.ppmtool.domain.Project;
import io.nit.ppmtool.services.MapValidationErrorService;
import io.nit.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
@Autowired
    private ProjectService projectService;
@Autowired
private MapValidationErrorService mapValidationErrorService;

@PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

ResponseEntity<?> errorMap = mapValidationErrorService.MapValidaionService(result);
if(errorMap!=null) return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project, HttpStatus.CREATED);
}

@GetMapping("/{ProjectId}")
    public ResponseEntity<?> findProjectbyId(@PathVariable String ProjectId){

    Project project  = projectService.findProjectByIdentifier(ProjectId);
    return new ResponseEntity<Project>(project,HttpStatus.OK);
}

@GetMapping("/all")
    public Iterable<Project> getAllProjects()
{
    return projectService.findAllProject();
}
@DeleteMapping("/{ProjectId}")
    public ResponseEntity<?> deleteProject(@PathVariable  String ProjectId)
{
    projectService.deleteProjectById(ProjectId);
    return new ResponseEntity<String>("Successfully deleted the project with Id:"+ProjectId,HttpStatus.OK);
}
}
