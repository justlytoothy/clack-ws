package com.portfolio.clack.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {
  private Long createdBy;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="thread_id",referencedColumnName = "id")
  private Thread thread;
  @Column(name = "thread_id")
  private Long threadId;

}
