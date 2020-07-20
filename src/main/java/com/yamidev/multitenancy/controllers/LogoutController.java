package com.yamidev.multitenancy.controllers;

import com.yamidev.multitenancy.security.UserTenantInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api")
public class LogoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/logout")
    public ResponseEntity<?> logoutFromApp(Principal principal) {
        LOGGER.info("AuthenticationController::logoutFromApp() method call..");
        UserTenantInformation userCharityInfo = applicationContext.getBean(UserTenantInformation.class);
        Map<String, String> map = userCharityInfo.getMap();
        map.remove(principal.getName());
        userCharityInfo.setMap(map);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
