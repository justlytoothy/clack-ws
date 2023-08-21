package com.portfolio.clack.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto extends BaseEntityDto {

  private Long createdBy;

  private Long threadId;

  private String body;

  private UserLiteDto creator;

  private ThreadDto thread;

  public MessageDto clone(final boolean includeID) {
    return MessageDto.builder()
            .id(includeID ? this.getId() : null)
            .body(this.getBody())
            .createdBy(this.getCreatedBy())
            .threadId(this.getThreadId())
            .creator(this.getCreator())
            .thread(this.getThread())
            .createdDate(this.getCreatedDate())
            .updatedDate(this.getUpdatedDate())
            .build();
  }

  public UserLiteDto getCreator() {
    if (creator == null) {
      creator = new UserLiteDto();
    }
    return creator;
  }

  public ThreadDto getThread() {
    if (thread == null) {
      thread = new ThreadDto();
    }
    return thread;
  }
}
