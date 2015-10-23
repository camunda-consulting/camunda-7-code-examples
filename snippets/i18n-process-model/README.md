Translate your process model on the fly
=========================

If you have multi-lingual audiences for your process model 
you can

- translate the process model in the BPMN XML and afterwards
- render it using bpmn.io.

This example translates the following process which was modeled using I18N_KEYS:
![BPMN Process](src/main/resources/process.png)

And looks like this after translation:

![Translated Model in BPMN.IO](translated-model.png)

How does it work?
--------------

Translations are authored using simple properties files

- [en.properties](https://github.com/camunda/camunda-consulting/blob/master/snippets/i18n-process-model/src/main/resources/en.properties)
- [de.properties](https://github.com/camunda/camunda-consulting/blob/master/snippets/i18n-process-model/src/main/resources/de.properties)

The translation is done by using XPath to find and replace names:

- [ProcessModelTranslator.java](https://github.com/camunda/camunda-consulting/blob/master/snippets/i18n-process-model/src/main/java/com/camunda/demo/i18n/processmodel/ProcessModelTranslator.java)

This translated BPMN.xml is handed out via a [Simple Servlet](https://github.com/camunda/camunda-consulting/blob/master/snippets/i18n-process-model/src/main/java/com/camunda/demo/i18n/processmodel/GetTranslatedProcessModelServlet.java) to have as less environment requirements as possible for the example.
In real-life use REST, JSF or whatever you always use for this job.

Last step is to render the model using bpmn.io:

- [index.html](https://github.com/camunda/camunda-consulting/blob/master/snippets/i18n-process-model/src/main/webapp/index.html)


How to use it?
--------------

Build it via Maven and deploy the WAR to your Camunda Installation.

Once you deployed the application access the process model via
[http://localhost:8080/i18n-process-model/](http://localhost:8080/i18n-process-model/).


Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.3.0.


Improvement Ideas
------------------------

- Use [GNU Gettext](https://de.wikipedia.org/wiki/GNU_gettext) to use english wording as keys and keep the original BPMN readable
  - [Java Library](https://code.google.com/p/gettext-commons/)
- Use [CAT-Tools] (https://de.wikipedia.org/wiki/Computerunterst%C3%BCtzte_%C3%9Cbersetzung) for translation


License
-------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
