package com.zaljumaei.driveflow.common;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Page response that can be used in methods,
 * which return multiple elements like findAll.
 */
@Builder
public record PageResponse<T>(
        List<T> content,
        int totalElement,
        int totalPages,
        boolean isLast,
        boolean isFirst
)
{ }
