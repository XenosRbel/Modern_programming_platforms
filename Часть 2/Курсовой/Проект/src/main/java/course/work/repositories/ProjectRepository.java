package course.work.repositories;

import course.work.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByCreatedAtBetween(
            Date createdAtBefore,
            Date createdAtAfter
    );
}
