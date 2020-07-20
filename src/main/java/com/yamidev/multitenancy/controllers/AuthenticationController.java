package com.yamidev.multitenancy.controllers;

import com.yamidev.multitenancy.constant.UserStatus;
import com.yamidev.multitenancy.dto.entity.AuthResponse;
import com.yamidev.multitenancy.dto.entity.UserLogin;
import com.yamidev.multitenancy.mastertenant.config.DBContextHolder;
import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;
import com.yamidev.multitenancy.mastertenant.service.MasterTenantService;
import com.yamidev.multitenancy.security.UserTenantInformation;
import com.yamidev.multitenancy.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/auth")
public class AuthenticationController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private Map<String, String> mapValue = new HashMap<>();
    private Map<String, String> userDbMap = new HashMap<>();


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    MasterTenantService masterTenantService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLogin userLogin) throws AuthenticationException {
        LOGGER.info("userLogin() method call...");
        if(null == userLogin.getUserName() || userLogin.getUserName().isEmpty()){
            return new ResponseEntity<>("User name is required", HttpStatus.BAD_REQUEST);
        }

        MasterTenant masterTenant = masterTenantService.findByClientId(userLogin.getTenantOrClientId());
        if(null == masterTenant || masterTenant.getStatus().toUpperCase().equals(UserStatus.INACTIVE)){
            throw new RuntimeException("Please contact service provider.");
        }

        loadCurrentDatabaseInstance(masterTenant.getDbName(), userLogin.getUserName());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername(),
                String.valueOf(userLogin.getTenantOrClientId()));

        setMetaDataAfterLogin();

        return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(),token));

    }

    private void loadCurrentDatabaseInstance(String databaseName, String userName) {
        DBContextHolder.setCurrentDb(databaseName);
        mapValue.put(userName, databaseName);
    }

    @Bean(name = "userTenantInfo")
    @ApplicationScope
    public UserTenantInformation setMetaDataAfterLogin() {
        UserTenantInformation tenantInformation = new UserTenantInformation();
        if (mapValue.size() > 0) {
            for (String key : mapValue.keySet()) {
                if (null == userDbMap.get(key)) {
                    //Here Assign putAll due to all time one come.
                    userDbMap.putAll(mapValue);
                } else {
                    userDbMap.put(key, mapValue.get(key));
                }
            }
            mapValue = new HashMap<>();
        }
        tenantInformation.setMap(userDbMap);
        return tenantInformation;
    }
}
