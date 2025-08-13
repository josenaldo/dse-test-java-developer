package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

  Customer findByUsername(String username);

  Customer findByEmail(String email);

  List<Customer> findByNameLikeIgnoreCase(String name);
}