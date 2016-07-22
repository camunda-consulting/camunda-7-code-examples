package com.camunda.consulting.webinar.externaltask;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.consulting.webinar.externaltask.DelegateCodeSlides.WarehouseService;

public class DelegateCodeSlides {
	

	public static class WarehouseServiceFactory {
		static WarehouseService getWarehouseService() {
			return new WarehouseService();
		}
	}
	public static class WarehouseService {

		public String reserveGoods(List<OrderItem> items) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	public static class OrderItem {
		
	}
	
	static	
	@SuppressWarnings("unchecked")
	
	public class ReserveGoodsAdapter implements JavaDelegate {

		@Override
		public void execute(DelegateExecution context) throws Exception {
			// Prepare data for service call
			List<OrderItem> items = (List<OrderItem>) context.getVariable("orderItemList");
			
			// Get service endpoint (factory, service registry, injection, ...)
			WarehouseService service = WarehouseServiceFactory.getWarehouseService();
			
			// Call service, might throw an exception which could be directly catched in BPMN
			String reservationNumber = service.reserveGoods(items);
			
			// Process result
			context.setVariable("warehouseReservationNumber", reservationNumber);
		}
	}
	
	
}
