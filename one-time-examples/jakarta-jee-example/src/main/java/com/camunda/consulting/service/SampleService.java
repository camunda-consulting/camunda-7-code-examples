package com.camunda.consulting.service;

import com.camunda.consulting.dao.SampleUserDao;
import com.camunda.consulting.model.SampleUserEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class SampleService {
    private static final Logger log = LoggerFactory.getLogger(SampleService.class);
    @EJB
    private SampleUserDao sampleUserDao;

    public String hello(String name) {
        var user = sampleUserDao.findUserByName(name);
        if (user != null) {
            return "user already exists";
        }
        SampleUserEntity sampleUserEntity = new SampleUserEntity();
        sampleUserEntity.setName(name);
        try {
            sampleUserDao.createUser(sampleUserEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return String.format("Hello '%s'.", name);
    }
}