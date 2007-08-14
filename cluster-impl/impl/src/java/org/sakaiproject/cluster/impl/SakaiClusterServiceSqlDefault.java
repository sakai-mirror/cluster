/**********************************************************************************
 * $URL: https://source.sakaiproject.org/contrib/rsmart/dbrefactor/cluster/cluster-impl/impl/src/java/org/sakaiproject/cluster/impl/SakaiClusterServiceSqlDefault.java $
 * $Id: SakaiClusterServiceSqlDefault.java 3560 2007-02-19 22:08:01Z jbush@rsmart.com $
 ***********************************************************************************
 *
 * Copyright (c) 2007 The Sakai Foundation.
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

/**
 * methods for accessing cluster data in a database.
 */
public class SakaiClusterServiceSqlDefault implements ClusterServiceSql
{
	/**
	 * returns the sql statement for deleting locks for a given session from the sakai_locks table.
	 */
	public String getDeleteLocksSql()
	{
		return "delete from SAKAI_LOCKS where USAGE_SESSION_ID = ?";
	}

	/**
	 * returns the sql statement for deleting a server from the sakai_cluster table.
	 */
	public String getDeleteServerSql()
	{
		return "delete from SAKAI_CLUSTER where SERVER_ID = ?";
	}

	/**
	 * returns the sql statement for inserting a server id into the sakai_cluster table.
	 */
	public String getInsertServerSql()
	{
		return "insert into SAKAI_CLUSTER (SERVER_ID,UPDATE_TIME) values (?, " + sqlTimestamp() + ")";
	}

	/**
    * returns the sql statement for obtaining a list of expired sakai servers from the sakai_cluster table.
    * <br/>br/>
    * @param timeout  how long (in seconds) we give an app server to respond before it is considered lost.
	 */
	public String getListExpiredServers(long timeout)
	{
		return "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and DATEDIFF('ss', UPDATE_TIME, CURRENT_TIMESTAMP) >= " + timeout;
	}

	/**
	 * returns the sql statement for obtaining a list of sakai servers from the sakai_cluster table in server_id order.
	 */
	public String getListServersSql()
	{
		return "select SERVER_ID from SAKAI_CLUSTER order by SERVER_ID asc";
	}

	/**
	 * find all the session ids of sessions that are open but are from closed servers.
	 */
	public String getListOpenSessionsFromClosedServersSql()
	{
		return "select SS.SESSION_ID from SAKAI_SESSION SS left join SAKAI_CLUSTER SC on SS.SESSION_SERVER = SC.SERVER_ID "
				+ "where SS.SESSION_START = SS.SESSION_END and SC.SERVER_ID is null";
	}

	/**
	 * returns the sql statement for retrieving a particular server from the sakai_cluster table.
	 */
	public String getReadServerSql()
	{
		return "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID = ?";
	}

	/**
	 * returns the sql statement for updating a server in the sakai_cluster table.
	 */
	public String getUpdateServerSql()
	{
		return "update SAKAI_CLUSTER set UPDATE_TIME = " + sqlTimestamp() + " where SERVER_ID = ?";
	}

	/**
	 * returns the sql statement for updating a sakai session in the sakai_session table.
	 */
	public String getUpdateSakaiSessionSql()
	{
		return "update SAKAI_SESSION set SESSION_END = " + sqlTimestamp() + " where SESSION_ID = ?";
	}

	/**
	 * returns the current timestamp.
	 */
	public String sqlTimestamp()
	{
		return "CURRENT_TIMESTAMP";
	}
}
