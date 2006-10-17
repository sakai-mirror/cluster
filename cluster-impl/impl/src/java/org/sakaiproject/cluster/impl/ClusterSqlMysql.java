/**********************************************************************************
 * $HeadURL:$
 * $Id:$
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

class  ClusterSqlMysql extends ClusterSqlGeneric {

	public String sqlTimestamp() {
		return "CURRENT_TIMESTAMP()";
	}

	public String returnOldServerId(long expired) {
		return "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and UPDATE_TIME < "
		+sqlTimestamp()
		+" - INTERVAL "
		+ expired + " SECOND";
	}
	
//	public String returnOldServerId(long expired) {
//		return "select SERVER_ID from SAKAI_CLUSTER where SERVER_ID != ? and UPDATE_TIME < CURRENT_TIMESTAMP() - INTERVAL "
//		+ expired + " SECOND";
//	}
}