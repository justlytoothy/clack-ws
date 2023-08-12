package com.portfolio.clack.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.clack.dtos.UserDto;
import com.portfolio.clack.entities.User;
import com.portfolio.clack.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String username;
  @JsonIgnore
  private String password;

  private UserDto user;
  private String photoUrl;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(final Long id, final String username, final String password,
                         final Collection<? extends GrantedAuthority> authorities, final UserDto user) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.user = user;

  }
  public UserDetailsImpl(final Long id, final String username, final String password,
                         final Collection<? extends GrantedAuthority> authorities, final String photoUrl, final UserDto user) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.photoUrl = photoUrl;
    this.user = user;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    UserDto userDto = new UserService.UserTransposer().toDtoType(user);
    userDto.setPassword(null);
    return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            authorities,
            user.getPhotoUrl(),
            userDto);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public UserDto getUser() {
    return user;
  }

  @Override
  public String getUsername() {
    return username;
  }
  public String getPhotoUrl() {
    return photoUrl;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
