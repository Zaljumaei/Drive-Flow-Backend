package com.zaljumaei.driveflow.common;

public interface BaseService<I, O> {

    O create(I request);

    O update(Long id, I request);

    O findById(Long id);

    void delete(Long id);

}
