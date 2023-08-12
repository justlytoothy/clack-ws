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
public class UserDto extends BaseEntityDto {
  private String firstName;

  private String lastName;

  private String username;

  private String password;

  private String photoUrl;

  @Builder.Default
  private List<ThreadDto> threads = new ArrayList<>();

  @Builder.Default
  private List<ThreadDto> createdThreads = new ArrayList<>();

  @Builder.Default
  private List<MessageDto> sentMessages = new ArrayList<>();
  public UserDto clone(final boolean includeId) {
    final UserDto userDto = UserDto.builder()
            .id(includeId ? this.getId() : null)
            .firstName(this.getFirstName())
            .lastName(this.getLastName())
            .username(this.getUsername())
            .password(this.getPassword())
            .createdDate(this.getCreatedDate())
            .updatedDate(this.getUpdatedDate())
            .photoUrl(this.getPhotoUrl())
            .build();
    for (final ThreadDto threadDto : this.getThreads()) {
      if (threadDto != null) {
        userDto.getThreads().add(threadDto.clone(includeId));
      }
    }
    for (final ThreadDto threadDto : this.getCreatedThreads()) {
      if (threadDto != null) {
        userDto.getCreatedThreads().add(threadDto.clone(includeId));
      }
    }
    for (final MessageDto messageDto : this.getSentMessages()) {
      if (messageDto != null) {
        userDto.getSentMessages().add(messageDto.clone(includeId));
      }
    }
    return userDto;
  }
}
