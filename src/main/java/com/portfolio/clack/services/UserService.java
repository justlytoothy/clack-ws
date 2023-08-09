package com.portfolio.clack.services;

import com.portfolio.clack.models.User;
import com.portfolio.clack.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> listAllUser() {
    return userRepository.findAll();
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
