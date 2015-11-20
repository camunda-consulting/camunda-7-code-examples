/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.dmn.unittest;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.test.DmnEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.dmn.feel.operator.CustomFeelEngineFactory;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class SimpleTestCase {

  @Rule
  public DmnEngineRule rule = new DmnEngineRule(getDmnEngineConfiguration());

  public DmnEngine dmnEngine;
  public DmnDecision decision;

  protected DmnEngineConfiguration getDmnEngineConfiguration() {
    return new DefaultDmnEngineConfiguration()
        .feelEngineFactory(new CustomFeelEngineFactory());
  }

  @Before
  public void initEngineAndDecision() {
    dmnEngine = rule.getDmnEngine();
    decision = dmnEngine.parseDecision("orderDecision", this.getClass().getResourceAsStream("/Example.dmn"));
  }

  @Test
  public void customerWithoutHobbies() {
    Customer customer = new Customer();

    VariableMap variables = Variables.createVariables().putValue("customer", customer);

    DmnDecisionTableResult results = dmnEngine.evaluateDecisionTable(decision, variables);

    assertThat(results).isEmpty();
  }

  @Test
  public void customerWithOneHobby() {
    Customer customer = new Customer();
    customer.getHobbies().add("hiking");

    VariableMap variables = Variables.createVariables().putValue("customer", customer);

    DmnDecisionTableResult results = dmnEngine.evaluateDecisionTable(decision, variables);

    assertThat(results)
        .hasSize(1);

    assertThat(results.getSingleResult())
        .containsOnly(entry("result", "Hiking is great"));
  }

  @Test
  public void customerWithManyHobby() {
    Customer customer = new Customer();
    customer.setHobbies(Arrays.asList("hiking", "swimming", "biking", "sleeping"));

    VariableMap variables = Variables.createVariables().putValue("customer", customer);

    DmnDecisionTableResult results = dmnEngine.evaluateDecisionTable(decision, variables);

    assertThat(results)
        .hasSize(3);

    assertThat(results.collectEntries("result"))
        .containsOnly(
            "Biking is great",
            "Hiking is great",
            "Swimming is great"
        );
  }

}
