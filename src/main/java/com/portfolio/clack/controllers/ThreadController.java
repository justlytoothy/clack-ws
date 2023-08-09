package com.portfolio.clack.controllers;

import com.portfolio.clack.models.Thread;
import com.portfolio.clack.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/thread")
public class ThreadController {
  private final ThreadService threadService;

  @Autowired
  public ThreadController(final ThreadService threadService) {
    this.threadService = threadService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Thread> get(@PathVariable Long id) {
    try {
      Thread thread = threadService.getThread(id);
      return new ResponseEntity<Thread>(thread, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Thread>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/create")
  public void add(@RequestBody Thread thread) {
    threadService.saveThread(thread);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody Thread thread, @PathVariable Long id) {
    try {
      Thread existingThread = threadService.getThread(id);
      thread.setId(id);
      thread.setCreatedDate(existingThread.getCreatedDate());
      threadService.saveThread(thread);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    threadService.deleteThread(id);
  }
}
