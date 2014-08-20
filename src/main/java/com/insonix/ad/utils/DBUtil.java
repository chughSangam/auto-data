/**
 * Copyright Â© 2014 Insonix

 * Permission is hereby granted, free of charge, to any person obtaining a copy of these 
 * Experiments and associated documentation files (the â€œSoftwareâ€�), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sub-license, and/or sell copies of the Software, and to permit persons 
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED â€œAS ISâ€�, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.insonix.ad.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Utility for database operations
 * 
 * @author sunnydyal
 * @date 14-Aug-2014 12:54:37 pm
 * @year 2014
 * @version 1.0
 * @since 1.0
 */
public final class DBUtil {
	/**
	 * This method provide connection
	 * @param url
	 * @param uName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static String url="jdbc:mysql://localhost:3306/insonix";
	private static String userName="root";
	private static String password="12345";

	public static Connection getConnection() throws Exception {
	Connection con=DriverManager.getConnection(url, userName,password);
		return con;
	
	}
	/**
	 * Function loads a driver class
	 * @param supportedDatabases
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void loadDriver(SupportedDatabases supportedDatabases)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		String driverName = "";
		switch (supportedDatabases) {
			case MYSQL :
				driverName = "com.mysql.jdbc.Driver";
				break;
			case POSTGRESQL :
				driverName = "org.postgresql.Driver";
				break;
			case HSQLDB :
				driverName = "org.hsqldb.jdbc.JDBCDriver";
				break;
		}
		Class.forName(driverName).newInstance();
	}
}
