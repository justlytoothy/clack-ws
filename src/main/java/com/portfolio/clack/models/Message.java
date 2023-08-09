package com.portfolio.clack.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
public class Message extends BaseEntity {
  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "thread_id")
  private Long threadId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="thread_id", nullable = false)
  private Thread thread;

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Long getThreadId() {
    return threadId;
  }

  public void setThreadId(Long threadId) {
    this.threadId = threadId;
  }
}