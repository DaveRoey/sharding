package com.dave.sharding.dao.impl;

import com.dave.sharding.dao.EmployeeRepository;
import com.dave.sharding.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Dave on 2018/12/20
 * Describes
 */
public class JpaEmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Long insert(Employee entity) {
        entityManager.persist(entity);
        return null;
    }

    @Override
    public void delete(Long id) {
        Query query = entityManager.createQuery("DELETE FROM OrderEntity o WHERE o.orderId = ?1");
        query.setParameter(1, orderId);
        query.executeUpdate();
    }

    @Override
    public List<Employee> selectAll() {
        return null;
    }

    @Override
    public List<Employee> selectRange() {
        return null;
    }

    @Override
    public void batchSave(List<Employee> list) {
        entityManager.detach();
    }
}
