package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.SalesPerson;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class SalesPersonJDBCRepository implements SalesPersonRepository {

  private DataSource dataSource;
  private JdbcClient jdbcClient;

  public SalesPersonJDBCRepository(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcClient = JdbcClient.create(dataSource);
  }

  @Override
  public long count() {
    final var sql = "SELECT COUNT(*) FROM sales_person";
    return this.jdbcClient.sql(sql).query(Long.class).single();
  }

  @Override
  public List<SalesPerson> findAll() {
    String sql = "SELECT * FROM sales_person";
    try (
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)
    ) {
      List<SalesPerson> results = new ArrayList<>();

      while (resultSet.next()) {
        results.add(mapResultSetToSalesPerson(resultSet));
      }
      return results;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public List<SalesPerson> findBiggerSalaries(BigDecimal baseSalary) {
    String sql = """
          SELECT s.id, s.name, s.age, s.salary
          FROM sales_person s
          WHERE s.salary > ?
        """;

    try (
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)
    ) {
      preparedStatement.setBigDecimal(1, baseSalary);

      ResultSet resultSet = preparedStatement.executeQuery();

      List<SalesPerson> results = new ArrayList<>();

      while (resultSet.next()) {
        results.add(mapResultSetToSalesPerson(resultSet));
      }
      return results;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<SalesPerson> findOlderThan60() {
    String sql = """
          SELECT s.id, s.name, s.age, s.salary
          FROM sales_person s
          WHERE s.age > 60
        """;

    try (
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)
    ) {

      ResultSet resultSet = preparedStatement.executeQuery();

      List<SalesPerson> results = new ArrayList<>();

      while (resultSet.next()) {
        results.add(mapResultSetToSalesPerson(resultSet));
      }
      return results;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static SalesPerson mapResultSetToSalesPerson(ResultSet resultSet) throws SQLException {
    String id = resultSet.getString("id");
    String name = resultSet.getString("name");
    Short age = resultSet.getShort("age");
    BigDecimal salary = resultSet.getBigDecimal("salary");
    SalesPerson e = new SalesPerson(id, name, age, salary);
    return e;
  }
}
