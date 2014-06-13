package org.camunda.bpm.cockpit.plugin.xml.test;

import org.camunda.bpm.cockpit.Cockpit;
import org.camunda.bpm.cockpit.plugin.spi.CockpitPlugin;
import org.camunda.bpm.cockpit.plugin.test.AbstractCockpitPluginTest;
import org.junit.Assert;
import org.junit.Test;

public class TestXMLCockpitPlugin extends AbstractCockpitPluginTest {
	
	@Test
	public void testPluginDiscovery() {
		CockpitPlugin xmlPlugin = Cockpit.getRuntimeDelegate().getPluginRegistry().getPlugin("xml-plugin");
		Assert.assertNotNull("Plugin nicht gefunden", xmlPlugin);
	}

}
