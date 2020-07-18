package firstPracticalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_book;

    @Column
    private String firstName;

    @Column
    private String lastName;
}
