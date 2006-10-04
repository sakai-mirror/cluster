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
package org.sakaiproject.cluster.api;

/**
 * Provides the vendor-specific SQL needed by the ClusterService.
 */
public interface ClusterSql {

	public String returnInsertIdUpdateTime();

	public String returnUpdateTime();

	public String returnUpdateSessionEnd();

	public String returnGenericOldServerId(long expired);

	public String returnSelectServerIdFromCluster();

	public String returnDeleteId();

	public String returnServerIdFromCluster();

	public String returnSelectOpenSessionsClosedServers();

	public String returnSelectLocationFromPresenceBySessionSql();

	public String returnDeletePresenceBySession();

	public String returnDeleteSessionLock();
}