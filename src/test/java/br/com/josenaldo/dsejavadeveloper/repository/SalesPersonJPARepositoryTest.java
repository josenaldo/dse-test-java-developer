package br.com.josenaldo.dsejavadeveloper.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.josenaldo.dsejavadeveloper.annotations.IntegrationTest;
import br.com.josenaldo.dsejavadeveloper.entity.SalesPerson;
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
class SalesPersonJPARepositoryTest {

  @Autowired
  private SalesPersonJPARepository salesPersonJPARepository;

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
    assertThat(5).isEqualTo(salesPersonJPARepository.count());
  }

  @Test
  void givenAValidListOfSalesPerson_whenFindAll_thenReturnListOfSalesPerson() {
    // Arrange - Given

    // Act - When
    final var result = salesPersonJPARepository.findAll();

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(5);
  }

  @Test
  void givenAValidSalary_whenFindBiggerSalaries_thenShouldReturnTwoSalesPerson(){
    // Arrange - Given
    final var expectedBaseSalary = new BigDecimal(100000);

    // Act - When
    final var result = salesPersonJPARepository.findBiggerSalaries(expectedBaseSalary);

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(2);

    SalesPerson first = result.getFirst();
    assertThat("Abe").isEqualTo(first.getName());

    SalesPerson second = result.getLast();
    assertThat("Ken").isEqualTo(second.getName());
  }

  @Test
  void givenSalesPersonList_whenFindOlderThan60_thenShouldReturnOneSalesPerson(){
    // Arrange - Given

    // Act - When
    final var result = salesPersonJPARepository.findOlderThan60();

    // Assert - Then
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(1);

    SalesPerson first = result.getFirst();
    assertThat("Abe").isEqualTo(first.getName());
  }
}