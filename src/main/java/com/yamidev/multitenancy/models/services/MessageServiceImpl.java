package com.yamidev.multitenancy.models.services;

import com.yamidev.multitenancy.models.dao.IMessageDao;
import com.yamidev.multitenancy.models.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    private IMessageDao repo;

    @Override
    public Message save(Message message) {
        return repo.save(message);
    }

    @Override
    public Message findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return repo.findAll();
    }
}
