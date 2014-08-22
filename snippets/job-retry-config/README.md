# Introduction

An example suppress the retries after a failed job. 

For an OptimisticLockingException the number of retries won't be changed, for all others it is set to 1.
One retry means that the failure will create an incident immediately.  

# Remarks to run this application
The feature comes in an process engine plugin, which you can copy easily into your project.

Four tests show you, how it works, and where not.

It works only with failing jobs, all other failures behave as without the plugin.
 
# Known Issues

non 

# Improvements Backlog

some configuration from the config files?