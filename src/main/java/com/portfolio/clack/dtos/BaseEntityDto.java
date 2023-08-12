package com.portfolio.clack.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@EqualsAndHashCode()
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntityDto {
  private static final long serialVersionUID = 1L;

  protected Long id;

  protected Timestamp createdDate;

  protected Timestamp updatedDate;
}
