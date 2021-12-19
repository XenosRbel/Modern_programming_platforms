package course.work.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    @Column(columnDefinition = "ENUM('User', 'ProjectManager', 'Admin')")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<User> users = new HashSet<>();
}
