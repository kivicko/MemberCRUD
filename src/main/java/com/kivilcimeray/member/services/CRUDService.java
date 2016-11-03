package com.kivilcimeray.member.services;

import java.util.List;

/**
 * Created by anıl on 03.11.2016.
 *  Designed as common interface for all type of models. Because, there might be some other models next time.
 */
public interface CRUDService<T> {
    List<T> listAll();
    T getById(String id);
    T saveOrUpdate(T domainObject);
    void delete(String id);
}
