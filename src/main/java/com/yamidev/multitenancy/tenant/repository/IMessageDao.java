package com.yamidev.multitenancy.tenant.repository;

import com.yamidev.multitenancy.tenant.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageDao extends JpaRepository<Message, Long> {
}
