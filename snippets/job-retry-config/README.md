# Introduction

An example to change the number of retries after a failed job. 

For an OptimisticLockingException the number of retries is set to 3, for all others it is set to 1.
One retry means that the failure will create an incident immediately.  

# Remarks to run this application
The feature comes in an process engine plugin, which you can copy easily into your project.

Three tests show you, how it works, and where not.

It works only with failing jobs, all other failures behaves as without the plugin.
 
# Known Issues

non 

# Improvements Backlog
