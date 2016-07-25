package org.camunda.demo.custom.query;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;

@Stateless
@Named
public class TasklistService {

	public Properties getSqlSessionFactoryProperties(ProcessEngineConfigurationImpl conf) {
		Properties properties = new Properties();		
		ProcessEngineConfigurationImpl.initSqlSessionFactoryProperties(properties, conf.getDatabaseTablePrefix(), conf.getDatabaseType());
		return properties;
	}

	public SqlSessionFactory createMyBatisSqlSessionFactory(InputStream config) {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		DataSource dataSource = processEngineConfiguration.getDataSource();

		// use this transaction factory if you work in a non transactional
		// environment
		// TransactionFactory transactionFactory = new JdbcTransactionFactory();

		// use this transaction factory if you work in a transactional
		// environment (e.g. called within the engine or using JTA)
		TransactionFactory transactionFactory = new ManagedTransactionFactory();

		Environment environment = new Environment("customTasks", transactionFactory, dataSource);

		XMLConfigBuilder parser = new XMLConfigBuilder( //
				new InputStreamReader(config), //
				"", // set environment later via code
				getSqlSessionFactoryProperties((ProcessEngineConfigurationImpl) processEngineConfiguration));
		Configuration configuration = parser.getConfiguration();
		configuration.setEnvironment(environment);
		configuration = parser.parse();

		configuration.setDefaultStatementTimeout(processEngineConfiguration.getJdbcStatementTimeout());

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		return sqlSessionFactory;
	}

	public List<TaskDTO> getTasksForRegion(final String assignee, final String region) {
		InputStream config = this.getClass().getResourceAsStream("/customMybatisConfiguration.xml");

		SqlSessionFactory sqlSessionFactory = createMyBatisSqlSessionFactory(config);

		// compare to http://www.mybatis.org/mybatis-3/getting-started.html

		SqlSession session = sqlSessionFactory.openSession();
		try {
			// You can use this object to leverage Camunda Pagination features
			ListQueryParameterObject queryParameterObject = new ListQueryParameterObject();
			queryParameterObject.setParameter(region);

			List<TaskDTO> tasks = session.selectList("customTask.selectTasksForRegion", queryParameterObject);
			return tasks;
		} finally {
			session.close();
		}
	}

}
