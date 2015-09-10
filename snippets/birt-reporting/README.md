# Process Reporting with BIRT

	
## What does it demonstrate?

You can use the reporting tool of your choice for process reporting with Camunda BPM. This snippet shows how to use the Open Source Reporting Tool [Eclipse BIRT](http://www.eclipse.org/birt/phoenix/) for this.

BIRT queries data via SQL from the Camunda history tables and visualizes them in the diagram type of your choice, e.g.: 

![Screenshot Exampe Report][1]

[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/birt-reporting/screenshots/example1.PNG

## BIRT Components and Installation

In order to *create* a BIRT report you will have to install

 * [BIRT Designer](http://www.eclipse.org/birt/phoenix/build/). 

When you want to *view* your reports via some web browser you can leverage the

 * [BIRT Runtime](http://www.eclipse.org/birt/phoenix/build/#j2ee), e.g. for Java EE environments. You will have to extract a "birt.war" from the download and include your report file into the war:

![Screenshot Exampe Report][https://raw.github.com/camunda/camunda-consulting/master/snippets/birt-reporting/screenshots/download-birt-viewer.png]
![xxx][https://raw.github.com/camunda/camunda-consulting/master/snippets/birt-reporting/screenshots/xxx.png]

Now you can access the report here: http://localhost:8080/birt/frameset?__report=camunda.rptdesign

Note that you have to adjust the datasource in the report file if you *do not use the default H2 datasource* of our distribution:

```
            <property name="odaDriverClass">org.h2.Driver</property>
            <property name="odaJndiName">java:jboss/datasources/ProcessEngine</property>
```

Note that we provide a ready-to-go birt.war in the dist folder (but with a potentially outdated BIRT version!).



## Technical environment
- Tested on camunda BPM 7.3.0 on WildFly

