package org.camunda.bpm.demo.cockpit.plugin.kpi;

import org.camunda.bpm.cockpit.Cockpit;
import org.camunda.bpm.cockpit.plugin.spi.CockpitPlugin;
import org.camunda.bpm.cockpit.plugin.test.AbstractCockpitPluginTest;
import org.junit.Assert;
import org.junit.Test;

public class ProcessCountBarPluginTest extends AbstractCockpitPluginTest {

  @Test
  public void testPluginDiscovery() {
    CockpitPlugin samplePlugin = Cockpit.getRuntimeDelegate().getAppPluginRegistry().getPlugin("reporting-process-count");

    Assert.assertNotNull(samplePlugin);
  }
}