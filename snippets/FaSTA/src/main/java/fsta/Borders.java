package fsta;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class Borders implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exe) throws Exception {
		// TODO Auto-generated method stub
		
		
		String name = "Berlin";


		HttpResponse<String> response = Unirest.get("https://restcountries-v1.p.mashape.com/capital/" + name)
				.header("X-Mashape-Key", "sjbEkLUmcWmshKXq0pWqXIkM6BzSp1e37zxjsnXSjAjEQfWwG4")
				.header("Accept", "application/json")
				.asString();
		
//		JSONObject jo = new JSONObject(response);
//		String myNewJson = jo.get("body").toString();
//		
//		jo = new JSONObject(theNewerJson);
//		// jo.get("AbstractText");
//		String def = jo.get("borders").toString(); // jo.toString();//jo.get("Abstract").toString();
//		if (def.isEmpty()) {
//			def = "You are UNDEFINED";
//		}
//
//		System.out.println(def);
//		exe.setVariable("Borders", def);

	
		
	}
}
		