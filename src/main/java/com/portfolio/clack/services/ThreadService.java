package com.portfolio.clack.services;

import com.portfolio.clack.models.Thread;
import com.portfolio.clack.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadService {
  private final ThreadRepository threadRepository;

  @Autowired
  public ThreadService(final ThreadRepository threadRepository) {
    this.threadRepository = threadRepository;
  }


  public void saveThread(Thread thread) {
    threadRepository.save(thread);
  }

  public Thread getThread(Long id) {
    return threadRepository.findById(id).get();
  }

  public void deleteThread(Long id) {
    threadRepository.deleteById(id);
  }

}
