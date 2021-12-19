package course.work.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;


@Entity
@Table(name = "note")
public class Note {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Getter
    @Setter
    @Column(columnDefinition="TEXT")
    private String text;

    @Getter
    @CreationTimestamp
    private Date createdAt;
    @Getter
    @UpdateTimestamp
    private Date updatedAt;
}
