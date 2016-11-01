package org.camunda.bpm.example.invoice.facade;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.example.box.BoxConnector;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("/rest")
public class UploadFacade {
		@POST
		@Path("/upload")
		@Consumes("multipart/form-data")
		public Response uploadFile(MultipartFormDataInput input) throws Exception {
			String uniqueID = "";
			String fileName = "";
			String boxfolder = null;
			Boolean success = true;
			
			Map<String, List<InputPart>> formParts = input.getFormDataMap();
			List<InputPart> boxfolderParts = formParts.get("boxfolder");
			for (InputPart inputPart : boxfolderParts) {
				boxfolder = inputPart.getBodyAsString();
			}
			List<InputPart> inPart = formParts.get("file");

			for (InputPart inputPart : inPart) {

				 try {

					// Retrieve headers, read the Content-Disposition header to obtain the original name of the file
					MultivaluedMap<String, String> headers = inputPart.getHeaders();
					fileName = parseFileName(headers);

					// Handle the body of that part with an InputStream
					InputStream inputStream = inputPart.getBody(InputStream.class, null);

					fileName = "/" + fileName;
					
					BoxConnector bx = new BoxConnector();
					JSONObject jsonObject = bx.upload(inputStream, fileName, boxfolder);
					uniqueID = jsonObject.getString("uniqueID");
					if (jsonObject.getBoolean("success") == false) {
						success = false;
					}
				  } catch (IOException e) {
					e.printStackTrace();
					success = false;
				  }

				}
			
			JSONObject outputObject = new JSONObject();
			outputObject.put("uniqueID", uniqueID);
			outputObject.put("filename", fileName);
			outputObject.put("success", success);
			return Response.status(200).entity(outputObject.toString()).build();
		}
		
		@GET
		@Path("/embedLink")
		public Response getEmbedLink(@QueryParam("fileId") String fileId,@QueryParam("boxfoldername") String boxfoldername) {
			BoxConnector bx = new BoxConnector();
			String embedLink = bx.getEmbedLink(boxfoldername, fileId);
			return Response.status(200).entity(embedLink).build();
		}
		
		private String parseFileName(MultivaluedMap<String, String> headers) {

			String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

			for (String name : contentDispositionHeader) {
				if ((name.trim().startsWith("filename"))) {
					String[] tmp = name.split("=");
					String fileName = tmp[1].trim().replaceAll("\"","");
					return fileName;
				}
			}
			return "randomName";
		}
	}
