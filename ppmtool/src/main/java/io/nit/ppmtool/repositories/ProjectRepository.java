package io.nit.ppmtool.repositories;

import io.nit.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {


    Project findByProjectIdentifier(String ProjectId);

    @Override
    Iterable<Project> findAll();
}
