package com.camunda.training;

import org.camunda.bpm.cockpit.Cockpit;
import org.camunda.bpm.cockpit.plugin.spi.CockpitPlugin;
import org.camunda.bpm.cockpit.plugin.test.AbstractCockpitPluginTest;
import org.junit.Assert;
import org.junit.Test;

public class CockpitNavigationPluginTests extends AbstractCockpitPluginTest {
  @Test
  public void testPluginDiscovery() {
    CockpitPlugin samplePlugin = Cockpit.getRuntimeDelegate().getAppPluginRegistry()
        .getPlugin("cockpit-navigation-plugin");

    Assert.assertNotNull(samplePlugin);
  }
}
