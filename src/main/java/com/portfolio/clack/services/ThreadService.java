package com.portfolio.clack.services;

import com.portfolio.clack.dtos.ThreadDto;
import com.portfolio.clack.entities.Thread;
import com.portfolio.clack.repositories.ThreadRepository;
import com.portfolio.clack.util.AbstractTransposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadService {
  private final ThreadRepository threadRepository;

  private final ThreadTransposer threadTransposer = new ThreadTransposer();

  @Autowired
  public ThreadService(final ThreadRepository threadRepository) {
    this.threadRepository = threadRepository;
  }


  public void saveThread(ThreadDto threadDto) {
    threadRepository.save(threadTransposer.toEntityType(threadDto));
  }

  public ThreadDto getThread(Long id) {
    return threadTransposer.toDtoType(threadRepository.findById(id).orElse(null));
  }

  public void deleteThread(Long id) {
    threadRepository.deleteById(id);
  }

  public static class ThreadTransposer extends AbstractTransposer<ThreadDto, Thread> {
    @Override
    public ThreadDto toDtoType(Thread thread) {
      return ThreadDto.builder()
              .id(thread.getId())
              .name(thread.getName())
              .createdBy(thread.getCreatedBy())
              .creator(new UserService.UserLiteTransposer().toDtoType(thread.getCreator()))
              .createdDate(thread.getCreatedDate())
              .updatedDate(thread.getUpdatedDate())
              .users(buildUserLiteDtos(thread.getUsers()))
              .messages(buildMessageDtos(thread.getMessages()))
              .build();
    }

    @Override
    public Thread toEntityType(ThreadDto threadDto) {
      final Thread thread = new Thread();
      thread.setId(threadDto.getId());
      thread.setName(threadDto.getName());
      thread.setCreatedBy(threadDto.getCreatedBy());
      thread.setCreatedDate(threadDto.getCreatedDate());
      thread.setUpdatedDate(threadDto.getUpdatedDate());
      thread.setUsers(buildLiteUsers(threadDto.getUsers()));
      thread.setMessages(buildMessages(threadDto.getMessages()));
      return thread;
    }

    @Override
    public List<ThreadDto> toDtoTypes(final List<Thread> threads) {
      final List<ThreadDto> threadDtos = new ArrayList<>();
      for (final Thread thread : threads) {
        if (thread != null) {
          threadDtos.add(toDtoType(thread));
        }
      }
      return threadDtos;
    }
  }
}
