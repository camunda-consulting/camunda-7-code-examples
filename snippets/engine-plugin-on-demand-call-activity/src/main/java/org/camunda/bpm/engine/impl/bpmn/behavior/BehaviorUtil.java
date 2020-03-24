package org.camunda.bpm.engine.impl.bpmn.behavior;

import org.camunda.bpm.engine.delegate.Expression;

public class BehaviorUtil {

    public static String getClassName(CallableElementActivityBehavior c){
        return c.className;
    }

    public static Expression getExpression(CallableElementActivityBehavior c){
        return c.expression;
    }
}
