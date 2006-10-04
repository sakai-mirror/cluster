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

import org.sakaiproject.cluster.api.ClusterSQL;

class  ClusterSQLHsql implements ClusterSQL {
	
	protected String sqlTimestamp() {
		return "CURRENT_TIMESTAMP";
	}
	
	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnInsertIdUpdateTime(java.lang.String)
	 */
	public String returnInsertIdUpdateTime() {
		String statement = "insert into SAKAI_CLUSTER (SERVER_ID,UPDATE_TIME) values (?, "
				+ sqlTimestamp() + ")";
		return statement;
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnUpdateTime(java.lang.String)
	 */
	public String returnUpdateTime() {
		String statement;
		statement = "update SAKAI_CLUSTER set UPDATE_TIME = "
				+ sqlTimestamp() + " where SERVER_ID = ?";
		return statement;
	}

	public String returnUpdateSessionEnd() {
		String statement;
		statement = "update SAKAI_SESSION set SESSION_END = "
				+ sqlTimestamp() + " where SESSION_ID = ?";
		return statement;
	}
	
	public String returnGenericOldServerId(long expired) {
		return "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and DATEDIFF('ss', UPDATE_TIME, CURRENT_TIMESTAMP) >= " + expired;
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnSelectServerIdFromCluster()
	 */
	public String returnSelectServerIdFromCluster() {
		return "select SERVER_ID from SAKAI_CLUSTER order by SERVER_ID asc";
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnDeleteId()
	 */
	public String returnDeleteId() {
		return "delete from SAKAI_CLUSTER where SERVER_ID = ?";
	}


	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnServerIdFromCluster()
	 */
	public String returnServerIdFromCluster() {
		String statement = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID = ?";
		return statement;
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnSelectOpenSessionsClosedServers()
	 */
	public String returnSelectOpenSessionsClosedServers() {
		String statement;
		statement = "select SS.SESSION_ID "
				+ "from SAKAI_SESSION SS "
				+ "left join SAKAI_CLUSTER SC on SS.SESSION_SERVER = SC.SERVER_ID "
				+ "where SS.SESSION_START = SS.SESSION_END "
				+ "and SC.SERVER_ID is null";
		return statement;
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnSelectLocationFromPresenceBySessionSQL()
	 */
	public String returnSelectLocationFromPresenceBySessionSQL() {
		return "select LOCATION_ID from SAKAI_PRESENCE where SESSION_ID = ?";
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnDeletePresenceBySession()
	 */
	public String returnDeletePresenceBySession() {
		return "delete from SAKAI_PRESENCE where SESSION_ID = ?";
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.cluster.impl.ClusterSQL#returnDeleteSessionLock()
	 */
	public String returnDeleteSessionLock() {
		return "delete from SAKAI_LOCKS where USAGE_SESSION_ID = ?";
	}

}