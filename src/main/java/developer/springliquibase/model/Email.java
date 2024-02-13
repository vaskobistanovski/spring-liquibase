package developer.springliquibase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "EMAILS")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_seq_generator")
    @SequenceGenerator(name="email_seq_generator", sequenceName = "email_seq", allocationSize = 1)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
