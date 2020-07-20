package com.yamidev.multitenancy.tenant.service;

import com.yamidev.multitenancy.tenant.entity.Message;

import java.util.List;

public interface IMessageService {

    Message save(Message message);

    Message findById(Long id);

    List<Message> findAll();
}
