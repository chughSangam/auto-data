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
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
/**
 * 
 * @author gagan
 * @year 2014
 * @version 1.0
 * @since 1.0  
 */
public class OperationOnDataBase {
	private static Map<Integer,String> map=new HashMap<Integer,String>();
	private static int i=0;  
	private static String tableName;
	private static Scanner sc=new Scanner(System.in);
	
	/**
	 * This method get the Table Names from specified database
	 * @author gagan
	 * @since 1.0
	 * @version 1.0
	 */
	public static void getTables(){
		try {
			DBUtil.loadDriver(SupportedDatabases.MYSQL);
			try {
				Connection con=DBUtil.getConnection();
				DatabaseMetaData dbmd = con.getMetaData();
	            String[] types = {"TABLE"};
	            ResultSet rs = dbmd.getTables(null, null, "%", types);
	            System.out.println("*************List of table's************");
	            while (rs.next()) {
	            	i++;
	            	String tableName=rs.getString("TABLE_NAME");
	            	map.put(i, tableName);
	            	System.out.println("Table "+i+"  Name of table is "+tableName);
	                }
	             
			     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | IllegalAccessException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * This method provide information about  table  
	 */
	public static void getTableInformation(){
		System.out.println("Enter table number if you get information about the table");
		int key=sc.nextInt();
		Set<Map.Entry<Integer,String>> set=map.entrySet(); 
	    Iterator<Entry<Integer,String>> itr=set.iterator();
	    while(itr.hasNext()){
			Entry<Integer,String> entry=itr.next();
			if(entry.getKey()==key){
				tableName=entry.getValue();
			}
	    }	
		try {
			DBUtil.loadDriver(SupportedDatabases.MYSQL);
			Connection con=DBUtil.getConnection();
					PreparedStatement ps=con.prepareStatement("Select * from "+tableName);
			ResultSet resultSet=ps.executeQuery();
			ResultSetMetaData metadata=resultSet.getMetaData();
		    int columnCount = metadata.getColumnCount();
		    
		    for (int j=1; j <=columnCount; j++) {
		    	
			      String columnName = metadata.getColumnName(j);
			      String dataType=metadata.getColumnTypeName(j);
			      int size=metadata.getColumnDisplaySize(j);
			      System.out.println("Column "+j+" :: Column Name "+columnName+" Data Type "+dataType+" size "+size);
			      
			    }
				
	
		} catch (ClassNotFoundException | IllegalAccessException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
