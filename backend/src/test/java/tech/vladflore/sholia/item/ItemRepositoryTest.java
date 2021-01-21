package tech.vladflore.sholia.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tech.vladflore.sholia.config.InsertDummyItemsIntoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers(disabledWithoutDocker = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(InsertDummyItemsIntoDatabase.class)
class ItemRepositoryTest {

	@Autowired
	private ItemRepository cut;

	// will be shared between test methods
	@Container
	private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:9.6.19-alpine")
			.withDatabaseName("test").withUsername("test").withPassword("t3st");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@Test
	@DisplayName("Should be able to save data into the database and read it out")
	void shouldSaveAndReadOutData() {
		assertThat(cut.findAll().size()).isEqualTo(75);
	}

}
