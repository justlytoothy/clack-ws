package com.portfolio.clack.controllers;

import com.portfolio.clack.dtos.ThreadDto;
import com.portfolio.clack.dtos.UserDto;
import com.portfolio.clack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public List<UserDto> list() {
    return userService.listAllUser();
  }

  @GetMapping("/threads/{id}")
  public ResponseEntity<List<ThreadDto>> getUserThreads(@PathVariable Long id) {
    try {
      List<ThreadDto> threadDtos = userService.getUserThreads(id);
      return new ResponseEntity<List<ThreadDto>>(threadDtos, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<List<ThreadDto>>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> get(@PathVariable Long id) {
    try {
      UserDto userDto = userService.getUser(id);
      return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/create")
  public void add(@RequestBody UserDto userDto) {
    userDto.setUsername(userDto.getUsername().toLowerCase());
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userService.saveUser(userDto);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable Long id) {
    try {
      UserDto existingUserDto = userService.getUser(id);
      userDto.setId(id);
      userDto.setCreatedDate(existingUserDto.getCreatedDate());
      userDto.setPassword(existingUserDto.getPassword());
      userService.saveUser(userDto);
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
