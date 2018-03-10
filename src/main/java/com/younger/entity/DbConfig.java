package com.younger.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

public class DbConfig {
	
	private static DbConfig instance = null;
	
	String hostname = null;
	String port = null;
	String dbname = null;
	
	String username = null;
	String password = null;
	
	private Properties prop = new Properties();//属性集合对象 
	

	private  DbConfig(String configFile) throws IOException{
			
			if(new File(configFile).exists() && new File(configFile).isFile()  ){
				System.out.println("load properties from file: "+configFile );
				FileInputStream fis = new FileInputStream(configFile);//属性文件流     
				prop.load(fis);//将属性文件流装载到Properties对象中    
			}else{
				throw new RuntimeException("can't find config file");
			}
	}
	
	public static synchronized DbConfig getInstance(String configFile){
		if(instance==null){
			try {
				instance =  new DbConfig(configFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	
	public String getValue(String key){
		return getValue(key, null);
	}

	public String getValue(String key, String defaultVal){
		String val = defaultVal;
		if(prop.containsKey(key)){
			val = prop.getProperty(key);
		}
		return val;
	}
	
	

	public void print(){
		for(Entry<Object, Object> e:prop.entrySet()){
			System.out.println(e.getKey()+"\t"+e.getValue());
		}
	}

}
