package com.portfolio.clack.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Table(name = "message")
public class Message extends BaseEntity {

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "thread_id")
  private Long threadId;

  @Column(name = "body")
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="thread_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Thread thread;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="created_by", referencedColumnName = "id", insertable = false, updatable = false)
  private User creator;

}