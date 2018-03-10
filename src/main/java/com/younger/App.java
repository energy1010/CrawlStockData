package com.younger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
	
	/**
	 * 读取resources目录下面的res.txt文件
	 * @return
	 * @throws Exception
	 */
	private Properties loadUser() throws Exception{
		Properties userList = null;
		if(userList == null){

			InputStream in = this.getClass().getResourceAsStream("/userFile.properties");
			if(in == null)
				throw new Exception("Can not find user file");
			else{
				userList = new Properties();
				userList.load(in);
			}
			
		}
		return userList;
	}
	
    public static void main( String[] args )
    {
    	
//    	xxx.class.getClassLoader().getResourceAsInputStream("/car_params.properties");
        System.out.println( "Hello World!" );
    }
}
