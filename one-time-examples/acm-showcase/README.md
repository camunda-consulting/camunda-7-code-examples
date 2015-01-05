# DEPRECTAED!

Please not that this example is deprecated. It used a prototype of CMMN - a real implementation is relesed as part of camunda BPM in the meanwhile.

Please refer to the [Underwriting Example] (https://github.com/camunda/camunda-consulting/tree/master/one-time-examples/2015-01-webinars/webinar-cmmn) instead!


# camunda BPM: ACM Showcase / Pilot

Table of Contents

- [What does it demonstrate?](#what-does-it-demonstrate)
- [Technical environment](#technical-environment)
- [Getting Started](#getting-started)
	
	
## What does it demonstrate?

- This demo is a stripped down version of a "Proof of Concept" (POC) project at a customer. 
- There we developed a pilot for Adapative Case Management (ACM) on the camunda BPM platform. 
- It executes the standard "CMMN" (Case Management Model & Notation) on an extended camunda BPM platform (which includes ACM in a very early alpha stage).
- It shows a possible User Interface for Case Management

![Screenshot "Kredit-Cockpit"][1]

[1]: https://raw.github.com/camunda/camunda-consulting/master/one-time-examples/acm-showcase/screenshot.png


## Technical environment
[camunda BPM platform 7.1.0-Final-acm (7.1.0-Final with ACM prototyp)](https://github.com/berndruecker/camunda-bpm-platform/tree/7.1.0-acm).

**This is an instable hacked version on a private branch**. It was built for a pilot project. There is no guruantee that this will look like this in the product in a later stage.
See [JIRA](https://app.camunda.com/jira/secure/RapidBoard.jspa?rapidView=39&quickFilter=158) for details on CMMN implementation in the current platform.



## Getting Started

* Build the camunda BPM Platform yourself from this branch: https://github.com/berndruecker/camunda-bpm-platform/tree/case-management. This will result in version *7.1.0-Final-acm*.
* Clone this repo and build the project using Maven
* Deploy the resulting WAR on the container (*tested only with JBoss AS 7*)

## CMMN

This example contains a CMMN model: [creditApplication.cmmn](https://github.com/camunda/camunda-consulting/blob/master/one-time-examples/acm-showcase/src/main/resources/creditApplication.cmmn).

CMMN is a specification of the OMG: [CMMN](http://www.omg.org/spec/CMMN/).

Currently there is limited modeling support, but the [CMMN Web Modeler](http://www.cmmnwebmodeler.com/) might help. The CMMN looks like this:

![Excerpt of the CMMN][2]

[2]: https://raw.github.com/camunda/camunda-consulting/master/one-time-examples/acm-showcase/src/main/resources/creditApplication.png

## Known Issues

The list of known issues or missing features is quite long. Here the most important ones:

- No CaseDefinition entity / database. No versioning. 
- No History of case instances
- Some CMMN Task Attributes are not yet supported (but would make sense):
  - Repeatable
  - Required
  - Blocking
  - "Conditional available"
- Service Task are not yet supported
- ...
