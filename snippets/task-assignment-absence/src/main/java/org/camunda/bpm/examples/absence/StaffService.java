package org.camunda.bpm.examples.absence;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@Path("staff")
public class StaffService {
  
  private static final String SLASH = System.getProperty("file.separator");
  private static final String USER_HOME = System.getProperty("user.home");

  @POST
  @Consumes("application/json")
  public void saveStaffJson(String json) throws IOException {
    FileUtils.writeStringToFile(getStaffFile(), json);
  }

  @GET
  @Produces("application/json")
  public String getStaffJson() throws IOException, URISyntaxException {
    if (!getStaffFile().exists()) {
      // if json file does not yet exist, create it by using the default one from classpath
      FileUtils.writeByteArrayToFile(
          getStaffFile(),
          IOUtils.toByteArray(this.getClass().getResourceAsStream("/staff.json")));
    }
    String json = FileUtils.readFileToString(getStaffFile());
    return json;
  }
  
  public static File getStaffFile() {
    return new File(USER_HOME + SLASH + "camunda-demo-absence" + SLASH + "staff.json");
  }

}
