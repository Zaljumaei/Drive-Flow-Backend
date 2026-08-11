package com.zaljumaei.driveflow.common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Property class to manage properties for pagination.
 * It allowed us to change the values without rebuild or redeploy
 */
@Component
@ConfigurationProperties(prefix = "paging.page")
@Data
public class PagingProps {


    private int drivingSchoolPageSize = 50;

    @Min(value = 5, message = "must be between 5 and 50.")
    @Max(value = 50, message = "must be between 5 and 50.")
    private int instructorPageSize = 20;

    @Min(value = 5, message = "must be between 5 and 50.")
    @Max(value = 50, message = "must be between 5 and 50.")
    private int studentPageSize = 20;

    private int vehiclePageSize = 20;
}
