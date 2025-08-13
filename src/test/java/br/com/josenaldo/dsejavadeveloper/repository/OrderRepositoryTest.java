package br.com.josenaldo.dsejavadeveloper.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.josenaldo.dsejavadeveloper.annotations.IntegrationTest;
import br.com.josenaldo.dsejavadeveloper.entity.Order;
import java.math.BigDecimal;
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
class OrderRepositoryTest {
  @Autowired
  private OrderRepository orderRepository;

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
    assertThat(6).isEqualTo(orderRepository.count());
  }

  @Test
  void givenAValidListOfOrdewrs_whenFindAll_thenReturnListOfOrders() {
    // Arrange - Given

    // Act - When
    final var customers = orderRepository.findAll();

    // Assert - Then
    assertThat(customers).isNotNull();
    assertThat(customers).isNotEmpty();
    assertThat(customers).hasSize(6);
  }

  @Test
  void givenAValidCustomerUsername_whenFindByCustomerUsername_thenReturnListOfOrders() {
    // Arrange - Given
    final var expectedUsername = "samony";

    // Act - When
    final var result = orderRepository.findAll(OrderRepository.byCustomerUsername(expectedUsername));

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(2);

    final var firstOrder = result.getFirst();
    final var expectedFirstValue = new BigDecimal("2400.00");
    String expectedFirstCustomerId = "33333333-4444-5555-6666-777777777777";

    assertThat(expectedFirstValue).isEqualTo(firstOrder.getAmount());
    assertThat(expectedFirstCustomerId).isEqualTo(firstOrder.getCustomer().getId());

    final var secondOrder = result.getLast();
    final var expectedSecondValue = new BigDecimal("1200.00");
    String expectedSecondCustomerId = "33333333-4444-5555-6666-777777777777";

    assertThat(expectedSecondCustomerId).isEqualTo(secondOrder.getCustomer().getId());
    assertThat(expectedSecondValue).isEqualTo(secondOrder.getAmount());
  }

  @Test
  void givenAValidSalesPersonId_whenFindBySalesPersonId_thenReturnListOfOrders() {
    // Arrange - Given
    final var expectedId = "9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c44";

    // Act - When
    final var result = orderRepository.findAll(OrderRepository.bySalesPerson(expectedId));

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(2);

    final var firstOrder = result.getFirst();
    final var expectedFirstValue = new BigDecimal("600.00");
    String expectedFirstCustomerId = "22222222-3333-4444-5555-666666666666";

    assertThat(expectedFirstValue).isEqualTo(firstOrder.getAmount());
    assertThat(expectedFirstCustomerId).isEqualTo(firstOrder.getCustomer().getId());

    final var secondOrder = result.getLast();
    final var expectedSecondValue = new BigDecimal("1200.00");
    String expectedSecondCustomerId = "33333333-4444-5555-6666-777777777777";

    assertThat(expectedSecondCustomerId).isEqualTo(secondOrder.getCustomer().getId());
    assertThat(expectedSecondValue).isEqualTo(secondOrder.getAmount());
  }

  @Test
  void givenAValidSalesPersonIdAndCustomerUsername_whenFindBySalesPersonAndCustomerUsername_thenReturnListOfOrders() {
    // Arrange - Given
    final var expectedId = "9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c44";
    final var expectedUsername = "samony";

    // Act - When
    final var result = orderRepository.findAll(OrderRepository.bySalesPersonAndCustomerUsername(expectedId, expectedUsername));

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(1);

    final var firstOrder = result.getFirst();
    final var expectedFirstValue = new BigDecimal("1200.00");
    String expectedFirstCustomerId = "33333333-4444-5555-6666-777777777777";

    assertThat(expectedFirstValue).isEqualTo(firstOrder.getAmount());
    assertThat(expectedFirstCustomerId).isEqualTo(firstOrder.getCustomer().getId());
  }

}