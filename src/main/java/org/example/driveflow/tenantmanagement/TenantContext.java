package org.example.driveflow.tenantmanagement;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * this class hold the Current tenant per request.
 */

@Component
@RequestScope
public class TenantContext {

    private static ThreadLocal<Long> CURRENT_TENANT = new ThreadLocal<>();

    public static long getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    public static void setCurrentTenant(long tenant) {
        CURRENT_TENANT.set(tenant);
    }

    public static void removeCurrentTenant() {
        CURRENT_TENANT.remove();
    }
}
