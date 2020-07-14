package com.camunda.consulting.util;

import org.camunda.bpm.engine.impl.util.xml.Element;

import com.camunda.consulting.MessageTimeoutPluginProperties;
import com.camunda.consulting.exception.TimeoutDurationMissingException;
import com.camunda.consulting.exception.TimeoutListenerMissingException;
import com.camunda.consulting.exception.TimeoutListenerTypeMissingException;

import java.util.Optional;

public class ElementsUtil {

    public static String getExtensionPropertyValue(Element bpmnElement, String extensionName) {
        Optional<Element> extensionOptional = bpmnElement.element("extensionElements").element("properties").elements().stream()
                .filter(elementFilter -> elementFilter.attribute("name").equalsIgnoreCase(extensionName))
                .findFirst();

        if (extensionOptional.isPresent()) {
            Element extension = extensionOptional.get();
            String name = extension.attribute("name");
            if (extension.attribute("value") == null) {
            	if (name.equalsIgnoreCase(MessageTimeoutPluginProperties.TIMEOUTDURATION.name())) {
            		throw new TimeoutDurationMissingException("Value for extension " + name + " is empty");
            	} else if (name.equalsIgnoreCase(MessageTimeoutPluginProperties.TIMEOUTLISTENER.name())) {
            		throw new TimeoutListenerMissingException("Value for extension " + name + " is empty");
            	} else if (name.equalsIgnoreCase(MessageTimeoutPluginProperties.TIMEOUTLISTENERTYPE.name())) {
            		throw new TimeoutListenerTypeMissingException("Value for extension " + name + " is empty");
            	} 
                
            } else {
                return extension.attribute("value");
            }
        }

        return null;
    }
}
