package course.work.models;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Setter;
import org.w3c.dom.Text;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "task")
public class Task {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Getter
    @Setter
    @Min(0)
    @Column(columnDefinition = "float default 0.0")
    private Float estimation;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @Column(columnDefinition = "ENUM('NotStarted', 'InProgress', 'Submitted', 'Closed')")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Getter
    @Setter
    @Column(columnDefinition="TEXT")
    private String description;

    @Getter
    @Setter
    private String priority;

    @Getter
    @Setter
    private float workTime;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private User user;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Note> notes = new HashSet<>();

    @Getter
    @Transient
    private String noteText;

    @Getter
    @Setter
    @Column(nullable = true)
    private Date startAt;
    @Getter
    @Setter
    private Date dueAt;

    @Getter
    @CreationTimestamp
    private Date createdAt;
    @Getter
    @UpdateTimestamp
    private Date updatedAt;
}
