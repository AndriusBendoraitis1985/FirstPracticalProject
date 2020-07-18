package firstPracticalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_author;

    @Column
    private String first_Name;

    @Column
    private String last_Name;

   /* @ManyToOne
    @JoinColumn(name="departmentId")
    private Department department;*/


}
