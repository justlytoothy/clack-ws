package com.portfolio.clack.controllers;

import com.portfolio.clack.models.User;
import com.portfolio.clack.security.jwt.JwtUtils;
import com.portfolio.clack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(final UserService userService, final PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("")
  public List<User> list() {
    return userService.listAllUser();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> get(@PathVariable Long id) {
    try {
      User user = userService.getUser(id);
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/create")
  public void add(@RequestBody User user) {
    user.setUsername(user.getUsername().toLowerCase());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
    try {
      User existingUser = userService.getUser(id);
      user.setId(id);
      user.setCreatedDate(existingUser.getCreatedDate());
      user.setPassword(existingUser.getPassword());
      userService.saveUser(user);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    userService.deleteUser(id);
  }

}
