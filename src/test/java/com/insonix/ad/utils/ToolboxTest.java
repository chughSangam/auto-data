package com.insonix.ad.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolboxTest {
	public static final Logger logger = LoggerFactory.getLogger(ToolboxTest.class);
	
	/**
	 * This method verify the working of generateNumber() of class ToolBox
	 */
    @Test
    public void testGenerateNumber() {
        for(int i=0;i<10;i++) {
        	try {
        		logger.trace("Number : " + Toolbox.generateNumber(1513));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
        }
    }
    
    /**
     * This method verify the working of generateNumber(int min,int max) of class ToolBox
     */
    @Test 
    public void testGenerateNumberWithMinMax()
    {
    	for(int i=0;i<10;i++) {
    	try{
    		logger.info(""+Toolbox.generateNumber(45,150));
    	}
    	catch(Exception e)
    	{
    		logger.error(e.getMessage());
    	}
    	}
    }
    
    /**
     * This method verify the working of generateNumber(int min,int max,int seed) of class ToolBox
     * @throws Exception 
     */
    @Test
    public void testGenerateNumberWithSeed() throws Exception
    {
    	for(int i=0;i<10;i++)
    	{
    		int number=Toolbox.generateNumber(0, 450);
    		try {
				logger.info("Seed Value: "+Toolbox.generateNumber(0, 450, number));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    /**
     *This method verify the working of generateRandomString(int length,boolean alphaNumeric,boolean withSpecialCharacters) 
     *of class ToolBox 
     */
    @Test
    public void testStringGenerator()
    {
    	try {
    		for(int i=0;i<10;i++ )
    		{
			logger.info("String generated containing only Alphabets ; "+ Toolbox.generateRandomString(40, false, false));
			logger.info("String generated with Alphanumerics : " +Toolbox.generateRandomString(45, true, false));
			logger.info("String genarated with SpecialCharacters : "+Toolbox.generateRandomString(45, true,true));
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
