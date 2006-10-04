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

class  ClusterSQL {

	// SQL specific functions are below.

	/**
	 * Return the vendor-specific SQL for the current timestamp
	 * 
	 * @param vendor
	 *            TODO
	 */
	
	// This SQL varies by vendor.
	// The variations are the format of CURRENT_TIMESTAMP and the format
	// of the value of time subtracted from CURRENT_TIMESTAMP.
	
	////////////////////////////////////////////////////////////////////////
	// Timestamp varies by vendor and some of these depend on the timestamp.
	
	static String sqlTimestamp(String vendor) {
		if ("mysql".equals(vendor)) {
			return "CURRENT_TIMESTAMP()";
		} else
		// oracle, hsqldb
		{
			return "CURRENT_TIMESTAMP";
		}
	}
	
	static String returnInsertIdUpdateTime(String vendor) {
		String statement = "insert into SAKAI_CLUSTER (SERVER_ID,UPDATE_TIME) values (?, "
				+ sqlTimestamp(vendor) + ")";
		return statement;
	}

	static String returnUpdateTime(String vendor) {
		String statement;
		statement = "update SAKAI_CLUSTER set UPDATE_TIME = "
				+ sqlTimestamp(vendor) + " where SERVER_ID = ?";
		return statement;
	}

	static String returnUpdateSessionEnd(String vendor) {
		String statement;
		statement = "update SAKAI_SESSION set SESSION_END = "
				+ sqlTimestamp(vendor) + " where SESSION_ID = ?";
		return statement;
	}
	
	
	///////////////////////////////////////////////////////////////////
	/// These vary in the format of the value of 'expired'
	static String returnGenericOldServerId(String vendor, long expired) {
		String statement;
		if ("oracle".equals(vendor)) {
			statement = returnOldServerIdOracle(expired);
		} else if ("mysql".equals(vendor)) {
			statement = returnOldServerIdMysql(expired);
		} else
		// if ("hsqldb".equals(m_sqlService.getVendor()))
		{
			statement = returnOldServerId(expired);
		}
		return statement;
	}
	
	static String returnOldServerIdOracle(long expired) {
		String statement;
		statement = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and UPDATE_TIME < (CURRENT_TIMESTAMP - "
				+ ((float) expired / (float) (60 * 60 * 24)) + " )";
		return statement;
	}

	static String returnOldServerIdMysql(long expired) {
		String statement;
		statement = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and UPDATE_TIME < CURRENT_TIMESTAMP() - INTERVAL "
				+ expired + " SECOND";
		return statement;
	}

	static String returnOldServerId(long expired) {
		String statement;
		statement = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and DATEDIFF('ss', UPDATE_TIME, CURRENT_TIMESTAMP) >= "
				+ expired;
		return statement;
	}

	/////////////////////////////////////////////////////////////////
	//////// The following SQL is vendor independent ////////////////
	
	static String returnSelectServerIdFromCluster() {
		String statement = "select SERVER_ID from SAKAI_CLUSTER order by SERVER_ID asc";
		return statement;
	}

	static String returnDeleteId() {
		String statement = "delete from SAKAI_CLUSTER where SERVER_ID = ?";
		return statement;
	}


	static String returnServerIdFromCluster() {
		String statement = "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID = ?";
		return statement;
	}

	static String returnSelectOpenSessionsClosedServers() {
		String statement;
		statement = "select SS.SESSION_ID "
				+ "from SAKAI_SESSION SS "
				+ "left join SAKAI_CLUSTER SC on SS.SESSION_SERVER = SC.SERVER_ID "
				+ "where SS.SESSION_START = SS.SESSION_END "
				+ "and SC.SERVER_ID is null";
		return statement;
	}

	static String returnSelectLocationFromPresenceBySessionSQL() {
		String statement;
		statement = "select LOCATION_ID from SAKAI_PRESENCE where SESSION_ID = ?";
		return statement;
	}

	static String returnDeletePresenceBySession() {
		String statement;
		statement = "delete from SAKAI_PRESENCE where SESSION_ID = ?";
		return statement;
	}

	static String returnDeleteSessionLock() {
		String statement;
		statement = "delete from SAKAI_LOCKS where USAGE_SESSION_ID = ?";
		return statement;
	}


}