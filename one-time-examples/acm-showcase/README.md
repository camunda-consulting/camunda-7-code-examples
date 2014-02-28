# camunda BPM: ACM Showcase / Pilot

Table of Contents

- [What does it demonstrate?](#what-does-it-demonstrate)
- [Technical environment](#technical-environment)
- [Getting Started](#getting-started)
	
	
## What does it demonstrate?

- This demo is a stripped down version of a "Proof of Concept" (POC) project at a customer. 
- There we developed a pilot for Adapative Case Management (ACM) on the camunda BPM platform. 
- It executes the standard "CMMN" (Case Management Model & Notation) on an extended camunda BPM platform (which includes ACM in a very early alpha stage)
- It shows a possible User Interface for Case Management

![Screenshot "Kredit-Cockpit"][1]

[1]: https://raw.github.com/camunda/camunda-consulting/tree/master/one-time-examples/acm-showcase/screenshot.png



## Technical environment
[camunda BPM platform 7.1.0-alpha1-cm (7.1.0-alpha1 with ACM extensions)](https://github.com/berndruecker/camunda-bpm-platform/tree/case-management).

*This is an instable hacked version on a private branch*. It was built for a pilot project. There is no guruantee that this will be productized or that it will look like this in the product in a later stage.



## Getting Started

* Build the camunda BPM Platform yourself from this branch: https://github.com/berndruecker/camunda-bpm-platform/tree/case-management. This will result in version *7.1.0-alpha1-cm*.
* Clone this repo and build the project using Maven
* Deploy the resulting WAR on the container (*tested only with JBoss AS 7*)


## Known Issues

The list of known issues or missing features is quite long. Here the most important ones:

- No CaseDefinition entity / database. No versioning. 
- Pretty hacky Deployment-Cache, problems when restarting the server are quite common. Best delete your database in this cases.
- No History of case instances
- Some CMMN Task Attributes are not yet supported (but would make sense):
  - Repeatable
  - Required
  - Blocking
  - "Conditional available"
- Service Task are not yet supported
- ...