package course.work.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Getter
    @Setter
    @Column(unique=true, nullable = false)
    private String name;
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @Column(columnDefinition = "ENUM('Open', 'Closed')")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<User> users = new HashSet<>();

    @Getter
    @Setter
    @Column(nullable = false)
    private Date startAt;

    @Getter
    @Setter
    private Date endAt;

    @Getter
    @CreationTimestamp
    private Date createdAt;
    @Getter
    @UpdateTimestamp
    private Date updatedAt;
}
