package com.camunda.consulting.bpmn_db_exporter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbExporter 
{
	
	public static final String TARGET_DIR = "target" + File.separator + "export-prod" + File.separator;
	
	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
	public static final String DB_URL = "YOUR_JDBC_URL";
	
	public static final String USERNAME = "YOUR_USERNAME";
	public static final String PASSWORD = "YOUR_PASSWORD";
    
    public static void main( String[] args )
    {
    	new File(TARGET_DIR).mkdirs();
    	
    	Connection conn = null;
    	Statement statement = null;
    	
    	System.out.println("Connecting to DB ...");
    	
    	try {
    		Class.forName(JDBC_DRIVER);
    		conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    		
    		statement = conn.createStatement();
    		    		
    		String sql = "select ACT_RE_PROCDEF.ID_ as PROCDEF_ID, ACT_GE_BYTEARRAY.ID_ as BYTEARRAY_ID, ACT_GE_BYTEARRAY.BYTES_ "+
    						"from ACT_GE_BYTEARRAY join ACT_RE_PROCDEF on ACT_RE_PROCDEF.DEPLOYMENT_ID_= ACT_GE_BYTEARRAY.DEPLOYMENT_ID_ and ACT_RE_PROCDEF.RESOURCE_NAME_ = ACT_GE_BYTEARRAY.NAME_ "+
    						"where ACT_GE_BYTEARRAY.NAME_ LIKE '%.bpmn'";

    		
    		ResultSet rs = statement.executeQuery(sql);	
    		
    		while(rs.next()) {
    			Path file = Paths.get(TARGET_DIR + rs.getString("PROCDEF_ID").replaceAll(":", "#") + "#" + rs.getString("BYTEARRAY_ID") + ".bpmn");
    			Files.write(file, rs.getBytes("BYTES_"),StandardOpenOption.CREATE_NEW);
    		}
    		
    	rs.close();
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	finally {
        	System.out.println("Disconnecting from DB ...");    		
    		if(statement != null) {
    			try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	
    	System.out.println("Goodbye");
    	
        
    }
}
