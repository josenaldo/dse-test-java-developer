package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.SalesPerson;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesPersonRepository extends JpaRepository<SalesPerson, String> {

  @Query("SELECT s FROM SalesPerson s WHERE s.salary >= :base_salary")
  List<SalesPerson> findBiggerSalaries(@Param("base_salary") BigDecimal baseSalary);

}