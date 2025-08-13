package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, String>,
    JpaSpecificationExecutor<Order> {

  static Specification<Order> byCustomerUsername(String username) {
    return (root, query, cb) -> cb.equal(root.join("customer").get("username"), username);
  }

  public static Specification<Order> bySalesPerson(String salesPersonId) {
    return (root, query, cb) ->cb.equal(root.join("salesperson").get("id"), salesPersonId);
  }
}