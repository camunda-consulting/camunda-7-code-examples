package fsta;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class LoopListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution exe) throws Exception {
		// TODO Auto-generated method stub
		
		Long loop = (Long) exe.getVariable("qualityloop");
		if(loop == null) {
		loop = 0L;
	}
		else {
		loop++;
		}
		
		exe.setVariable("qualityloop", loop);
	}
	
	
}
