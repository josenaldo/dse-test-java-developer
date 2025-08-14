package br.com.josenaldo.dsejavadeveloper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "sales_person")
public class SalesPerson {

  public SalesPerson() {
  }

  public SalesPerson(String id, String name, Short age, BigDecimal salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  @Id
  @Size(max = 36)
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Size(max = 100)
  @NotNull
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "age", columnDefinition = "tinyint UNSIGNED not null")
  private Short age;

  @NotNull
  @Column(name = "salary", nullable = false, precision = 12, scale = 2)
  private BigDecimal salary;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Short getAge() {
    return age;
  }

  public void setAge(Short age) {
    this.age = age;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

}