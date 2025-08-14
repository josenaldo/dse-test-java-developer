package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.SalesPerson;
import java.math.BigDecimal;
import java.util.List;

public interface SalesPersonRepository {

  public long count();

  List<SalesPerson> findAll();

  List<SalesPerson> findBiggerSalaries(BigDecimal baseSalary);

  List<SalesPerson> findOlderThan60();
}
