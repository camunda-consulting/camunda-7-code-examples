package org.camunda.bpm.examples.noarquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.camunda.bpm.examples.Helper;
import org.junit.Test;
import org.w3c.dom.Element;

public class XmlParserTest {

  @Test
  public void testFourEyesGroupParsing() {
    InputStream inputStream = this.getClass().getResourceAsStream("/FourEyesAdvanced.bpmn");
    Element taskExtensions = Helper.getUserTaskExtensions(inputStream, "first_check", Helper.FOUR_EYES_GROUP_NAME);

    assertNotNull(taskExtensions);
    assertEquals("asset1", taskExtensions.getAttribute("name"));
  }
}
