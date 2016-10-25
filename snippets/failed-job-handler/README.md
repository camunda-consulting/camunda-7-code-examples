# Failed Job Handler which does not do retry on business exceptions

The default Camunda Job Executor retries 3 times if an exception occurs. Sometimes you do not want to have retries on certain business exceptions, which you want to decide during delegation code e.g. by throwing specific exception classes.