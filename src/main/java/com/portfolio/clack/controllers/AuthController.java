package com.portfolio.clack.controllers;

import com.portfolio.clack.dtos.UserDto;
import com.portfolio.clack.repositories.UserRepository;
import com.portfolio.clack.security.jwt.JwtUtils;
import com.portfolio.clack.security.requests.LoginRequest;
import com.portfolio.clack.security.responses.JwtResponse;
import com.portfolio.clack.security.responses.MessageResponse;
import com.portfolio.clack.security.services.UserDetailsImpl;
import com.portfolio.clack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  @Autowired
  public AuthController(final PasswordEncoder passwordEncoder, final JwtUtils jwtUtils,
                        final AuthenticationManager authenticationManager, final UserRepository userRepository,
                        final UserService userService) {
    this.passwordEncoder = passwordEncoder;
    this.jwtUtils = jwtUtils;
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.userService = userService;
  }
  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return ResponseEntity.ok(new JwtResponse(
            userDetails.getId(),
            userDetails.getUsername(),
            jwt,
            userDetails.getUser()));
  }
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
    if (userRepository.existsByUsername(userDto.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }
    final String password = userDto.getPassword();
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userService.saveUser(userDto);
    return authenticateUser(new LoginRequest(userDto.getUsername(),password));
  }
}
