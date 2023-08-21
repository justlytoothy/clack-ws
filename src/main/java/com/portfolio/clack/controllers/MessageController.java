package com.portfolio.clack.controllers;

import com.portfolio.clack.dtos.MessageDto;
import com.portfolio.clack.dtos.UserDto;
import com.portfolio.clack.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
public class MessageController {
  private final MessageService messageService;

  @Autowired
  public MessageController(final MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<MessageDto> get(@PathVariable Long id) {
    try {
      MessageDto messageDto = messageService.getMessage(id);
      return new ResponseEntity<MessageDto>(messageDto, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<MessageDto>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/send")
  public MessageDto add(@RequestBody MessageDto messageDto) {
    return messageService.saveMessage(messageDto);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody MessageDto messageDto, @PathVariable Long id) {
    try {
      MessageDto existingMessageDto = messageService.getMessage(id);
      messageDto.setId(id);
      messageDto.setCreatedDate(existingMessageDto.getCreatedDate());
      messageService.saveMessage(messageDto);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    messageService.deleteMessage(id);
  }
}
