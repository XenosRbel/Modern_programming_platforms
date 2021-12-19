package course.work.requests;

import course.work.models.Project;
import lombok.Getter;
import lombok.Setter;

public class ProjectCreate extends Project {
    @Getter
    @Setter
    public int user_id;
}
