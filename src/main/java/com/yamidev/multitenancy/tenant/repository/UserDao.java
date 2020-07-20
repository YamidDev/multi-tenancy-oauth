package com.yamidev.multitenancy.tenant.repository;

import com.yamidev.multitenancy.tenant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
