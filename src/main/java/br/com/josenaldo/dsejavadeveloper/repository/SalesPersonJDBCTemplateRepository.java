package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.SalesPerson;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class SalesPersonJDBCTemplateRepository implements SalesPersonRepository {

  private final JdbcTemplate jdbcTemplate;

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public SalesPersonJDBCTemplateRepository(
      JdbcTemplate jdbcTemplate,
      NamedParameterJdbcTemplate namedParameterJdbcTemplate
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  public long count() {
    String sql = "SELECT COUNT(*) FROM sales_person";
    return jdbcTemplate.queryForObject(sql, Long.class);
  }

  public List<SalesPerson> findAll() {
    String sql = "SELECT * FROM sales_person";

    return jdbcTemplate.query(sql, new SalesPersonRowMapper());
  }

  @Override
  public List<SalesPerson> findBiggerSalaries(BigDecimal baseSalary) {
    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(
        "baseSalary",
        baseSalary
    );

    String sql = """
          SELECT s.id, s.name, s.age, s.salary
          FROM sales_person s
          WHERE s.salary > :baseSalary
        """;

    return this.namedParameterJdbcTemplate.query(
        sql,
        namedParameters,
        new SalesPersonRowMapper()
    );
  }

  @Override
  public List<SalesPerson> findOlderThan60() {
    String sql = "SELECT * FROM sales_person s WHERE s.age > 60";

    return jdbcTemplate.query(sql, new SalesPersonRowMapper());
  }
}

class SalesPersonRowMapper implements RowMapper<SalesPerson> {

  @Override
  public SalesPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
    SalesPerson salesPerson = new SalesPerson();

    salesPerson.setId(rs.getString("id"));
    salesPerson.setName(rs.getString("name"));
    salesPerson.setAge(rs.getShort("age"));
    salesPerson.setSalary(rs.getBigDecimal("salary"));

    return salesPerson;
  }
}
