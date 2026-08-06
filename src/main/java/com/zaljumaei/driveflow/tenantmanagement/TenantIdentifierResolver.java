package com.zaljumaei.driveflow.tenantmanagement;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentTenant = TenantContext.getCurrentTenant();
        return currentTenant != null ? currentTenant : "DEFAULT";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
