
# DSE - Test - Java Programming

## Question 1

> Write a Java program to solve the following problem:
> 
> You are tasked with creating a utility function for a text-processing application. The
> function must generate all possible anagrams from a given group of distinct letters. For
> example, the input {a, b, c} should return the output: abc, acb, bac, bca, cab, cba.
>
> Additional Requirements:
> 
> 1. The program should accept any group of distinct letters as input and produce the
> correct result.
> 2. Optimize for readability and clarity.
> 3. Include basic validation (e.g., ensure the input is non-empty and contains 
> only letters).
> 4. Write unit tests to validate your function with at least three different test cases,
> including edge cases (e.g., input with a single letter or empty input).
> 5. Document your code clearly, explaining the logic for generating anagrams.

### Answer 1

See the code in the following files:

- [AnagramGenerator.java file](src/main/java/br/com/josenaldo/dsejavadeveloper/AnagramGenerator.java)
- [AnagramGeneratorTest.java file](src/test/java/br/com/josenaldo/dsejavadeveloper/AnagramGeneratorTest.java)

## Question 2

> Provide an example scenario where overriding the equals() method is necessary in Java.
> Explain the key considerations when implementing this method, such as ensuring it
> aligns with the hashCode() method. Include code examples if possible.

### Answer 2

In Java, the equals() method is used to compare two objects. The default implementation
of this method compares the references of the objects. If you want to compare the
contents of the objects, you must override this method.

This can be useful when you want to compare two objects representing an entity (an entity is a class that
represents a single record and has a unique identifier). In this case, you can compare the ID of the
entities and consider that two entities are equal if they have the same ID.

```java
import java.util.Objects;

class Employee {

  private String id;
  private String name;
  private String email;

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    Employee other = (Employee) obj;
    return Objects.equals(id, other.id);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
```

Another case where you can override the equals() method is when you want to compare two objects
that are not entities. For example, when using value objects. A value object is an object that
represents a value, and it doesn't have an ID. In that case, you can compare each field of the
value object.

```java
public class Address {

  private String street;
  private String city;
  private String state;
  private String zipCode;

  public Address(String street, String city, String state, String zipCode) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(street, address.street)
        && Objects.equals(city, address.city)
        && Objects.equals(state, address.state)
        && Objects.equals(zipCode, address.zipCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, city, state, zipCode);
  }
}    
```

We must consider that the equals() method must be consistent with the hashCode() method, i.e.,
if two objects are equal, they must have the same hash code.

Additionally, we must ensure that the equals() method must respect the contract of the equals()
method, i.e., it must be: reflexive, symmetric, transitive, and consistent. 

## Question 3

> Explain how you would use a design pattern to decouple your code from a third-party
> library that might be replaced in the future. Describe the advantages and limitations of
> your chosen approach, and provide a small code snippet illustrating its application.

### Answer 3

In cases like that, my most used approach is to use the Adapter pattern. This pattern is used
to decouple the code from the third-party libraries, framework, or other systems. This way,
we can change the third-party library or framework without affecting the client code.

To implement this pattern, we need to create an abstraction that represents the external component. 
For example, we can create an interface that defines a Repository (an abstraction for a database), 
an ApiClient (an abstraction for an API) or a FileReader (an abstraction for a file).

After that, we can create a concrete implementation of the interface that represents the external
component. For example, we can create a class that implements the Repository interface and uses
the JDBC API to access the MySQL database.

Finally, we can create a class that uses the Repository interface to access the external component. 
This class is called the Client. Ideally, this class should be independent of the concrete implementation
of the Repository interface. I.e., the Client should not know anything about the concrete implementation
of the Repository interface. We can achieve this by using the Dependency Injection pattern, by using
the Factory pattern, or by using the Registry pattern.

