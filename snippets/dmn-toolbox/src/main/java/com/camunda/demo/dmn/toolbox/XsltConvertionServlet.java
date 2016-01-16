package com.camunda.demo.dmn.toolbox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.dmn.xlsx.XlsxConverter;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.xml.impl.util.IoUtil;

@WebServlet(value = "/xslx", loadOnStartup = 1)
public class XsltConvertionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			byte[] byteArray = IOUtils.toByteArray(request.getInputStream());
			byteArray = Base64.decodeBase64(byteArray);
			
			FileOutputStream outputStream = new FileOutputStream(new File("test.xlsx"));
			outputStream.write(byteArray);
			outputStream.close();
			
//			byte[] byteArray = getFile(request);
			XlsxConverter converter = new XlsxConverter();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
			DmnModelInstance dmnModelInstance = converter.convert(inputStream);
			IoUtil.writeDocumentToOutputStream(dmnModelInstance.getDocument(), response.getOutputStream());
		} catch (Exception ex) {
			throw new RuntimeException("Could not convert XSLX", ex);
		}
	}

	public byte[] getFile(HttpServletRequest request) throws FileUploadException {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List items = upload.parseRequest(request);

		// Process the uploaded items
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();

			if (item.isFormField()) {
				// if (item.getFieldName().equals("processDefinitionKey")) {
				// metaData.processDefinitionKey = item.getString();
				// } else if (item.getFieldName().equals("callbackUrl")) {
				// metaData.callbackUrl = item.getString();
				// } else {
				// processVariables.put(item.getFieldName(),
				// item.getString());
				// }

			} else {
				return item.get();
			}
		}
		return null;
	}
}
