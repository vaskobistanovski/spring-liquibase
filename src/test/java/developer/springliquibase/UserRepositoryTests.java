package developer.springliquibase;

import developer.springliquibase.model.Email;
import developer.springliquibase.model.User;
import developer.springliquibase.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UserRepositoryTests extends AbstractTestContainer {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testInsertUser() {

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
    }

    @Test
    public void testDeleteUser() {

        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");

        userRepository.save(user);

        userRepository.delete(user);

        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void testUpdateUser() {

        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");

        userRepository.save(user);

        user.setFirstName("John");
        user.setLastName("Teddy");

        userRepository.save(user);

        assertTrue(userRepository.findById(user.getId()).isPresent());
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Teddy");
    }

    @Test
    public void testInsertUserWithEmail() {

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        List<Email> emails = new ArrayList<>();

        Email email = new Email();
        email.setEmail("john_doe@gmail.com");

        emails.add(email);

        user.setEmails(emails);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());

        assertEquals(1, savedUser.getEmails().size());
        assertNotNull(savedUser.getEmails().get(0));

        Email savedEmail = savedUser.getEmails().get(0);

        assertEquals("john_doe@gmail.com", savedEmail.getEmail());
    }

    @Test
    public void testInsertUserWithMultipleEmails() {

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        List<Email> emails = new ArrayList<>();

        Email email1 = new Email();
        email1.setEmail("email_1@gmail.com");

        Email email2 = new Email();
        email2.setEmail("email_2@gmail.com");

        Email email3 = new Email();
        email3.setEmail("email_3@gmail.com");

        emails.add(email1);
        emails.add(email2);
        emails.add(email3);

        user.setEmails(emails);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());

        assertEquals(3, savedUser.getEmails().size());

        Email savedEmail1 = savedUser.getEmails().get(0);
        Email savedEmail2 = savedUser.getEmails().get(1);
        Email savedEmail3 = savedUser.getEmails().get(2);

        assertNotNull(savedEmail1.getId());
        assertNotNull(savedEmail2.getId());
        assertNotNull(savedEmail3.getId());

        assertEquals("email_1@gmail.com", savedEmail1.getEmail());
        assertEquals("email_2@gmail.com", savedEmail2.getEmail());
        assertEquals("email_3@gmail.com", savedEmail3.getEmail());
    }
}
