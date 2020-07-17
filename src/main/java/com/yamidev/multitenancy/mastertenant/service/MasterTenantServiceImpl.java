package com.yamidev.multitenancy.mastertenant.service;

import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;
import com.yamidev.multitenancy.mastertenant.repository.MasterTenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterTenantServiceImpl implements IMasterTenantService {

    private static final Logger LOG = LoggerFactory.getLogger(MasterTenantServiceImpl.class);

    @Autowired
    private MasterTenantRepository repo;

    @Override
    public MasterTenant findByClientId(Long clientId) {
        LOG.info("findByClientId() method call...");
        return repo.findByTenantClientId(clientId);
    }
}
