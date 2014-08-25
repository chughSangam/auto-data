/**
 * Copyright © 2014 Insonix

 * Permission is hereby granted, free of charge, to any person obtaining a copy of these 
 * Experiments and associated documentation files (the Software), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sub-license, and/or sell copies of the Software, and to permit persons 
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED AS IS, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
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
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author gagan
 * @year 2014
 * @version 1.0
 * @since 1.0
 */
public class OperationOnDataBase {
	private static Map<Integer, String> map = new HashMap<Integer, String>();
	static Map<String, Map<String, String>> columnsDetailMap = null;
	static Map<String, String> internalColumnMap = null;
	private static int i = 0;
	private static String tableName;
	private static Scanner sc = new Scanner(System.in);
	private static Scanner scanner;
	private static int randomNumber=0;
	private static String randomString=null;
	private static Connection connection=null;
	private static Statement statement=null;
	private static int UniqueRecordsAdded=0;
	private static int[] list=null;
	private static int counter=0;

	/**
	 * This method get the Table Names from specified database
	 * 
	 * @author gagan
	 * @since 1.0
	 * @version 1.0
	 */
	public static void getTables() {
		try {
			DBUtil.loadDriver(SupportedDatabases.MYSQL);
			try {
				Connection con = DBUtil.getConnection();
				DatabaseMetaData dbmd = con.getMetaData();
				String[] types = { "TABLE" };
				ResultSet rs = dbmd.getTables(null, null, "%", types);
				System.out.println("*************List of table's************");
				while (rs.next()) {
					i++;
					String tableName = rs.getString("TABLE_NAME");
					map.put(i, tableName);
					System.out.println("Table " + i + "  Name of table is "
							+ tableName);
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
	 * This method provide information about columns of table
	 */
	public static void getTableInformation() {
		columnsDetailMap = new HashMap<String, Map<String, String>>();
		System.out
				.println("Enter table number if you get information about the table");
		int key = sc.nextInt();
		Set<Map.Entry<Integer, String>> set = map.entrySet();
		Iterator<Entry<Integer, String>> itr = set.iterator();
		while (itr.hasNext()) {
			Entry<Integer, String> entry = itr.next();
			if (entry.getKey() == key) {
				tableName = entry.getValue();
			}
		}
		try {
			DBUtil.loadDriver(SupportedDatabases.MYSQL);
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("Select * from "
					+ tableName);
			ResultSet resultSet = ps.executeQuery();
			ResultSetMetaData metadata = resultSet.getMetaData();
			int columnCount = metadata.getColumnCount();

			for (int j = 1; j <= columnCount; j++) {
				internalColumnMap = new HashMap<String, String>();
				if (!metadata.isAutoIncrement(j)) {
					internalColumnMap.put("columnName",
							metadata.getColumnName(j));
					internalColumnMap.put("dataType",
							metadata.getColumnTypeName(j));
					internalColumnMap.put("size",
							String.valueOf(metadata.getColumnDisplaySize(j)));
					internalColumnMap.put("isNUllable",
							String.valueOf(metadata.isNullable(j) == 0));
					System.out.println("Column " + j + " :: Column Name "
							+ metadata.getColumnName(j) + " Data Type "
							+ metadata.getColumnTypeName(j) + " size "
							+ metadata.getColumnDisplaySize(j)
							+ " is mandatory " + (metadata.isNullable(j) == 0));
					columnsDetailMap.put(metadata.getColumnName(j),
							internalColumnMap);
				}
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

	public static int insertRecords(int randomNum,String randomString) {
		try{
		statement=connection.createStatement();
		int added=statement.executeUpdate("Insert into "+tableName+" values(null,"+randomNum+",'"+randomString+"')");
		UniqueRecordsAdded++;
		return added;
		
		}
		catch(Exception e)
		{
			return 0;
		}
			
	}

	/**
	 * This method will ask the user for the details required to run the auto
	 * data generator.
	 * @throws Exception 
	 */
	public static void saveAutoGeneratedNumbers() throws Exception
	{
		scanner = new Scanner(System.in);
		int  minVal = 0,maxVal=0,minlength=0,maxlength=0;
		boolean isAlphanumeric=false,iswithSpecialChar = false;
		System.out.println("\n Lets Enter the Values");
		for(String string:columnsDetailMap.keySet())
		{
			if(columnsDetailMap.get(string).get("dataType").equalsIgnoreCase("INT"))
			{
				minVal=0;
				maxVal=0;
				System.out.println("Please Enter the below values for "+string+". Value generated will be of integer type");
				while(minVal>maxVal || minVal<0 || maxVal<=0)
				{
				System.out.println("Enter the minimum value: ");
				minVal=scanner.nextInt();
				System.out.println("Enter the maximum value: ");
				maxVal=scanner.nextInt();
				}
			}
			else if(columnsDetailMap.get(string).get("dataType").equalsIgnoreCase("VARCHAR"))
			{
				minlength=0;
				maxlength=0;
				isAlphanumeric=false;
				iswithSpecialChar=false;
				System.out.println("Please Enter the below values for "+string+". Value generated will be of String type. Max value ="+columnsDetailMap.get(string).get("size"));
				while(minlength>maxlength || minlength<0 || maxlength<=0 || maxlength>Integer.parseInt(columnsDetailMap.get(string).get("size")))
				{
				System.out.println("Enter the minimum length of string: ");
				minlength=scanner.nextInt();
				System.out.println("Enter the maximum length of string: ");
				maxlength=scanner.nextInt();
				}
				System.out.println("Do you want an alphanumeric String?(Y/N)");
				String alphaNumeric=scanner.next();
				isAlphanumeric=("Y").equalsIgnoreCase(alphaNumeric);
				System.out.println("Do you want an special characters in String?(Y/N)");
				String splchar=scanner.next();
				iswithSpecialChar=("Y").equalsIgnoreCase(splchar);	
			}
		}
		connection=DBUtil.getConnection();
		System.out.println("Enter the total no records to save?");
		int totalRecords=scanner.nextInt();
		UniqueRecordsAdded=0;
		long lStartTime = new Date().getTime();
		list= new int[totalRecords];
		for(int i=1;i<=totalRecords;i++)
		{
		randomNumber=Toolbox.generateNumber(minVal, maxVal);
		while(check(randomNumber))
		{
			randomNumber=Toolbox.generateNumber(minVal, maxVal);
		}
		list[counter]=randomNumber;
		counter++;
		randomString=Toolbox.generateRandomString(minlength, maxlength, isAlphanumeric, iswithSpecialChar);
		insertRecords(randomNumber, randomString);
		}
		long lEndTime = new Date().getTime();
		   long difference = lEndTime - lStartTime; // check different
		   long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
		   long seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
		     - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
		       .toMinutes(difference));
		   System.out.println("Total Time in milli:"+difference);
		   long milliseconds=difference-TimeUnit.MINUTES.toMillis(minutes)-TimeUnit.SECONDS.toMillis(seconds);
		   System.out.println("Total number of unique records added are : "+UniqueRecordsAdded);
		   System.out.println("Elapsed minutes: " + minutes
		     + " , Elapsed seconds: " + seconds+" and Elapsed milliseconds: "+milliseconds);
		
	}
	
	/**
	 * This method checks whether the int x is a unique value or a previous generated value 
	 * @param x
	 * @return
	 */
	public static boolean check(int x)
    {
        boolean temp = false;
        for(int n=0;n<=counter;n++)
        {
            if(list[n]==x)
            {
                temp = true;
                break;
            }
            else
            {
                temp = false;
            }
        }
        return temp;
    }
}