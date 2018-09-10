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
package org.camunda.bpm.example.engine;


import java.util.logging.Logger;
import org.slf4j.MDC;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

/**
 * A {@link CommandInterceptor} which throws exceptions if {@link #shouldBlock} is enabled.
 *
 * @author Daniel Meyer
 *
 */
public class EngineNameCommandInterceptor extends CommandInterceptor {

  private String engineName;
  private final static Logger LOG = Logger.getLogger(EngineNameCommandInterceptor.class.getName());
  		
  		public void setEngineName(String engineNameInput) {
  			engineName = engineNameInput;
  		}
  
		@Override
		public <T> T execute(Command<T> command) {
				
				MDC.put("engin-name", engineName);
		        try {
		        	next.execute(command);
		        } finally {
		            MDC.remove("engin-name");
		        }
		        
			return null;
		}


}
