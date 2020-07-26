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
@ToString
@NoArgsConstructor

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_author;

    @Column
    private String first_Name;

    @Column
    private String last_Name;

    public Author(String first_Name, String last_Name) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
    }

    public Author(int id_author, String first_Name, String last_Name) {
        this.id_author = id_author;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
    }
}


/* @ManyToOne
    @JoinColumn(name="departmentId")
    private Department department;*/



