package com.portfolio.clack.services;

import com.portfolio.clack.dtos.MessageDto;
import com.portfolio.clack.entities.Message;
import com.portfolio.clack.repositories.MessageRepository;
import com.portfolio.clack.util.AbstractTransposer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

  private final MessageRepository messageRepository;

  private final MessageTransposer messageTransposer = new MessageTransposer();

  @Autowired
  public MessageService(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public void saveMessage(MessageDto messageDto) {
    messageRepository.save(messageTransposer.toEntityType(messageDto));
  }

  public MessageDto getMessage(Long id) {
    return messageTransposer.toDtoType(messageRepository.findById(id).orElse(null));
  }

  public void deleteMessage(Long id) {
    messageRepository.deleteById(id);
  }

  public static class MessageTransposer extends AbstractTransposer<MessageDto, Message> {

    @Override
    public MessageDto toDtoType(final Message message) {
      return MessageDto.builder()
              .id(message.getId())
              .body(message.getBody())
              .threadId(message.getThreadId())
              .createdBy(message.getCreatedBy())
              .createdDate(message.getCreatedDate())
              .updatedDate(message.getUpdatedDate())
              .build();
    }

    @Override
    public Message toEntityType(final MessageDto messageDto) {
      final Message message = new Message();
      message.setId(messageDto.getId());
      message.setBody(messageDto.getBody());
      message.setThreadId(messageDto.getThreadId());
      message.setCreatedBy(messageDto.getCreatedBy());
      message.setCreatedDate(messageDto.getCreatedDate());
      message.setUpdatedDate(messageDto.getUpdatedDate());
      return message;
    }

    @Override
    public List<MessageDto> toDtoTypes(final List<Message> messages) {
      final List<MessageDto> messageDtos = new ArrayList<>();
      for (final Message message : messages) {
        if (message != null) {
          messageDtos.add(toDtoType(message));
        }
      }
      return messageDtos;
    }
  }
}
