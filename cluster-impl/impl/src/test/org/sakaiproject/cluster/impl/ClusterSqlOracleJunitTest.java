package org.sakaiproject.cluster.impl;

import org.sakaiproject.cluster.api.ClusterSql;

public class ClusterSqlOracleJunitTest extends ClusterSqlGenericJunitAbstract {

//	ClusterSqlHsql cs = null;
	String oldServerIdSqlMysqlPrefix = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and UPDATE_TIME < CURRENT_TIMESTAMP() - INTERVAL ";
	String oldServerIdSqlMysqlSuffix = " SECOND";
	protected void setUp() throws Exception 
	{
		super.setUp();
		cs = (ClusterSql) new ClusterSqlMysql();
	}

	@Override
	public void testReturnOldServerId() {

		assertEquals(oldServerIdSqlMysqlPrefix+"0"+oldServerIdSqlMysqlSuffix,cs.returnOldServerId(0));

	}

	public void testReturnOldServerId1() {

		assertEquals(oldServerIdSqlMysqlPrefix+"1"+oldServerIdSqlMysqlSuffix,cs.returnOldServerId(1));

	}
	
	public void testReturnOldServerId2() {

		assertEquals(oldServerIdSqlMysqlPrefix+"2"+oldServerIdSqlMysqlSuffix,cs.returnOldServerId((long) 2));

	}
	
	
	public void testReturnOldServerId999() {

		assertEquals(oldServerIdSqlMysqlPrefix+"999"+oldServerIdSqlMysqlSuffix,cs.returnOldServerId((long) 999));

	}

	public void testReturnUpdateSessionEnd() {
		String statement = "update SAKAI_SESSION set SESSION_END = " + "CURRENT_TIMESTAMP()"
		+ " where SESSION_ID = ?";
		assertEquals(statement,cs.returnUpdateSessionEnd());
	}

	public void testReturnInsertIdUpdateTime() {
		String statement = "insert into SAKAI_CLUSTER (SERVER_ID,UPDATE_TIME) values (?, "
				+ "CURRENT_TIMESTAMP()" + ")";
		assertEquals(statement, cs.returnInsertIdUpdateTime());
	}

	public void testReturnUpdateTime() {
		String statement = "update SAKAI_CLUSTER set UPDATE_TIME = " + "CURRENT_TIMESTAMP()"
		+ " where SERVER_ID = ?";
		assertEquals(statement,cs.returnUpdateTime());
	}
	
	


}
