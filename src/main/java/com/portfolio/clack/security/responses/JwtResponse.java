package com.portfolio.clack.security.responses;

public class JwtResponse {
  private Long id;
  private String username;
  private String token;
  private String type = "Bearer";

  public JwtResponse(final Long id, final String username, final String token) {
    this.id = id;
    this.username = username;
    this.token = token;
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

  public String getToken() {
    return token;
  }

  public void setToken(final String token) {
    this.token = token;
  }
}
