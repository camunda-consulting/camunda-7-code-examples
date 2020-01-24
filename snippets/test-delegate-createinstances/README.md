# Parallel multi-instance by delegate

This small project contains a process that calls a delegate that creates multiple instances of a "subprocess". Each instance is created in a different transaction, so this avoid the problem of fat transactions that happen in a normal parallel multi instance.

This solution works for Camunda 7.9.x. For 7.11.2 or later patches / version can use the following solution: https://docs.camunda.org/manual/7.11/user-guide/process-engine/transactions-in-processes/#transactions-and-the-process-engine-context

The solution is built by the following blocks:

* A delegate which creates a random amount of UUID based on an input variable called "listSize" (org.camunda.bpm.demo.delegate.GenerateRandomDataDelegate).
* A delegate which set variable "instancesCount" with the size of the list previously generated. It also sets two control variables at 0 ("finishedInstancesCount" and "createdInstancesCount") (org.camunda.bpm.demo.delegate.SetInstanceCount).
* A delegate which creates all the instances of a dummy subprocess based on the list previously created (org.camunda.bpm.demo.delegate.CreateInstancesDelegate).
* A conditional event that waits until the variable "finishedInstancesCount" to be equal to "instancesCount".
* The subprocess calls a simple logger delegate and then updates the variable "finishedInstancesCount" on the parent process through a delegate (org.camunda.bpm.demo.delegate.UpdateParentInstanceCountDelegate).

The following part is a central element that creates the instances in different transactions:
```
CommandExecutor commandExecutor = configuration.getCommandExecutorTxRequiresNew();
commandExecutor.execute(new Command<Void>() {
    @Override
    public Void execute(CommandContext commandContext) {
        //HERE YOU CAN INSERT A COMMAND THAT WILL BE EXECUTED IN A DIFFERENT TRANSACTION
    }
});
```

Unfortunately this solution has a flaw, that if the subprocess is fully automatic you will get a lot of OptimisticLockingException when trying to update the variable in the parent. As such, this solution works best when you either don't need to wait for the subprocesses to finish, or when they are not executed immediately.