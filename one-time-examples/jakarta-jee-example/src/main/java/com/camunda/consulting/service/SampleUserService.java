package com.camunda.consulting.service;

import com.camunda.consulting.dao.SampleUserDao;
import com.camunda.consulting.model.SampleUserEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Named
@Stateless
public class SampleUserService {
    private static final Logger log = LoggerFactory.getLogger(SampleUserService.class);

    @EJB
    private SampleUserDao sampleUserDao;

    public List<SampleUserEntity> getAllUsers() {
        return sampleUserDao.findAllUsers();
    }

    public void findUserByName(DelegateExecution delegateExecution) {
        // Get all process variables
        log.info("Executing findUserByName called by service task");
        Map<String, Object> variables = delegateExecution.getVariables();

        String name = variables.get("user").toString();

        var user = sampleUserDao.findUserByName(name);

        if (user == null) {
            var userEntity = new SampleUserEntity();
            log.error("user does not exists create new one");
            userEntity.setName(name);
            sampleUserDao.createUser(userEntity);
            delegateExecution.setVariable("userId", userEntity.getId());
        } else {
            delegateExecution.setVariable("userId", user.getId());
        }
    }
}
