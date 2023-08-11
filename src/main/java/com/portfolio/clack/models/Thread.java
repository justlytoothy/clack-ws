package com.portfolio.clack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@Table(name = "thread")
public class Thread extends BaseEntity {

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "name")
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_thread",
          joinColumns = @JoinColumn(name = "thread_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
  @JsonIgnore
  private List<User> users = new ArrayList<>();

  @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL,
          orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Message> messages = new ArrayList<>();
}
