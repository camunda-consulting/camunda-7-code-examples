package com.camunda.consulting.bpmn_db_importer;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.camunda.consulting.bpmn_db_exporter.DbExporter;

public class DbImporter {

	static final String SOURCE_DIR = DbExporter.TARGET_DIR;
	static final String JDBC_DRIVER = DbExporter.JDBC_DRIVER;
	static final String DB_URL = DbExporter.DB_URL;
	static final String USERNAME = DbExporter.USERNAME;
	static final String PASSWORD = DbExporter.PASSWORD;

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			
			System.out.println("Getting files from " + SOURCE_DIR);
			
			File parentDir = new File(SOURCE_DIR);
			FilenameFilter filenameFilter = (dir, name) -> name.matches(".*\\.bpmn");
			
			System.out.println("Connecting to DB ...");
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			
			statement = conn.prepareStatement("UPDATE ACT_GE_BYTEARRAY" +
					" SET BYTES_ = ?" + 
					" WHERE ID_ = ?");

			for (File file : parentDir.listFiles(filenameFilter)) {			

				String fileName = file.getName();
				String byteArryId = fileName.substring(fileName.lastIndexOf("#")+1).split("\\.")[0];		
				
				
				statement.setBytes(1, Files.readAllBytes(file.toPath()));
				statement.setString(2, byteArryId);
				
				statement.executeQuery();
			
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Disconnecting from DB ...");
			if(statement != null) {
    			try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		System.out.println("Goodbye");

	}

}
