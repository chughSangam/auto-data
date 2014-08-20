package com.insonix.ad.utils;



import java.sql.Connection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtilTest {
	public static final Logger logger = LoggerFactory
			.getLogger(DBUtilTest.class);

	@Test
	public void testLoadDriver() throws Exception {
		DBUtil.loadDriver(SupportedDatabases.MYSQL);
		
	}
	@Test
	public void testConnection() throws Exception{
		
		Connection con=DBUtil.getConnection();
		System.out.println(con);
		
		
	}
	
}
