package com.yamidev.multitenancy.models.services;

import com.yamidev.multitenancy.models.entity.Message;

import java.util.List;

public interface IMessageService {

    Message save(Message message);

    Message findById(Long id);

    List<Message> findAll();
}
