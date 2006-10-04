package org.sakaiproject.cluster.impl;

import java.util.List;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.sakaiproject.cluster.api.ClusterSQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class ClusterSQLHsqlTest extends AbstractTransactionalSpringContextTests {

	protected ClusterSQL clusterSql;
	protected DataSource dataSource;
	
	protected String[] getConfigLocations() {
		return new String[] {"spring-test-hsql.xml"};
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		clusterSql = (ClusterSQL)applicationContext.getBean("clusterSQL");
		dataSource = (DataSource)applicationContext.getBean("testDataSource");
	}

	public void testFoo() throws Exception {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String sql = clusterSql.returnSelectServerIdFromCluster();
		List list = jdbc.queryForList(sql);
		
		Assert.assertTrue(true);
	}

}
