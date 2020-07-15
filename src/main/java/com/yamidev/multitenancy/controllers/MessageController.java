package com.yamidev.multitenancy.controllers;

import com.yamidev.multitenancy.models.entity.Message;
import com.yamidev.multitenancy.models.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1")
@RestController
public class MessageController {

    @Autowired
    private MessageServiceImpl service;

    @GetMapping("/all/messages")
    public List<Message> show() {
        return service.findAll();
    }

    @PostMapping("/message/new")
    public ResponseEntity<?> newMessage(@RequestBody Message message) {

        Map<String, Object> response = new HashMap<>();

        try {
            response.put("message", service.save(message));
        } catch (DataAccessException e) {
            response.put("message", "fallo al insertar");
            response.put("error", e.getMessage()+": "+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
