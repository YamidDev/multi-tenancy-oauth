package com.yamidev.multitenancy.mastertenant.repository;

import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Long> {
    MasterTenant findByTenantClientId(Long clientId);
}
