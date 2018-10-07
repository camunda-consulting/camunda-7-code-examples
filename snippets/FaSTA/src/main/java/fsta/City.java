package fsta;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class City implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exe) throws Exception {
		// TODO Auto-generated method stub

		String name = (String) exe.getVariable("cityname");

		// These code snippets use an open-source library. http://unirest.io/java
		HttpResponse<String> response = Unirest.get(
				"https://duckduckgo-duckduckgo-zero-click-info.p.mashape.com/?callback=process_duckduckgo&format=json&no_html=1&no_redirect=1&q="
						+ name + "&skip_disambig=1")
				.header("X-Mashape-Key", "sjbEkLUmcWmshKXq0pWqXIkM6BzSp1e37zxjsnXSjAjEQfWwG4")
				.header("Accept", "application/json").asString();

		JSONObject jo = new JSONObject(response);
		String myNewJson = jo.get("body").toString();
		String theNewerJson = myNewJson.substring(19, myNewJson.length() - 2);
		jo = new JSONObject(theNewerJson);
		// jo.get("AbstractText");
		String def = jo.get("Abstract").toString(); // jo.toString();//jo.get("Abstract").toString();
		if (def.isEmpty()) {
			def = "You are UNDEFINED";
		}
		
		def = removeCityfromText(name.toLowerCase(), def);
		def = removeCityfromText(name, def);
		//remove name from text

		System.out.println(def);
		exe.setVariable("DescriptionOfTheCity", def);

	}
	
	private String removeCityfromText(String cityname, String description) {
		
		String newdescription = description.replace(cityname, "this place");
		
		 
		return newdescription;
		
	}

}
