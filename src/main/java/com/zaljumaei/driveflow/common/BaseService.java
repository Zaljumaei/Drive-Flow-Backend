package com.zaljumaei.driveflow.common;

public interface BaseService<I, O> {

    O create(I request);

    O update(String id, I request);

    O findById(String id);

    void delete(String id);

}
