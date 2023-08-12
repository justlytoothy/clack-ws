package com.portfolio.clack.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
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

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_thread",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "thread_id"))
  @JsonIgnore
  private List<Thread> threads = new ArrayList<>();

  @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL,
          orphanRemoval = true, fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Thread> createdThreads = new ArrayList<>();

  @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL,
          orphanRemoval = true, fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Message> sentMessages = new ArrayList<>();
}