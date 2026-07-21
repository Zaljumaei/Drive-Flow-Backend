package com.zaljumaei.driveflow.tenantmanagement;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TenantFilter extends OncePerRequestFilter {

    private static final String TENANT_HEADER_NAME = "X-Tenant-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String tenantIdHeader = resolveTenantId(request);
        if (tenantIdHeader == null || tenantIdHeader.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Tenant Id is missing\"}");
            return;
        }
        try{
            long tennatId = Long.parseLong(tenantIdHeader);
            TenantContext.setCurrentTenant(tennatId);
            filterChain.doFilter(request, response);
        }finally {
            TenantContext.removeCurrentTenant();
        }

    }

    private String resolveTenantId(HttpServletRequest request) {
        String tenantIdHeader = request.getHeader(TENANT_HEADER_NAME);
        if (tenantIdHeader != null && !tenantIdHeader.isBlank()) {
            return tenantIdHeader;
        }
        return null;
    }
}
