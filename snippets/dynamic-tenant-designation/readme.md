## Dynamic tenant designation in Camunda BPM

**Camunda Version used:**  7.13.0

**Community or Enterprise:**  Community

**Java Version used:**  Java 11

**Platform:**  Spring Boot

===========

### Introduction

This is a Spring Boot version of the following example in the Camunda documentation:

https://docs.camunda.org/manual/7.13/user-guide/process-engine/multi-tenancy/#shared-definitions-for-all-tenants

If you are looking for examples of accomplishing this other than Spring Boot, then you can look at the link referenced in the docs.

https://github.com/camunda/camunda-bpm-examples/tree/master/multi-tenancy/tenant-identifier-shared-definitions

### Steps to follow

1. Start Spring Boot.
2. Log in to Camunda Admin as demo/demo
- Create tenants "tenantOne", "tenantTwo", "tenantThree"
- Create users "userx", "usery", "userz"
- Select the list of users.  
	- Select userx: add to tenant "tenantOne".  
	- Select usery: add to tenant "tenantTwo".  
	- Select userz: add to tenant "tenantThree".  
3. Log out of Camunda Admin
4. Log in as userx.  Go to Tasklist.  Select "Start Process" in the upper right hand corner.  Go to Cockpit, go to the process definition, then the process instance.  You should see that it was automatically assigned "tenantOne" to the instance.  This is visible in the left hand pane once you have selected the instance.
5. Repeat step 4 for usery and userz.

Notice the following:

- after starting the process instance in tasklist, refresh the screen.  You will notice that your user can only see the tenant instance of the user task that they are allowed to see per the dynamic tenant designation.
- in Cockpit, you will notice that userx, usery, and userz can only see the process instances associated to their tenant.
- finally, log into Cockpit as demo/demo.  Since demo is part of the "camunda-admin" group, demo can see all three instances for all tenants.

### Files to examine:

in src/main/java/org/example:

- CustomTenantIdProvider.java
- MyTenantPlugin.java