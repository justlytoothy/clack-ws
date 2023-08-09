package com.portfolio.clack.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thread")
public class Thread extends BaseEntity {
  @Column(name = "created_by")
  private Long createdBy;
  @Column(name = "name")
  private String name;
//  @ManyToMany(mappedBy = "threads")
//  Set<User> users;
  @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL,
          orphanRemoval = true, fetch = FetchType.LAZY)
  private Set<Message> messages = new HashSet<>();

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
