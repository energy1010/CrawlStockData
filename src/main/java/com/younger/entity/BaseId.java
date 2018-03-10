package com.younger.entity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseId {
	
	private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	protected static Object fromGson2Obj(String gsonStr, Class classOfT){
		if(gsonStr==null) return null;
		return gson.fromJson(gsonStr, classOfT);
	}
	
	protected static String toGsonStr(Object obj){
		return gson.toJson(obj);
	}
	
	public String toJsonStr(){
		//TODO:
		JSONObject jsonObj = new JSONObject();
//		jsonObj.put(key, value)
		return jsonObj.toString();
		
	}
	
	public String toGsonStr(){
//		return null;
		return toGsonStr(this);
	}
	
	
	public String toCSVStr(){
		return "";
	}
	
	
	  public static void write2CSV( String csvFile, List<? extends BaseId> objList) throws IOException{
	    	if(objList==null) return;
	    	FileOutputStream out = new FileOutputStream(csvFile);
//	    	Writer out = null;
//	    	BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
	    	for(BaseId s: objList){
	    		String line = s.toCSVStr()+"\n";
	    		//ANSI编码
	    		out.write(line.getBytes("GB2312"));
//	    		writer.write(s.getP_id()+","+new String(s.getP_name().getBytes("utf-8"), "GB2312") +"\r\n");
//	    		writer.write(s.getP_id()+","+s.getP_name()+"\r\n");
	    	}
	    	out.close();
//	    	writer.close();
	    }

}
