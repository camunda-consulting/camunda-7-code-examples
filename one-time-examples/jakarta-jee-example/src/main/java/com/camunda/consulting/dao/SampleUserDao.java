package com.camunda.consulting.dao;

import com.camunda.consulting.model.SampleUserEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class SampleUserDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createUser(SampleUserEntity user) {
        em.persist(user);
    }

    public List<SampleUserEntity> findAllUsers() {
        return em.createQuery("SELECT u FROM SAMPLE_USER u", SampleUserEntity.class).getResultList();
    }

    public SampleUserEntity findUserByName(String userName) {
        var user = em.createQuery("SELECT u FROM SAMPLE_USER u WHERE u.name = :userName", SampleUserEntity.class)
                .setParameter("userName", userName).getResultList();
        if (user.isEmpty()) {
            return null;
        }
        return user.get(0);
    }
}
