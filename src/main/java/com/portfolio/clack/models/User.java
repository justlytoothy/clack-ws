package com.portfolio.clack.models;
import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "photo_url")
  private String photoUrl;
//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(name = "user_thread",
//          joinColumns = @JoinColumn(name = "user_id"),
//          inverseJoinColumns = @JoinColumn(name = "thread_id"))
//  private Set<Thread> threads;


}