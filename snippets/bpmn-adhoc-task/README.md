# How to realize BPMN AdHoc SubProcess with camunda BPM?

Table of Contents

- [What does it demonstrate?](#what-does-it-demonstrate)
- [Technical environment](#technical-environment)
- [Getting Started](#getting-started)
	
	
## What does it demonstrate?

camunda BPM currently does not support the BPMN 2.0 AdHoc SubProcess. 

But if you have requirements like
- Only 2 out of 4 tasks must be finished - then we can continue
- The user can decide after what task the process should continue
- Some rules can determine when some open tasks are no longer necessary and the process should move on.

This can be easily hooked in camunda BPM with simple workaround. This snippet shows an example where the process continues after task "A" and task "B" are finished (in this case either "C" weas already finished or it is skipped):

![Screenshot "Example process"][1]

[1]: https://raw.github.com/camunda/camunda-consulting/master/snippet/bpmn-adhoc-task/src/main/resources/process.png

The implementation is pretty straightforward and uses [http://docs.camunda.org/latest/guides/user-guide/#process-engine-delegation-code-task-listener](Task Listener) which check at the completion of every task the rules if the other tasks should be cancelled. You could easily extend this check and for example check only tasks in the current sub process (to be pretty close to the AdHoc SubProcess).


## Technical environment
- camunda BPM platform 7.0.0
- Tested only as JUnit test case


## Getting Started

* Clone this repo and build the project using Maven
* Run the Unit Test
* Deploy the resulting WAR on the container (*tested only with JBoss AS 7*)

## Known Issues

This is a pretty rough example hacked in a couple of minutes between two appointments. So it is neither a really sophisticed nor the best solution for this approach! 

Feel free to discuss it in our forum: http://camunda.org/community/forum.html