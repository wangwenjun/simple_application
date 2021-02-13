package com.wangwenjun.simple.application.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String address;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  private String remark;

  public Employee() {
  }

  /**
   * Full properties constructor.
   *
   * @param name      The employee name
   * @param address   The employee address
   * @param createdAt The employee creation date
   * @param updatedAt The employee update date
   * @param remark    remark field.
   */
  public Employee(String name, String address, Date createdAt, Date updatedAt, String remark) {
    this.name = name;
    this.address = address;
    this.createdAt = createdAt != null ? new Date(createdAt.getTime()) : null;
    this.updatedAt = updatedAt != null ? new Date(updatedAt.getTime()) : null;
    this.remark = remark;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getCreatedAt() {
    return createdAt != null ? new Date(createdAt.getTime()) : null;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt != null ? new Date(createdAt.getTime()) : null;
  }

  public Date getUpdatedAt() {
    return updatedAt != null ? new Date(updatedAt.getTime()) : null;
  }

  @SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" }, justification = "I prefer to suppress these FindBugs warnings")
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id)
            && Objects.equals(name, employee.name)
            && Objects.equals(address, employee.address)
            && Objects.equals(createdAt, employee.createdAt)
            && Objects.equals(updatedAt, employee.updatedAt)
            && Objects.equals(remark, employee.remark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, createdAt, updatedAt, remark);
  }

}
