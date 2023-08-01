package com.portfolio.clack.models;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String photoUrl;
//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(  name = "user_thread",
//          joinColumns = @JoinColumn(name = "user_id"),
//          inverseJoinColumns = @JoinColumn(name = "thread_id"))
//  private Set<Thread> threads;


}