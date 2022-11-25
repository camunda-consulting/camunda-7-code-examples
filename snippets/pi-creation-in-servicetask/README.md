# Process Instance Creation in Servicetask 

This repo contains an example on how to instantiate process instances inside a service task.

## Wait, this is totally trivial!

Might be, but it also shows that the creation of new process instances happens inside the same transaction as the execution of the service task. This means that as soon as the transction that executes the service task ends. all process instances started do also exist.

## What can we do with it?

You could connect this test your database setup and see whether it also works there. If not, you might have a problem with your database configuration.
