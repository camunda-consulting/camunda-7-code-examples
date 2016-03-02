package com.camunda.demo.dmn.toolbox;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
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
			XlsxConverter converter = new XlsxConverter();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
			DmnModelInstance dmnModelInstance = converter.convert(inputStream);
			IoUtil.writeDocumentToOutputStream(dmnModelInstance.getDocument(), response.getOutputStream());
		} catch (Exception ex) {
			throw new RuntimeException("Could not convert XSLX", ex);
		}
	}

}
