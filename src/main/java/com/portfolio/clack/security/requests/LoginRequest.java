package com.portfolio.clack.security.requests;

public class LoginRequest {
  private String username;
  private String password;

  public LoginRequest(final String username, final String password) {
    this.username = username;
    this.password = password;
  }
  public LoginRequest() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }
}
