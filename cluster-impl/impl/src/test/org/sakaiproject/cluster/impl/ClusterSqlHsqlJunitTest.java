package org.sakaiproject.cluster.impl;

import org.sakaiproject.cluster.api.ClusterSql;

public class ClusterSqlHsqlJunitTest extends ClusterSqlGenericJunitAbstract {

//	ClusterSqlHsql cs = null;
	String oldServerIdSqlHsql = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and DATEDIFF('ss', UPDATE_TIME, CURRENT_TIMESTAMP) >= ";

	protected void setUp() throws Exception 
	{
		super.setUp();
		cs = (ClusterSql) new ClusterSqlHsql();
	}

	@Override
	public void testReturnOldServerId() {

		assertEquals(oldServerIdSqlHsql+"0",cs.returnOldServerId(0));

	}

	public void testReturnOldServerId1() {

		assertEquals(oldServerIdSqlHsql+"1",cs.returnOldServerId(1));

	}
	
	public void testReturnOldServerId2() {

		assertEquals(oldServerIdSqlHsql+"2",cs.returnOldServerId((long) 2));

	}
	
	
	public void testReturnOldServerId999() {

		assertEquals(oldServerIdSqlHsql+"999",cs.returnOldServerId((long) 999));

	}

	public void testReturnUpdateSessionEnd() {
		String statement = "update SAKAI_SESSION set SESSION_END = " + "CURRENT_TIMESTAMP"
		+ " where SESSION_ID = ?";
		assertEquals(statement,cs.returnUpdateSessionEnd());
	}

	public void testReturnInsertIdUpdateTime() {
		String statement = "insert into SAKAI_CLUSTER (SERVER_ID,UPDATE_TIME) values (?, "
				+ "CURRENT_TIMESTAMP" + ")";
		assertEquals(statement, cs.returnInsertIdUpdateTime());
	}

	public void testReturnUpdateTime() {
		String statement = "update SAKAI_CLUSTER set UPDATE_TIME = " + "CURRENT_TIMESTAMP"
		+ " where SERVER_ID = ?";
		assertEquals(statement,cs.returnUpdateTime());
	}
	
	


}
