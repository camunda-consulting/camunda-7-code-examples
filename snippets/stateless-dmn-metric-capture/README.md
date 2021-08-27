# Capturing DI metrics from a stateless DMN engine
An example of how to capture Decision Instance (DI) metrics when using an Embedded DMN engine


## How does it work?
The main class - EmbeddedDMNEngine, creates a DMN engine with a metric collection feature in its configuration.
A sample DMN with two decision tables is deployed. A sample variables map is created and the decision tables are evaluated.
Lastly, the Decision Instance metric is queried. Calling Decision 1 will result in one DI added. Calling Decision 2 will result in two DIs added. 
The results of the evaluations and DI extract are displayed in sysout.


### Running the application
Simply run the main method of EmbeddedDMNEngine class.


