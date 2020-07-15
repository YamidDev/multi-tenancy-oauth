package com.yamidev.multitenancy.models.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    private Long id;

    private String message;
}
