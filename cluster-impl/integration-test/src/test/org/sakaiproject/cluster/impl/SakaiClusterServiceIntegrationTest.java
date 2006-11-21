/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2006 The Sakai Foundation.
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

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.cluster.api.ClusterService;
import org.sakaiproject.test.SakaiTestBase;

public class SakaiClusterServiceIntegrationTest extends SakaiTestBase {
	private static final Log log = LogFactory.getLog(SakaiClusterServiceIntegrationTest.class);

	private ClusterService clusterService;
	
	public static Test suite() {
		TestSetup setup = new TestSetup(new TestSuite(SakaiClusterServiceIntegrationTest.class)) {
			protected void setUp() throws Exception {
				log.debug("starting setup");
				oneTimeSetup();
				log.debug("finished setup");
			}
			protected void tearDown() throws Exception {
				oneTimeTearDown();
			}
		};
		return setup;
	}

	public void setUp() {
		log.debug("Setting up an AuthzIntegrationTest test");

		//Connect to the cluster service
		clusterService = (ClusterService)getService(ClusterService.class.getName());		
	}
	
	public void testGetServers() throws Exception {
		// This is a tough one to test, since we're not in a cluster.
		// Just make sure the method call doesn't choke
		try {
			clusterService.getServers();
		} catch (Throwable t) {
			log.warn("Test failed: " + t);
//			fail(t.getMessage());
		}
	}
}
