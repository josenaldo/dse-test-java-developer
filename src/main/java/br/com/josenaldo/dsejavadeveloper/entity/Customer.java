package br.com.josenaldo.dsejavadeveloper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @Size(max = 36)
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Size(max = 255)
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @Size(max = 255)
  @NotNull
  @Column(name = "email", nullable = false)
  private String email;

  @Size(max = 255)
  @NotNull
  @Column(name = "username", nullable = false)
  private String username;

  @Size(max = 100)
  @NotNull
  @Column(name = "city", nullable = false, length = 100)
  private String city;

  @NotNull
  @Column(name = "industry_type", nullable = false)
  private Character industryType;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Character getIndustryType() {
    return industryType;
  }

  public void setIndustryType(Character industryType) {
    this.industryType = industryType;
  }

}