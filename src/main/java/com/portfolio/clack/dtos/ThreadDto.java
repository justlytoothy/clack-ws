package com.portfolio.clack.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadDto extends BaseEntityDto {
  private Long createdBy;

  private String name;

  private UserLiteDto creator;

  @Builder.Default
  private List<UserLiteDto> users = new ArrayList<>();

  @Builder.Default
  private List<MessageDto> messages = new ArrayList<>();

  public ThreadDto clone(final boolean includeId) {
    final ThreadDto threadDto = ThreadDto.builder()
            .id(includeId ? this.getId() : null)
            .name(this.getName())
            .createdBy(this.getCreatedBy())
            .creator(this.getCreator())
            .createdDate(this.getCreatedDate())
            .updatedDate(this.getUpdatedDate())
            .build();
    for (final UserLiteDto userDto : this.getUsers()) {
      if (userDto != null) {
        threadDto.getUsers().add(userDto.clone(includeId));
      }
    }
    for (final MessageDto messageDto : this.getMessages()) {
      if (messageDto != null) {
        threadDto.getMessages().add(messageDto.clone(includeId));
      }
    }
    return threadDto;
  }

  public UserLiteDto getCreator() {
    if (creator == null) {
      creator = new UserLiteDto();
    }
    return creator;
  }
}
