package com.camunda.fox.demo.outerspace.task_name_beautifier.nonarquillian;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.camunda.fox.demo.outerspace.task_name_beautifier.TaskNameBeautifier;


public class TaskNameBeautifierTest {

  private TaskNameBeautifier beautifier;

  @Before
  public void setUp() throws Exception {
    beautifier = new TaskNameBeautifier();
  }

  @Test
  public void testBeautifyTaskName() {
    assertEquals("Geschäftspartner identifizieren", beautifier.beautifyTaskName("Geschäfts-partner identifizieren"));
    assertEquals("Metadaten erfassen", beautifier.beautifyTaskName("Metadaten erfassen"));
    assertEquals("Anschlussobjekt (AO) identifizieren", beautifier.beautifyTaskName("Anschluss-objekt (AO) identifizieren"));
    assertEquals("Verbrauchstelle (VS) identifizieren", beautifier.beautifyTaskName("Verbrauch-stelle (VS) identifizieren"));
    assertEquals("Vertragskonto (VK) identifizieren", beautifier.beautifyTaskName("Vertragskonto (VK) identifizieren"));
    assertEquals("Task with terribly long name", beautifier.beautifyTaskName("Task with terri-  bly long name"));

    // edge cases without replacement
    assertEquals("Party at SO-36", beautifier.beautifyTaskName("Party at SO-36")); // assuming special name
    assertEquals("camunda fox BPM-Plattform installieren", beautifier.beautifyTaskName("camunda fox BPM-Plattform installieren")); // assuming dash was used on purpose

    // questionable edge case
    assertEquals("Implement Serviceoriented Architecture", beautifier.beautifyTaskName("Implement Service-oriented Architecture"));
  }

}
