package com.zaljumaei.driveflow.tenantmanagement;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HttpRequestTenantResolver extends OncePerRequestFilter {

    private static final String TENANT_HEADER_NAME = "X-Tenant-Id";

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/h2-console",
            "/swagger-ui",
            "/v3/api-docs",
            "/actuator/health",
            "/api/admin"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return EXCLUDED_PATHS.stream()
                .anyMatch(path::startsWith);
    }

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
            TenantContext.setCurrentTenant(tenantIdHeader);
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
