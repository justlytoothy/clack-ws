package com.portfolio.clack.services;

import com.portfolio.clack.dtos.ThreadDto;
import com.portfolio.clack.dtos.UserDto;
import com.portfolio.clack.dtos.UserLiteDto;
import com.portfolio.clack.entities.User;
import com.portfolio.clack.repositories.UserRepository;
import com.portfolio.clack.util.AbstractTransposer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  private final UserTransposer userTransposer = new UserTransposer();

  @Autowired
  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserDto> listAllUser() {
    return userTransposer.toDtoTypes(userRepository.findAll());
  }

  public void saveUser(UserDto userDto) {
    userRepository.save(userTransposer.toEntityType(userDto));
  }

  public UserDto getUser(Long id) {
    return userTransposer.toDtoType(userRepository.findById(id).orElse(null));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public List<ThreadDto> getUserThreads(final Long id) {
    return getUser(id).getThreads();
  }

  public static class UserTransposer extends AbstractTransposer<UserDto, User> {
    @Override
    public UserDto toDtoType(final User user) {
      return UserDto.builder()
              .id(user.getId())
              .username(user.getUsername())
              .password(user.getPassword())
              .firstName(user.getFirstName())
              .lastName(user.getLastName())
              .photoUrl(user.getPhotoUrl())
              .createdDate(user.getCreatedDate())
              .updatedDate(user.getUpdatedDate())
              .threads(buildThreadDtos(user.getThreads()))
              .createdThreads(buildThreadDtos(user.getCreatedThreads()))
              .build();
    }

    @Override
    public User toEntityType(UserDto userDto) {
      final User user = new User();
      user.setId(userDto.getId());
      user.setUsername(userDto.getUsername());
      user.setPassword(userDto.getPassword());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setPhotoUrl(userDto.getPhotoUrl());
      user.setCreatedDate(userDto.getCreatedDate());
      user.setUpdatedDate(userDto.getUpdatedDate());
      user.setThreads(buildThreads(userDto.getThreads()));
      user.setCreatedThreads(buildThreads(userDto.getCreatedThreads()));
      return user;
    }

    @Override
    public List<UserDto> toDtoTypes(final List<User> users) {
      final List<UserDto> userDtos = new ArrayList<>();
      for (final User user : users) {
        if (user != null) {
          userDtos.add(toDtoType(user));
        }
      }
      return userDtos;
    }
  }

  public static class UserLiteTransposer extends AbstractTransposer<UserLiteDto, User> {
    @Override
    public UserLiteDto toDtoType(final User user) {
      return UserLiteDto.builder()
              .id(user.getId())
              .firstName(user.getFirstName())
              .lastName(user.getLastName())
              .photoUrl(user.getPhotoUrl())
              .createdDate(user.getCreatedDate())
              .updatedDate(user.getUpdatedDate())
              .build();
    }

    @Override
    public User toEntityType(UserLiteDto userDto) {
      final User user = new User();
      user.setId(userDto.getId());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setPhotoUrl(userDto.getPhotoUrl());
      user.setCreatedDate(userDto.getCreatedDate());
      user.setUpdatedDate(userDto.getUpdatedDate());
      return user;
    }

    @Override
    public List<UserLiteDto> toDtoTypes(final List<User> users) {
      final List<UserLiteDto> userDtos = new ArrayList<>();
      for (final User user : users) {
        if (user != null) {
          userDtos.add(toDtoType(user));
        }
      }
      return userDtos;
    }
  }
}
