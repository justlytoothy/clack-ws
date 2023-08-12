package com.portfolio.clack.security.responses;

import com.portfolio.clack.dtos.UserDto;

public class JwtResponse {
  private Long id;
  private String username;
  private String token;
  private UserDto user;
  private String type = "Bearer";

  public JwtResponse(final Long id, final String username, final String token, final UserDto user) {
    this.id = id;
    this.username = username;
    this.token = token;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public UserDto getUser() {
    return user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(final String token) {
    this.token = token;
  }
}
