package ut.org.camunda.consulting;

import org.junit.Test;
import org.camunda.consulting.api.MyPluginComponent;
import org.camunda.consulting.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}