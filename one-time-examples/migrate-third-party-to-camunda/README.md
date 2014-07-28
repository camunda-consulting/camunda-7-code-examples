# How to migrate process instances from existing solutions to camunda BPM

In every company you will sooner or later migrate to camunda BPM as your favorite BPM platform. When doing this you often do not start on a green field - but have ruinning process instances somewhere already:
* In some third party process engine which might be outdated, discontinued or just crap.
* In some hard coded application running till now which shall be replaced by a real workflow engine (good decision by the way).
* The business process is burried in  some clumsy construct consisting of code, database tables, triggers and magic scripts.
* ...

When you now want to release the process as real BPMN process model on camunda BPM you normaly have to migrate process definitions as well as running process instances. 

Camunda needs process definitions as valid BPMN 2.0 files (XML). In order to get them you have these options:
1. Your old engine was BPMN 2.0 as well - so there is no migration.
   * Well, theoretically yes. In real-life you always have to migrate some nuts and bolts, e.g. extensions used by the vendors.
2. Automated conversion from the old format to the new format.
   * This might work if you already had a process engine with a proper XML format or some magic process mining tool generating meaningful data to create process definitions.
   * However - we never did this in bigger projects and advise against it. Normally it is a really good idea to re-model the processes using BPMN and the BPMN best practices. You will get better models - and this could be already worth the whole migration :-)
3. Re-Model the processes-
   * This is actually what we did in each and every project till now. But it always turned out to be a good idea. BPMN is really a great language to model processes and you can only leverage this power if you start from scratch.
   * Normally the number of process definitions is limited and the time you need to re-model is much less than the time you would need to create and test an automatic conversion.


For the process instances (the actually more thrilling part) you have two basic options:
1. Run in parallel: You still run the old solution untill no process instances are left. In the meanwhile you start new process instances on camunda BPM. 
   * You need some kind of switch to route incoming requests to the old or new system.
   * You need to take care of operating both systems, e.g. checking failures, calculating KPI's or doing instance counts.
   * THUMBS UP: You do not need to migrate running process instances and save hazzle for this. This imposes less risk as well.
   * THUMBS DOWN: Operating two systems in parallel for maybe a long time is quite some effort. And you might face problems with the switch sooner or later. And in most companies we were the people were really eager to throw out the old soltution - so it might be even a downer for motivation to keep it.
2. Migrate in big bang: You stop the old system and make sure all process instances have reached a wait state. Now you migrate all instances to camunda BPM, deploy the new solution and startup everything. 
   * You need a mapping for all possible wait states of the old process. This means you need to know in which node a process waiting in "wait for payment" in the old system should be moved to.
   * You need to be able to read the process instances from the old system.
   * You need a proper migration script to "generate" the new process instances in camunda BPM in the correct state. And for this I want to sketch a solution in this example.



# Migration script to create process instances in the correct state

The basic idea of migration is simple: Create a version of the process definition with seperate Start Events to start process instances and directly move them to the desired state.

We extended this a bit to be able to go into hierarchies of call activities (sub processes). We added an own extension element to "jump" to the correct node of the called process. And this can be easily done recursively. 

Lets make this more clear by sketching a simple example:




Limitations:
* History data (e.g. Audit Trail) is *not* migrated
* Parallel paths

Advantages:
* Visible in history / cockpit
* No Magic



# Possible alternatives not used

