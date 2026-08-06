package com.zaljumaei.driveflow.tenantmanagement;


import org.springframework.web.context.annotation.RequestScope;

/**
 * this class hold the Current tenant per request.
 */
@RequestScope
public class TenantContext {

    private static ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    public static String getCurrentTenant() {
        return CURRENT_TENANT.get() == null ? "DEFAULT" : CURRENT_TENANT.get();
    }

    public static void setCurrentTenant(String tenant) {
        CURRENT_TENANT.set(tenant);
    }

    public static void removeCurrentTenant() {
        CURRENT_TENANT.remove();
    }
}
