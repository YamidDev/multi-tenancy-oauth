package com.yamidev.multitenancy.models.dao;

import com.yamidev.multitenancy.models.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDao extends JpaRepository<Message, Long> {
}
