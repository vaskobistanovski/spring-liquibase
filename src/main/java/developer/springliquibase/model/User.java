package developer.springliquibase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
    @SequenceGenerator(name="user_seq_generator", sequenceName = "user_seq", allocationSize = 1)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Email> emails;
}
