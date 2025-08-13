package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, String>,
    JpaSpecificationExecutor<Order> {

  static Specification<Order> byCustomerUsername(String username) {
    return (root, query, cb) -> cb.equal(root.join("customer").get("username"), username);
  }

  public static Specification<Order> bySalesPerson(String salesPersonId) {
    return (root, query, cb) ->cb.equal(root.join("salesperson").get("id"), salesPersonId);
  }
}