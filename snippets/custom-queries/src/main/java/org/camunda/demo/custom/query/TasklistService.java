package org.camunda.demo.custom.query;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.camunda.bpm.engine.impl.db.sql.DbSqlSessionFactory;

@Stateless
@Named
public class TasklistService {

	public Properties getProps(ProcessEngineConfigurationImpl conf) {
		Properties properties = new Properties();
		properties.put("prefix", conf.getDatabaseTablePrefix());
		if (conf.getDatabaseType() != null) {
			String databaseType = conf.getDatabaseType();
			properties.put("limitBefore", DbSqlSessionFactory.databaseSpecificLimitBeforeStatements.get(databaseType));
			properties.put("limitAfter", DbSqlSessionFactory.databaseSpecificLimitAfterStatements.get(databaseType));
			properties.put("innerLimitAfter", DbSqlSessionFactory.databaseSpecificInnerLimitAfterStatements.get(databaseType));
			properties.put("limitBetween", DbSqlSessionFactory.databaseSpecificLimitBetweenStatements.get(databaseType));
			properties.put("limitBetweenClob", DbSqlSessionFactory.databaseSpecificLimitBetweenClobStatements.get(databaseType));
			properties.put("orderBy", DbSqlSessionFactory.databaseSpecificOrderByStatements.get(databaseType));
			properties.put("limitBeforeNativeQuery", DbSqlSessionFactory.databaseSpecificLimitBeforeNativeQueryStatements.get(databaseType));

			properties.put("bitand1", DbSqlSessionFactory.databaseSpecificBitAnd1.get(databaseType));
			properties.put("bitand2", DbSqlSessionFactory.databaseSpecificBitAnd2.get(databaseType));
			properties.put("bitand3", DbSqlSessionFactory.databaseSpecificBitAnd3.get(databaseType));

			properties.put("trueConstant", DbSqlSessionFactory.databaseSpecificTrueConstant.get(databaseType));
			properties.put("falseConstant", DbSqlSessionFactory.databaseSpecificFalseConstant.get(databaseType));

			properties.put("dbSpecificDummyTable", DbSqlSessionFactory.databaseSpecificDummyTable.get(databaseType));
			properties.put("dbSpecificIfNullFunction", DbSqlSessionFactory.databaseSpecificIfNull.get(databaseType));

			Map<String, String> constants = DbSqlSessionFactory.dbSpecificConstants.get(databaseType);
			for (Entry<String, String> entry : constants.entrySet()) {
				properties.put(entry.getKey(), entry.getValue());
			}

		}
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
				getProps((ProcessEngineConfigurationImpl) processEngineConfiguration));
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
