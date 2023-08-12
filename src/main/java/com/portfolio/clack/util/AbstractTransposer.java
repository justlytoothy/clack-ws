package com.portfolio.clack.util;

import com.portfolio.clack.dtos.BaseEntityDto;
import com.portfolio.clack.dtos.MessageDto;
import com.portfolio.clack.dtos.ThreadDto;
import com.portfolio.clack.dtos.UserDto;
import com.portfolio.clack.entities.BaseEntity;
import com.portfolio.clack.entities.Message;
import com.portfolio.clack.entities.Thread;
import com.portfolio.clack.entities.User;
import com.portfolio.clack.services.MessageService;
import com.portfolio.clack.services.ThreadService;
import com.portfolio.clack.services.UserService;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransposer<E extends BaseEntityDto, T extends BaseEntity> {

  public abstract E toDtoType(final T entity);

  public abstract T toEntityType(final E dto);

  public abstract List<E> toDtoTypes(final List<T> entities);



  protected List<ThreadDto> buildThreadDtos(final List<Thread> threads) {
    final List<ThreadDto> threadDtos = new ArrayList<>();
    if (threads != null) {
      for (final Thread thread : threads) {
        if (thread != null) {
          threadDtos.add(new ThreadService.ThreadTransposer().toDtoType(thread));
        }
      }
    }
    return threadDtos;
  }

  protected List<Thread> buildThreads(final List<ThreadDto> threadDtos) {
    final List<Thread> threads = new ArrayList<>();
    if (threadDtos != null) {
      for (final ThreadDto threadDto : threadDtos) {
        if (threadDto != null) {
          threads.add(new ThreadService.ThreadTransposer().toEntityType(threadDto));
        }
      }
    }
    return threads;
  }

  protected List<UserDto> buildUserDtos(final List<User> users) {
    final List<UserDto> userDtos = new ArrayList<>();
    if (users != null) {
      for (final User user : users) {
        if (user != null) {
          userDtos.add(new UserService.UserTransposer().toDtoType(user));
        }
      }
    }
    return userDtos;
  }

  protected List<User> buildUsers(final List<UserDto> userDtos) {
    final List<User> users = new ArrayList<>();
    if (userDtos != null) {
      for (final UserDto userDto : userDtos) {
        if (userDto != null) {
          users.add(new UserService.UserTransposer().toEntityType(userDto));
        }
      }
    }
    return users;
  }

  protected List<MessageDto> buildMessageDtos(final List<Message> messages) {
    final List<MessageDto> messageDtos = new ArrayList<>();
    if (messages != null) {
      for (final Message message : messages) {
        if (message != null) {
          messageDtos.add(new MessageService.MessageTransposer().toDtoType(message));
        }
      }
    }
    return messageDtos;
  }

  protected List<Message> buildMessages(final List<MessageDto> messageDtos) {
    final List<Message> messages = new ArrayList<>();
    if (messageDtos != null) {
      for (final MessageDto messageDto : messageDtos) {
        if (messageDto != null) {
          messages.add(new MessageService.MessageTransposer().toEntityType(messageDto));
        }
      }
    }
    return messages;
  }

}
