/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2004, 2005, 2006 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/
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