```java
public interface Repository {

  void save(Object object);

  void update(Object object);

  void delete(Object object);

  Object findById(String id);

  List<Object> findAll();
}

public class MysqlRepository implements Repository {

  private Connection connection;

  @Override
  public void save(Object object) {
    // specific MySQL implementation
  }
  
  // ...(other methods)
}

public class ClientService {
  private Repository repository;
  
  // injecting the repository by using the constructor injection
  public ClientService(Repository repository) {
    this.repository = repository;
  }
  
  public void save(Object object) {
    // ... (other code)
    repository.save(object);
    // ... (other code)
  }
  
  // ...(other methods)
}
```

This approach is commonly used in the Hexagonal Architecture (Ports and Adapters), to decouple the
domain code from the infrastructure and external systems.

## Question 4

> Describe your experience with Angular, including its core features and use cases. Provide
> an example of a practical application where you used Angular and include a code snippet
> demonstrating a key feature, such as component communication, data binding, or
> service integration.

### Answer 4

I don't have any experience with Angular. However, I have used React, Bootstrap, and other frameworks
to build web applications. Due to this experience, I can say that I have confidence that I can learn 
Angular quickly.

## Question 5

> Discuss the techniques you use to prevent SQL injection attacks in web applications.
> Provide examples of code showing secure implementations, such as using parameterized
> queries or ORMs. Mention any additional measures you take to secure the database
> layer.

### Answer 5

The problem with SQL injection attacks is that it is possible to inject SQL commands into the
application. This can be done, for example, by injecting malicious code into the application input.

To prevent this, we can use a series of techniques:

- Use your tools correctly. Let the right layer of abstraction handle the SQL commands (from the 
 highest to the lowest level of abstraction)
  - Spring Data query methods.
  - @Query annotations with JPQL/HQL.
  - Criteria API.
  - Native queries over @Query annotations 
  - Native queries over JDBCTemplate.
  - Native queries over JDBC directly, using prepared statements and parameterized queries.
  - Never construct SQL queries by concatenating strings with inputs directly.
- Always sanitize and validate user input
  - Use your framework (like Spring MVC) to extract the input data from the request. That way, you 
    can validate the input data directly in the controller.
  - In Java, use the @Valid annotation and the Bean Validation API, to validate the input 
- Give, to your application, the minimum permissions they need to perform their tasks.

### Examples of secure implementations:

#### Spring Data query methods.

```java
public interface CustomerRepository extends JpaRepository<Customer, String> {

  Customer findByUsername(String username);

  Customer findByEmail(String email);

  List<Customer> findByNameLikeIgnoreCase(String name);
}
```

#### @Query annotations with JPQL/HQL.

```java
public interface SalesPersonRepository extends JpaRepository<SalesPerson, String> {

  @Query("SELECT s FROM SalesPerson s WHERE s.salary >= :base_salary")
  List<SalesPerson> findBiggerSalaries(@Param("base_salary") BigDecimal baseSalary);

}
```

#### Criteria API.

```java
public interface OrderRepository extends JpaRepository<Order, String>,
    JpaSpecificationExecutor<Order> {

  static Specification<Order> byCustomerUsername(String username) {
    return (root, query, cb) -> cb.equal(root.join("customer").get("username"), username);
  }

  static Specification<Order> bySalesPerson(String salesPersonId) {
    return (root, query, cb) ->cb.equal(root.join("salesperson").get("id"), salesPersonId);
  }

  static Specification<Order> bySalesPersonAndCustomerUsername(String salesPersonId, String  username) {
    Specification<Order> bySalesPerson = bySalesPerson(salesPersonId);
    Specification<Order> byCustomerUsername = byCustomerUsername(username);

    return (root, query, cb) -> cb.and(bySalesPerson.toPredicate(root, query, cb), byCustomerUsername.toPredicate(root, query, cb));
  }
}
```

