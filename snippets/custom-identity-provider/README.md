# Custom Identity Provider and Filter generator

This project contains 2 snippets: a custom identity provider and a generator for tasklist filters

## Custom identity Provider

A simple custom identity provider that is bound to a custom identity service. This service provides static, in-memory user data.
The identity provider is installed to the engine using a process engine plugin.

## Filter generator

A process engine plugin that creates required filters. Focus is on expressions used in filter fields like `${currentUser()}`, `${currentUserGroups()}` but I have also tried `${now()}` which is a function inside the Camunda expression context by default.