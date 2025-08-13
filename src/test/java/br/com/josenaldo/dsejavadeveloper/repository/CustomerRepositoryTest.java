package br.com.josenaldo.dsejavadeveloper.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.josenaldo.dsejavadeveloper.annotations.IntegrationTest;
import br.com.josenaldo.dsejavadeveloper.entity.Customer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@IntegrationTest
@Testcontainers
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Container
  private static final MySQLContainer MY_SQL_CONTAINER =
      new MySQLContainer("mysql:8")
          .withDatabaseName("dsedb")
          .withUsername("root")
          .withPassword("root");

  @DynamicPropertySource
  public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
    final var port = MY_SQL_CONTAINER.getMappedPort(3306);
    final var url = MY_SQL_CONTAINER.getJdbcUrl();
    final var username = MY_SQL_CONTAINER.getUsername();
    final var password = MY_SQL_CONTAINER.getPassword();
    final var driverClassName = MY_SQL_CONTAINER.getDriverClassName();

    System.out.printf("- Container is running on url: %s\n", url);
    System.out.printf("- Container is running on port: %s\n", port);
    System.out.printf("- Container is running on username: %s\n", username);
    System.out.printf("- Container is running on password: %s\n", password);
    System.out.printf("- Container is running on driverClassName: %s\n", driverClassName);

    registry.add("mysql.port", port::toString);
  }

  @BeforeEach
  void setUp() {
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());
  }

  @Test
  void givenAValidCustomer_whenListAllCustomers_thenReturnListOfCustomers() {
    // Arrange - Given

    // Act - When
    final var result = customerRepository.findAll();

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(5);
  }

  @Test
  void givenAValidCustomerUsername_whenFindByUsername_thenReturnCustomer() {
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    final var expectedUsername = "samony";

    // Act - When
    Customer result = customerRepository.findByUsername(expectedUsername);

    // Assert - Then
    assertNotNull(result);
    assertThat(result.getUsername()).isEqualTo(expectedUsername);
  }

  @Test
  void givenAValidCustomerNamePart_whenFindByName_thenReturnListOfCustomers() {
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    final var expectedNamePart = "Sa%";

    // Act - When
    List<Customer> result = customerRepository.findByNameLikeIgnoreCase(expectedNamePart);

    // Assert - Then
    assertNotNull(result);
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(2);
    assertThat(result.get(0).getName()).isEqualTo("Samsonic");
    assertThat(result.get(1).getName()).isEqualTo("Samony");
  }

  @Test
  void givenAValidEmail_whenFindByEmail_thenReturnTheCustomer(){
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    final var expectedEmail = "hello@samony.example";

    // Act - When
    Customer result = customerRepository.findByEmail(expectedEmail);

    // Assert - Then
    assertNotNull(result);
    assertThat(result.getEmail()).isEqualTo(expectedEmail);
    assertThat(result.getName()).isEqualTo("Samony");
    assertThat(result.getUsername()).isEqualTo("samony");
  }
}