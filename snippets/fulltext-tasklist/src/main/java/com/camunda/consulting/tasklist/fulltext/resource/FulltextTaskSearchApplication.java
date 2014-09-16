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
package com.camunda.consulting.tasklist.fulltext.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * @author Ingo Richtsmeier
 *
 */
public class FulltextTaskSearchApplication extends Application {
  
  private Set<Object> singletons = new HashSet<Object>();
  private Set<Class<?>> empty = new HashSet<Class<?>>();
  
  public FulltextTaskSearchApplication() {
    this.singletons.add(new FulltextTaskResource());
  }

  @Override
  public Set<Class<?>> getClasses() {
    return this.empty;
  }

  @Override
  public Set<Object> getSingletons() {
    return this.singletons;
  }
}
