package com.camunda.consulting.util;

import org.camunda.bpm.engine.impl.util.xml.Element;

import java.util.Optional;

public class ElementsUtil {

    public static String getExtensionPropertyValue(Element bpmnElement, String extensionName) {
        Optional<Element> extensionOptional = bpmnElement.element("extensionElements").element("properties").elements().stream()
                .filter(elementFilter -> elementFilter.attribute("name").equalsIgnoreCase(extensionName))
                .findFirst();

        if (extensionOptional.isPresent()) {
            Element extension = extensionOptional.get();
            if (extension.attribute("value") == null) {
                throw new RuntimeException("Value for extension " + extension.attribute("name") + " is empty");
            } else {
                return extension.attribute("value");
            }
        }

        return null;
    }
}
