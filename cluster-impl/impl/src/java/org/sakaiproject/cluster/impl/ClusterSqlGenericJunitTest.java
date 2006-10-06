/**********************************************************************************
 * $HeadURL$
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

// Only test methods that do something besides return constant string.


package org.sakaiproject.cluster.impl;

import junit.framework.TestCase;

import org.sakaiproject.cluster.api.ClusterSql;

public class ClusterSqlGenericJunitTest extends TestCase {

	ClusterSql cs = null;

	protected void setUp() {
		cs = new ClusterSqlGeneric();
	}

	public void testReturnGenericOldServerId() {
		// Must be overridden, so test must be overridden also.
		fail();
	}
	
	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.sqlTimestamp()'
	 */
	public void testSqlTimestamp() {
		assertEquals("CURRENT_TIMESTAMP", cs.sqlTimestamp());
	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnInsertIdUpdateTime()'
	 */
	public void testReturnInsertIdUpdateTime() {
		String statement = "insert into SAKAI_CLUSTER (SERVER_ID,UPDATE_TIME) values (?, "
				+ "CURRENT_TIMESTAMP" + ")";
		assertEquals(statement, cs.returnInsertIdUpdateTime());
	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnUpdateTime()'
	 */
	public void testReturnUpdateTime() {
		String statement = "update SAKAI_CLUSTER set UPDATE_TIME = " + "CURRENT_TIMESTAMP"
		+ " where SERVER_ID = ?";
		assertEquals(statement,cs.returnUpdateTime());
	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnUpdateSessionEnd()'
	 */
	public void testReturnUpdateSessionEnd() {
		String statement = "update SAKAI_SESSION set SESSION_END = " + "CURRENT_TIMESTAMP"
		+ " where SESSION_ID = ?";
		assertEquals(statement,cs.returnUpdateSessionEnd());
	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnSelectServerIdFromCluster()'
	 */
//	public void testReturnSelectServerIdFromCluster() {
//		fail();
//	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnDeleteId()'
	 */
	// No test if just returns a constant string.
	// public void testReturnDeleteId() {
	// fail();
	// }
	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnServerIdFromCluster()'
	 */
//	public void testReturnServerIdFromCluster() {
//		fail();
//	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnSelectOpenSessionsClosedServers()'
	 */
//	public void testReturnSelectOpenSessionsClosedServers() {
//		fail();
//	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnSelectLocationFromPresenceBySessionSql()'
	 */
//	public void testReturnSelectLocationFromPresenceBySessionSql() {
//		fail();
//	}

	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnDeletePresenceBySession()'
	 */
	// public void testReturnDeletePresenceBySession() {
	// fail();
	// }
	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnDeleteSessionLock()'
	 */
	// public void testReturnDeleteSessionLock() {
	// fail();
	// }
	/*
	 * Test method for
	 * 'org.sakaiproject.cluster.impl.ClusterSqlGeneric.returnGenericOldServerId(long)'
	 */

}
