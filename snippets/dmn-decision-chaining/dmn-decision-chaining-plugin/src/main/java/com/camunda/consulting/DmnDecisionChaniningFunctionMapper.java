package com.camunda.consulting;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.impl.util.ReflectUtil;
import org.camunda.bpm.engine.variable.context.VariableContext;
import org.camunda.bpm.engine.impl.javax.el.FunctionMapper;

/**
 * A FunctionMapper which resolves our DMN chaining function for Expression
 * Language.
 * 
 * <p>
 * Lazy loading: This implementation supports lazy loading: the Java Methods are
 * loaded upon the first request.
 * </p>
 * 
 * <p>
 * Caching: once the methods are loaded, they are cached in a Map for efficient
 * retrieval.
 * </p>
 * 
 */
public class DmnDecisionChaniningFunctionMapper extends FunctionMapper {

	public static Map<String, Method> functionCache = null;

	public Method resolveFunction(String prefix, String localName) {
		// methods are used un-prefixed
		ensureSpinFunctionMapInitialized();
		return functionCache.get(localName);
	}

	protected void ensureSpinFunctionMapInitialized() {
		if (functionCache == null) {
			synchronized (DmnDecisionChaniningFunctionMapper.class) {
				if (functionCache == null) {
					functionCache = new HashMap<String, Method>();
					createMethodBindings();
				}
			}
		}
	}

	protected void createMethodBindings() {
		// do manually because of https://app.camunda.com/jira/browse/CAM-5429
		// functionCache.put("dmnEvaluate",
		// ReflectUtil.getMethod(DecisionTableEvaluator.class, "evaluate",
		// String.class, VariableContext.class));
		// functionCache.put("dmn",
		// ReflectUtil.getMethod(DecisionTableEvaluator.class, "singleResult",
		// String.class, VariableContext.class));
		try {
			Method methodSingleResult = DecisionTableEvaluator.class.getMethod("singleResult", String.class, VariableContext.class);
			functionCache.put("dmn", methodSingleResult);
		} catch (Exception ex) {
			throw new RuntimeException("Could not create DMN function. Check nested exception for details", ex);
		}
	}

}