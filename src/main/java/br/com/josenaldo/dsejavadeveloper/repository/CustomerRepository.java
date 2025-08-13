package br.com.josenaldo.dsejavadeveloper.repository;

import br.com.josenaldo.dsejavadeveloper.entity.Customer;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, String>{

  Customer findByUsername(String username);

  Customer findByEmail(String email);

  List<Customer> findByNameLike(String name);
}