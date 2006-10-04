package org.sakaiproject.cluster.impl;

import junit.framework.Assert;

import org.springframework.test.AbstractTransactionalSpringContextTests;

public class ClusterSQLMysqlTest extends AbstractTransactionalSpringContextTests {

	protected String[] getConfigLocations() {
		return new String[] {"spring-test.xml"};
	}

	public void testFoo() throws Exception {
		Assert.assertTrue(true);
	}
}
