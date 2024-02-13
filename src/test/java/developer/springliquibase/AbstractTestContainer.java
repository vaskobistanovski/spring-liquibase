package developer.springliquibase;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
public abstract class AbstractTestContainer {

    public static PostgreSQLContainer database = new PostgreSQLContainer(
            DockerImageName.parse("postgres:15-alpine")
                    .asCompatibleSubstituteFor("postgres")
    )
            .withDatabaseName("db")
            .withUsername("test_user")
            .withPassword("test_password");

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", database::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", database::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", database::getDriverClassName);
    }

    static {
        database.start();
    }
}