```java
// bySalesPerson
final var result = orderRepository.findAll(OrderRepository.bySalesPerson(expectedId));

// byCustomerUsername
final var result = orderRepository.findAll(OrderRepository.byCustomerUsername(expectedUsername));

// bySalesPersonAndCustomerUsername
final var result = orderRepository.findAll(OrderRepository.bySalesPersonAndCustomerUsername(expectedId, expectedUsername));
```

#### Native queries over @Query annotations

```java
@Repository
public class CustomerRepository {
  
  @Query(value = "SELECT * FROM customer WHERE username = :username", nativeQuery = true)
  public Customer findByUsername(@Param("username") String username) {
    // ...
  }
}
````

#### Native queries over JDBCTemplate.
    
```java
@Repository
public class CustomerRepository {
    
  private JdbcTemplate jdbcTemplate;
  
  public CustomerRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
  
  public Customer findByUsername(String username) {
    String sql = "SELECT * FROM customer WHERE username = ?";
    List<Customer> customers = jdbcTemplate.query(sql, new Object[] {username}, new CustomerMapper());
    return customers.isEmpty() ? null : customers.get(0);
  }
}
```


    - Native queries over JDBC directly, using prepared statements and parameterized queries.
    - Never construct SQL queries by concatenating strings with inputs directly.

## Question 6

Describe the steps you would take to diagnose and improve the performance of a batch
process that interacts with a database and an FTP server. Explain how you would identify
bottlenecks, optimize database queries, improve logic execution, and enhance file
transfer efficiency. Provide examples of tools or techniques you would use during the
analysis.

## Question 7

Salesperson

| ID | Name  | Age | Salary |
|----|-------|-----|--------|
| 1  | Abe   | 61  | 140000 |
| 2  | Bob   | 34  | 44000  |
| 5  | Chris | 34  | 40000  |
| 7  | Dan   | 41  | 52000  |
| 8  | Ken   | 57  | 115000 |
| 11 | Joe   | 38  | 38000  |

Customer

| ID | Name     | City     | Industry Type |
|----|----------|----------|---------------|
| 4  | Samsonic | Pleasant | J             |
| 6  | Panasung | Oaktown  | J             |
| 7  | Samony   | Jackson  | B             |
| 9  | Orange   | Jackson  | B             |

Orders

| ID | order_date | customer_id | salesperson_id | Amount |
|----|------------|-------------|----------------|--------|
| 10 | 8/2/96     | 4           | 2              | 540    |
| 20 | 1/30/99    | 4           | 8              | 1800   |
| 30 | 7/14/95    | 9           | 1              | 460    |
| 40 | 1/29/98    | 7           | 2              | 2400   |
| 50 | 2/3/98     | 6           | 7              | 600    |
| 60 | 3/2/98     | 6           | 7              | 720    |
| 70 | 5/6/98     | 9           | 7              | 150    |

Given the tables above, write the SQL query that:

1. Returns the names of all Salesperson that don’t have any order with Samsonic.
2. Updates the names of Salesperson that have 2 or more orders. It’s necessary to add an
   ‘*’ in the end of the name.
3. Deletes all Salesperson that placed orders to the city of Jackson.
4. The total sales amount for each Salesperson. If the salesperson hasn’t sold anything,
   show zero.

## Question 8

The customer has a system called XYZ and intends to start updates split into 3 phases. The 
requirements for the first phase are as follows:

1. Enable new data entries in the system, which will serve as input for the second
   phase.
2. Implement functionality to create, update, delete, and search plants.
   o Plants should have the following attributes:
    Code: Numeric only, mandatory, and unique.
    Description: Alphanumeric, up to 10 characters, optional.
   o Only admin users can delete plants.
3. Ensure that the system prevents duplication of plant codes.

Task: Based on the above information:

1. Write a use case or user story for this scenario, ensuring that it clearly addresses the
   requirements.
2. Highlight any business rules or assumptions relevant to the solution.
3. Describe any validations or security measures you would implement in the system.
4. Suggest how you would test this functionality, including examples of edge cases.