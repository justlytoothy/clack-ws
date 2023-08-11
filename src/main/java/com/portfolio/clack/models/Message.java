package com.portfolio.clack.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="thread_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Thread thread;

}