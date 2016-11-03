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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.example.cmis.CMISConnector;
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
			String folderName = null;
			Boolean success = true;
			
			Map<String, List<InputPart>> formParts = input.getFormDataMap();
			List<InputPart> folderNameParts = formParts.get("folderName");
			for (InputPart inputPart : folderNameParts) {
				folderName = inputPart.getBodyAsString();
			}
			List<InputPart> inPart = formParts.get("file");

			for (InputPart inputPart : inPart) {

				 try {

					// Retrieve headers, read the Content-Disposition header to obtain the original name of the file
					MultivaluedMap<String, String> headers = inputPart.getHeaders();
					fileName = parseFileName(headers);

					// Handle the body of that part with an InputStream
					InputStream inputStream = inputPart.getBody(InputStream.class, null);
					
					CMISConnector cmis = new CMISConnector();
					JSONObject jsonObject = new JSONObject();
					jsonObject = cmis.upload(inputStream, fileName, folderName);
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
		@Path("/filePreview")
		public Response getFilePreview(@QueryParam("inline") Boolean inline,@QueryParam("fileId") String fileId, @QueryParam("fileName") String fileName) throws IOException {
			CMISConnector cmis = new CMISConnector();
			byte[] docStream = cmis.downloadFile(fileId);
			String contentDisposition = "attachment; filename = "+fileName;
			if (inline!=null && inline==true) {
				contentDisposition="inline; filename = "+fileName;
				return Response.ok(docStream, "application/pdf").header("content-disposition",contentDisposition).build();
			} else {
				return Response.ok(docStream, MediaType.APPLICATION_OCTET_STREAM).header("content-disposition",contentDisposition).build();
			}
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
