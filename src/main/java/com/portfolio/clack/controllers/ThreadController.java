package com.portfolio.clack.controllers;

import com.portfolio.clack.dtos.ThreadDto;
import com.portfolio.clack.entities.Thread;
import com.portfolio.clack.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<ThreadDto> get(@PathVariable Long id) {
    try {
      ThreadDto threadDto = threadService.getThread(id);
      return new ResponseEntity<ThreadDto>(threadDto, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<ThreadDto>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/create")
  public void add(@RequestBody ThreadDto threadDto) {
    threadService.saveThread(threadDto);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody ThreadDto threadDto, @PathVariable Long id) {
    try {
      ThreadDto existingThreadDto = threadService.getThread(id);
      threadDto.setId(id);
      threadDto.setCreatedDate(existingThreadDto.getCreatedDate());
      threadService.saveThread(threadDto);
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
