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

import java.util.Random;

/**
 * This class works as a helper class and provides common utilities.
 * 
 * @author sunnydyal
 * @date 14-Aug-2014 12:12:55 pm
 * @year 2014
 * @version 1.0
 * @since 1.0
 */
public class Toolbox {
	/**
	 * Instance of this class is not required because all methods are static
	 */
	private Toolbox() {

	}
	/**
	 * This method generates random number based on minimum and maximum value.
	 * @param max
	 * @return
	 */
	public static int generateNumber(int max) throws Exception {
		Random random = new Random();
		if(max<=1) throw new Exception("max value must be greater then 1");
		int randomNumber = random.nextInt(max);
		while(randomNumber==0) {
			randomNumber = random.nextInt(max);
		}
		return randomNumber;
	}
	/**
	 * This method generates random number  based on
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public static int generateNumber(int min, int max) throws Exception {
		
		if(min>max) throw new Exception("max value must be greater than min value");
		if(min<=0) throw new Exception("min value must be greater than 0");
		Random random= new Random();
		int randomNumber=random.nextInt(max);
		while(randomNumber<=min)
		{
			randomNumber=random.nextInt(max);
		}
		return randomNumber;
	}
	/**
	 * This method generates random number based on minimum , maximum value and seed(starting) value. 
	 * @param min
	 * @param max
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static int generateNumber(int min, int max, int seed) throws Exception {
		if(min>max)  throw new Exception("max value must be greater than min value");
		else if(min>seed)  throw new Exception("seed value must be greater than min value");
		else if(max<seed)  throw new Exception("seed value must be smaller than max value");
		Random random= new Random(seed);
		int randomNumber=random.nextInt(max);
		while(randomNumber<=seed)
		{
			randomNumber=random.nextInt(max);
		}
		return randomNumber;
	}
	/**
	 * This method returns a String value based on the below parameters
	 * @param length
	 * @param alphaNumeric
	 * @param withSpecialChars
	 * @return
	 * @throws Exception
	 */
	public static String generateRandomString(int min,int max, boolean alphaNumeric, boolean withSpecialChars) throws Exception {
		StringBuilder common = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		if(min>max) throw new Exception("max value must be greater than min value");
		if(min<=0) throw new Exception("min value must be greater than 0");
		int length=Toolbox.generateNumber(min, max);
		if(alphaNumeric) common.append("0123456789");
		if(withSpecialChars) common.append("`!@#$%^&*()=");
		//if(length<3) throw new Exception("Length must be greater than 3");
		StringBuilder outputString= new StringBuilder();
		int i=1;
		Random random= new Random();
		while(i<=length)
		{
	    outputString.append(common.charAt(random.nextInt(common.length()-1)));
		i++;
		}
		return outputString.toString();
	}
}
