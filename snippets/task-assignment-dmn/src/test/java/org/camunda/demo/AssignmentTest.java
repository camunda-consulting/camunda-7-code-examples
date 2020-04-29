package org.camunda.demo;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;


public class AssignmentTest {

    @Rule
    public ProcessEngineRule engine = new ProcessEngineRule();

    @Test
    @Deployment(resources = {"Approval.bpmn", "taskAssignment.dmn"})
    public void testMultipleCandidateUsers() {

        ProcessInstance pi = runtimeService()
                .startProcessInstanceByKey("ApprovalProcess", withVariables(
                        "productGroup", "",
                        "productDepartment", "Fruit",
                        "productClass", "Mango",
                        "halal", false));

        assertThat(pi)
                .isWaitingAt("PerformTaskTask")
                .task()
                .hasCandidateUser("Duey")
                .hasCandidateUser("Huey")
                .hasCandidateUser("Louie");
    }

    @Test
    @Deployment(resources = {"Approval.bpmn", "taskAssignment.dmn"})
    public void testGroupAssignment() {

        ProcessInstance pi = runtimeService()
                .startProcessInstanceByKey("ApprovalProcess", withVariables(
                        "productGroup", "",
                        "productDepartment", "Meat",
                        "productClass", "Beef",
                        "halal", false));

        assertThat(pi)
                .isWaitingAt("PerformTaskTask")
                .task()
                .hasCandidateGroup("Meat")
                .isNotAssigned();
    }

    @Test
    @Deployment(resources = {"Approval.bpmn", "taskAssignment.dmn"})
    public void testMultipleCandidateUsersWithExclusion() {

        ProcessInstance pi = runtimeService()
                .startProcessInstanceByKey("ApprovalProcess", withVariables(
                        "productGroup", "",
                        "productDepartment", "Fruit",
                        "productClass", "Banana",
                        "halal", false));

        assertThat(pi)
                .isWaitingAt("PerformTaskTask")
                .task()
                .hasCandidateUser("Huey")
                .hasCandidateUser("Duey")
                .isNotAssigned();
    }


    @Test
    @Deployment(resources = {"Approval.bpmn", "taskAssignment.dmn"})
    public void testGroupAssignmentWithSwitch() {

        ProcessInstance pi = runtimeService()
                .startProcessInstanceByKey("ApprovalProcess", withVariables(
                        "productGroup", "",
                        "productDepartment", "Meat",
                        "productClass", "Beef",
                        "halal", true));

        assertThat(pi)
                .isWaitingAt("PerformTaskTask")
                .task()
                .hasCandidateGroup("Halal")
                .isNotAssigned();
    }

    @Test
    @Deployment(resources = {"Approval.bpmn", "taskAssignment.dmn"})
    public void testUserAndGroupAssignment() {

        ProcessInstance pi = runtimeService()
                .startProcessInstanceByKey("ApprovalProcess", withVariables(
                        "productGroup", "",
                        "productDepartment", "Alcohol",
                        "productClass", "Rum",
                        "halal", false));

        assertThat(pi)
                .isWaitingAt("PerformTaskTask")
                .task()
                .hasCandidateGroupAssociated("18plus")
                .hasCandidateUserAssociated("Daisy")
                .isAssignedTo("Scrooge");
    }

    @Test
    @Deployment(resources = {"Approval.bpmn", "taskAssignment.dmn"})
    public void testUserAssignmentWithAssociatedGroup() {

        ProcessInstance pi = runtimeService()
                .startProcessInstanceByKey("ApprovalProcess", withVariables(
                        "productGroup", "",
                        "productDepartment", "Seafood",
                        "productClass", "Wale",
                        "halal", false));

        assertThat(pi).isWaitingAt("PerformTaskTask").task().isAssignedTo("Scrooge");
    }
}
