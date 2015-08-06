package com.camunda.demo.versicherungsneuantrag.adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import de.kwsoft.mtext.adapters.ws.ConfigurationProperties;
import de.kwsoft.mtext.adapters.ws.ConfigurationProperty;
import de.kwsoft.mtext.adapters.ws.IntegrationAdapter;
import de.kwsoft.mtext.adapters.ws.IntegrationAdapterException_Exception;
import de.kwsoft.mtext.adapters.ws.IntegrationAdapterService;
import de.kwsoft.mtext.adapters.ws.NamedData;
import de.kwsoft.mtext.adapters.ws.PrintResult;

public class MTEXT {

  public static byte[] generateAndDistributeDocument(String xml, String dataBinding) throws IntegrationAdapterException_Exception, IOException {
    final String documentName = "DoesNotMatter";
    final String dataSourceName = "DATA";

    final IntegrationAdapterService service = new IntegrationAdapterService();
    final IntegrationAdapter integrationAdapter = service.getIntegrationAdapterPort();

    final Properties props = new Properties();
    props.load(MTEXTRejectionAdapter.class.getClassLoader().getResourceAsStream("credentials.properties"));
    final ConfigurationProperties config = convert(props);

    final NamedData nd = new NamedData();
    nd.setName(dataSourceName);
    nd.setData(xml.getBytes("UTF-8"));
    final List<NamedData> namedDataList = new ArrayList<NamedData>();
    namedDataList.add(nd);

    // create document using M/TEXT and distribute using M/OMS
    PrintResult printResult = integrationAdapter.printTemporaryDocumentFromBindingWithResult(documentName, dataBinding, namedDataList, config);

    // simulate: get document from archive system
    final byte[] pdf = integrationAdapter.exportTemporaryDocumentFromBinding(documentName, dataBinding, namedDataList, "application/pdf", config);
    
    return pdf;
  }

  /**
   * Converts the given properties into ConfigurationProperties.
   * 
   * @param props
   *          the properties to be converted.
   * @return the conversion result.
   */
  private static ConfigurationProperties convert(final Properties props) {
    final ConfigurationProperties result = new ConfigurationProperties();
    final Iterator<String> it = props.stringPropertyNames().iterator();
    while (it.hasNext()) {
      final String name = it.next();
      final ConfigurationProperty property = new ConfigurationProperty();
      property.setKey(name);
      property.setValue(props.getProperty(name));
      result.getProperties().add(property);
    }
    return result;
  }

}
