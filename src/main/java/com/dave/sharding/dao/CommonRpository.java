package com.dave.sharding.dao;

import com.dave.sharding.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
public interface CommonRpository<T> {



    Long insert(T entity);

    void delete(Long id);

    List<T> selectAll();

    List<T> selectRange();

    void batchSave(List<T> list);


}
