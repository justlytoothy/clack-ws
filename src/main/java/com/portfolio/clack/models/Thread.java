package com.portfolio.clack.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread extends BaseEntity {
  private Long createdBy;
//  @ManyToMany(mappedBy = "threads")
//  Set<User> users;
  @OneToMany(mappedBy = "thread")
  private List<Message> messages;

}
