/**********************************************************************************
 * $URL: https://source.sakaiproject.org/contrib/rsmart/dbrefactor/cluster/cluster-api/api/src/java/org/sakaiproject/cluster/api/ClusterServiceSql.java $
 * $Id: ClusterServiceSql.java 3560 2007-02-19 22:08:01Z jbush@rsmart.com $
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
package org.sakaiproject.cluster.api;




/**
 * database methods.
 */
public interface ClusterServiceSql {

   /**
    * returns the sql statement for deleting locks for a given session from the sakai_locks table.
    */
   public String getDeleteLocksSql();

   /**
    * returns the sql statement for retrieving a sakai presence from the sakai_presence table.
    */
   public String getDeletePresenceSql();

   /**
    * returns the sql statement for deleting a server from the sakai_cluster table.
    */
   public String getDeleteServerSql();

   /**
    * returns the sql statement for inserting a server id into the sakai_cluster table.
    */
   public String getInsertServerSql();

   /**
    * returns the sql statement for obtaining a list of expired sakai servers from the sakai_cluster table.
    * <br/>br/>
    * @param timeout   how long (in seconds) we give an app server to respond before it is considered lost.
    */
   public String getListExpiredServers(long timeout);

   /**
    * returns the sql statement for obtaining a list of sakai servers from the sakai_cluster table in server_id order.
    */
   public String getListServersSql();

   /**
    * find all the session ids of sessions that are open but are from closed servers.
    */
   public String getListOpenSessionsFromClosedServersSql();

   /**
    * returns the sql statement for retrieving a sakai presence from the sakai_presence table.
    */
   public String getPresenceSql();

   /**
    * returns the sql statement for retrieving a particular server from the sakai_cluster table.
    */
   public String getReadServerSql();

   /**
    * returns the sql statement for updating a server in the sakai_cluster table.
    */
   public String getUpdateServerSql();

   /**
    * returns the sql statement for updating a sakai session in the sakai_session table.
    */
   public String getUpdateSakaiSessionSql();

   /**
    * returns the current timestamp.
    */
   public String sqlTimestamp();


}
