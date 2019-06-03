using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading;
using System.Threading.Tasks;
using System.Text;
using Newtonsoft.Json.Linq;
// Be sure to install the Newtonsoft JSON library - dotnet add package Newtonsoft.Json --version x.x.x.x
// Run the program by issuing command - dotnet run CSharpWorker.cs your_topic

namespace ConsoleProgram
{
	public class CSharpExternalWorker
	{
		static void Main(string[] args)
		{	
			// Check to see if a topic has been sent
			string topic;
			if(args.Length > 1) {
				Console.WriteLine("topic is "+args[1]);
				topic = (string) args[1];
				while(true) {
					CallREST(topic).Wait();
				}
			} else {
				Console.WriteLine("Need to pass in a topic as a parameter.");
				return;
			}				
			
		}
		
		private static async Task CallREST(string topic) {
			
			HttpClient client = new HttpClient();
			
			// Add an Accept header for JSON format.
			client.DefaultRequestHeaders.Accept.Add(
			new MediaTypeWithQualityHeaderValue("application/json"));
			
			// Provide a JSON object with a worker id, max tasks, usePriority, asyncResponseTimeout(aka long polling), an array of topics to subscribe to, and the lock duration in milliseconds with 
			// escaped quotation marks (\")
			
			string body = "{\"workerId\":\"aWorkerId\", \"maxTasks\":1, \"usePriority\":true, \"asyncResponseTimeout\": 5000, \"topics\":[{\"topicName\": \""+topic+"\", \"lockDuration\": 60000 }] }";
			
			var content = new StringContent(body, Encoding.UTF8, "application/json");

			// Fetch and lock a job
			HttpResponseMessage response = await client.PostAsync("http://localhost:8080/engine-rest/external-task/fetchAndLock", content);  
			if (response.IsSuccessStatusCode)
			{
				// Parse the response body.
				var jsonString = await response.Content.ReadAsStringAsync();
				var jArray = JArray.Parse(jsonString);
				
				// If a job is found, send a POST to complete it using the id from the response as well as any other business logic before completing the job
				if(jArray.Count > 0){
					JObject jObj = (JObject) jArray[0];
					string id = (string) jObj["id"];
					Console.WriteLine("Job found, completing it");
					string url = "http://localhost:8080/engine-rest/external-task/"+id+"/complete";
					body = "{\"workerId\": \"aWorkerId\", \"variables\": {\"sampleVariable\": {\"value\": \"someValue\"}}}";
					content = new StringContent(body, Encoding.UTF8, "application/json");
					response = await client.PostAsync(url, content); 
					if (response.IsSuccessStatusCode) {				
						Console.WriteLine("Job completed");
					} else
						{
							Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
					}	
				} else {	
					Console.WriteLine("No jobs found");
				}
			}
				else
				{
					Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
				}

			//Make any other calls using HttpClient here.

			//client.Dispose(); // optional
			Console.WriteLine("end of polling cycle");
		}	
	}
}