package br.com.josenaldo.dsejavadeveloper.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.josenaldo.dsejavadeveloper.annotations.IntegrationTest;
import br.com.josenaldo.dsejavadeveloper.entity.Customer;
import java.util.List;
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

  @Test
  void givenAValidCustomer_whenListAllCustomers_thenReturnListOfCustomers() {
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    // Act - When
    final var customers = customerRepository.findAll();

    // Assert - Then
    assertThat(customers).isNotNull();
    assertThat(customers).isNotEmpty();
    assertThat(customers).hasSize(5);
  }

  @Test
  void givenAValidCustomerUsername_whenFindByUsername_thenReturnCustomer() {
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    final var expectedUsername = "samony";

    // Act - When
    Customer customer = customerRepository.findByUsername(expectedUsername);

    // Assert - Then
    assertNotNull(customer);
    assertThat(customer.getUsername()).isEqualTo(expectedUsername);
  }

  @Test
  void givenAValidCustomerNamePart_whenFindByName_thenReturnListOfCustomers() {
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    final var expectedNamePart = "Sa%";

    // Act - When
    List<Customer> customers = customerRepository.findByNameLikeIgnoreCase(expectedNamePart);

    // Assert - Then
    assertNotNull(customers);
    assertThat(customers).isNotEmpty();
    assertThat(customers).hasSize(2);
    assertThat(customers.get(0).getName()).isEqualTo("Samsonic");
    assertThat(customers.get(1).getName()).isEqualTo("Samony");
  }

  @Test
  void givenAValidEmail_whenFindByEmail_thenReturnTheCustomer(){
    // Arrange - Given
    assertThat(MY_SQL_CONTAINER.isRunning()).isTrue();
    assertThat(5).isEqualTo(customerRepository.count());

    final var expectedEmail = "hello@samony.example";

    // Act - When
    Customer customer = customerRepository.findByEmail(expectedEmail);

    // Assert - Then
    assertNotNull(customer);
    assertThat(customer.getEmail()).isEqualTo(expectedEmail);
    assertThat(customer.getName()).isEqualTo("Samony");
    assertThat(customer.getUsername()).isEqualTo("samony");
  }
}