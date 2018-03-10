package com.younger.scrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.younger.MySqlTest;
import com.younger.dao.StockDao;
import com.younger.entity.Stock;

public class Scrab {
	
	

    /**
         * 发起http get请求获取网页源代码
         * @param requestUrl     String    请求地址
          * @return                 String    该地址返回的html字符串
          */
      private static String httpRequest(String requestUrl, String encode) {
    	  
//    	  String encode ="utf-8";
//    	  String encode ="gbk";
          StringBuffer buffer = null;
         BufferedReader bufferedReader = null;
         InputStreamReader inputStreamReader = null;
         InputStream inputStream = null;
         HttpURLConnection httpUrlConn = null;
          try {
             // 建立get请求
             URL url = new URL(requestUrl);
             httpUrlConn = (HttpURLConnection) url.openConnection();
             httpUrlConn.setDoInput(true);
             httpUrlConn.setRequestMethod("GET");
         // 获取输入流
             inputStream = httpUrlConn.getInputStream();
             inputStreamReader = new InputStreamReader(inputStream, encode);
             bufferedReader = new BufferedReader(inputStreamReader);
     // 从输入流读取结果
             buffer = new StringBuffer();
             String str = null;
             while ((str = bufferedReader.readLine()) != null) {
                 buffer.append(str);
              }
          } catch (Exception e) {
             e.printStackTrace();
         }  finally {
             // 释放资源
             if(bufferedReader != null) {
                 try {
                     bufferedReader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(inputStreamReader != null){
                 try {
                     inputStreamReader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(inputStream != null){
                 try {
                     inputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(httpUrlConn != null){
                 httpUrlConn.disconnect();
             }
         }
         return buffer.toString();
     }
      
      
      
      /** 
       * @deprecated
         * 过滤掉html字符串中无用的信息
           * @param html    String    html字符串
            * @return         String    有用的数据
            */ 
          private static String htmlFiter(String html) {  
              
             StringBuffer buffer = new StringBuffer();  
              String str1 = "";
               String str2 = "";
               buffer.append("今天:");
               
              // 取出有用的范围
               Pattern p = Pattern.compile("(.*)(<li class=\'dn on\' data-dn=\'7d1\'>)(.*?)(</li>)(.*)");  
              Matcher m = p.matcher(html);  
              if (m.matches()) {  
                   str1 = m.group(3);
                 // 匹配日期，注：日期被包含在<h2> 和 </h2>中
                 p = Pattern.compile("(.*)(<h2>)(.*?)(</h2>)(.*)");
                 m = p.matcher(str1);
                 if(m.matches()){
                     str2 = m.group(3);
                 buffer.append(str2);                 buffer.append("\n天气：");             }
             // 匹配天气，注：天气被包含在<p class="wea" title="..."> 和 </p>中
             p = Pattern.compile("(.*)(<p class=\"wea\" title=)(.*?)(>)(.*?)(</p>)(.*)");
             m = p.matcher(str1);
             if(m.matches()){
                 str2 = m.group(5);
                 buffer.append(str2);
                 buffer.append("\n温度：");
             }
             // 匹配温度，注：温度被包含在<p class=\"tem tem2\"> <span> 和 </span><i>中
             p = Pattern.compile("(.*)(<p class=\"tem tem2\"> <span>)(.*?)(</span><i>)(.*)");
             m = p.matcher(str1);
             if(m.matches()){
                 str2 = m.group(3);
                 buffer.append(str2);
                 buffer.append("°~");
             }
             p = Pattern.compile("(.*)(<p class=\"tem tem1\"> <span>)(.*?)(</span><i>)(.*)");
             m = p.matcher(str1);
             if(m.matches()){
                 str2 = m.group(3);
                 buffer.append(str2);
                 buffer.append("°\n风力：");
             }
             // 匹配风，注：<i> 和 </i> 中
             p = Pattern.compile("(.*)(<i>)(.*?)(</i>)(.*)");
             m = p.matcher(str1);
             if(m.matches()){
                 str2 = m.group(3);
                 buffer.append(str2);
             }
         }
         return buffer.toString();
     }
    
          
	
	
	  public  List<TagNode> getTagNodeListByXpath(String url,  String encode , String xpath) throws IOException, SQLException{
	       
	        String urlContent = httpRequest(url, encode);
	        List<TagNode> tagNodeList = new LinkedList<TagNode>();
	        if(urlContent!=null && !urlContent.isEmpty()){
//	            System.out.println(urlContent);
	            HtmlCleaner hc = new HtmlCleaner();
	        	TagNode tn = hc.clean(urlContent);
	        	Object[] objects;
				try {
					objects = tn.evaluateXPath(xpath);
//					if(objects.length!=2){
//						throw new RuntimeException("invalid ul length: "+ objects.length);
//					}
					for(Object obj: objects){
						if(obj instanceof TagNode){
							TagNode node  = (TagNode) obj;
							tagNodeList.add(node);
						}else{
							throw new RuntimeException("obj not tagNode:　"+obj.getClass() );
						}
					}
				} catch (XPatherException e) {
					e.printStackTrace();
				}
				
	        }else{
	        	throw new RuntimeException("request content is null");
	        }
	        return tagNodeList;
	    }


}
