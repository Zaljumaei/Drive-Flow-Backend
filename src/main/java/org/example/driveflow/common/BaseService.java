package org.example.driveflow.common;

public interface BaseService<I, O> {

    void create(I request);

    void update(Long id, I request);

    O findById(Long id);

    void delete(Long id);

}
