package org.sakaiproject.cluster.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ClusterSQLTestSuite extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite();
//		suite.addTestSuite(ClusterSQLOracleTest.class);
//		suite.addTestSuite(ClusterSQLMysqlTest.class);
//		suite.addTestSuite(ClusterSQLHsqlTest.class);
		return suite;
	}
}
