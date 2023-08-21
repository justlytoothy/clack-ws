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
public class UserLiteDto extends BaseEntityDto {
  private String firstName;

  private String lastName;

  private String photoUrl;
  public UserLiteDto clone(final boolean includeId) {
    final UserLiteDto userDto = UserLiteDto.builder()
            .id(includeId ? this.getId() : null)
            .firstName(this.getFirstName())
            .lastName(this.getLastName())
            .createdDate(this.getCreatedDate())
            .updatedDate(this.getUpdatedDate())
            .photoUrl(this.getPhotoUrl())
            .build();
    return userDto;
  }
}
