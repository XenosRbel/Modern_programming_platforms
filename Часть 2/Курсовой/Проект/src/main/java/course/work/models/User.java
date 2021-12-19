package course.work.models;

import course.work.models.Project;
import course.work.models.Role;
import course.work.models.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String name;
    public String username;
    public String email;
    public String password;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<Role> roles = new HashSet<>();
}
