package com.younger.job;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONArray;

import com.younger.MySqlTest;
import com.younger.dao.StockDao;
import com.younger.entity.BanKuai;
import com.younger.entity.BaseId;
import com.younger.entity.Stock;
import com.younger.scrab.Scrab;

/**
 * Created by Administrator on
7/7/22.
 */
public class CrawStockId {


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
    
          
          
    public static void write2CSV( String csvFile, List<Stock> stockList) throws IOException{
    	if(stockList==null) return;
    	FileOutputStream out = new FileOutputStream(csvFile);
//    	Writer out = null;
//    	BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
    	for(Stock s: stockList){
    		String line = s.getP_id()+","+s.getP_name()+"\n";
    		//ANSI编码
    		out.write(line.getBytes("GB2312"));
//    		writer.write(s.getP_id()+","+new String(s.getP_name().getBytes("utf-8"), "GB2312") +"\r\n");
//    		writer.write(s.getP_id()+","+s.getP_name()+"\r\n");
    	}
    	out.close();
//    	writer.close();
    }
          

    public static void getStock() throws IOException, SQLException{
    	
        String url =    "http://quote.eastmoney.com/stocklist.html";
        String encode ="gbk";
        String urlContent = httpRequest(url, encode);
        if(urlContent!=null && !urlContent.isEmpty()){
//            System.out.println(urlContent);
            HtmlCleaner hc = new HtmlCleaner();
        	TagNode tn = hc.clean(urlContent);
        	String xpath = "//div[@id='quotesearch']/ul"; //[1]
        	List<Stock> huStockList = new LinkedList<Stock>();	
        	List<Stock> szStockList = new LinkedList<Stock>();
        	
        	Object[] objects;
			try {
				objects = tn.evaluateXPath(xpath);
				if(objects.length!=2){
					throw new RuntimeException("invalid ul length: "+ objects.length);
				}
				Pattern p = Pattern.compile("(.*)\\((\\d+)\\)");
				Matcher m;
				String stockName = null;
				String stockId = null;
				Stock stock = null;
				int i=0;
				for(Object obj: objects){
					
					if(obj instanceof TagNode){
						TagNode node  = (TagNode) obj;
						List<TagNode> lilist = node.getChildTagList();
						for(TagNode t: lilist){
//							System.out.println(t.getText());
//							基金景业(500017)
				             m = p.matcher(t.getText());
				             if(m.matches()&& m.groupCount()==2 ){
				                 stockName  = m.group(1);
				                 stockId = m.group(2);
				                 if(stockName.isEmpty() || stockId.isEmpty()){
				                	 System.err.println("invalid stock:" + t.getText() );
				                 }
				                 stock = new Stock(stockId, stockName);
				             }
							if(i==0){
								stock.setP_ishz(true);
								huStockList.add(stock);
							}else{
								stock.setP_ishz(false);
								szStockList.add(stock);
							}
						}
					}else{
						
					}
					i++;
//					System.out.println(obj+" " +obj.getClass());
				}
//			write2CSV("hustock.csv", huStockList);
//			write2CSV("szstock.csv", szStockList);
				StockDao stockDao = new StockDao();
				stockDao.addStockList(huStockList);
				stockDao.addStockList(szStockList);
				
				MySqlTest.getConnection().close();
			} catch (XPatherException e) {
				e.printStackTrace();
			}
			
        }else{
        	throw new RuntimeException("request content is null");
        }
//    	   String html = httpRequest("http://www.weather.com.cn/html/weather/101280101.shtml");  
//    	   String result = htmlFiter(html);  
//    	   System.out.println(result);

        System.out.println("finished");
    }

    
    /**
     * 板块
     * @throws IOException
     * @throws SQLException
     */
    public static void getBankuai() throws IOException, SQLException{
    	String url =    "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._BKHY&sty=FPGBKI&st=c&sr=-1&p=1&ps=5000&cb=&js=var%20BKCache=[(x)]&token=7bc05d0d4c3c22ef9fca8c2a912d779c";
        String encode ="utf-8";
        String urlContent = httpRequest(url, encode);
        List<BanKuai> bkList = new ArrayList<BanKuai>();
        if(urlContent!=null && !urlContent.isEmpty()){
//            System.out.println(urlContent);
            urlContent = urlContent.substring(urlContent.indexOf("["));
			try {
				JSONArray jsonArray = new JSONArray(urlContent); 
				for(int i=0;i<jsonArray.length();i++){
					String str = jsonArray.getString(i);
//					System.out.println(str);
					String bkid = str.split(",")[1];
					String bkname = str.split(",")[2];
					bkList.add(new BanKuai(bkid, bkname));
				}
			BaseId.write2CSV("bankuai.csv", bkList);
//				StockDao stockDao = new StockDao();
//				stockDao.addStockList(huStockList);
				
//				MySqlTest.getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
        }else{
        	throw new RuntimeException("request content is null");
        }
//    	   String html = httpRequest("http://www.weather.com.cn/html/weather/101280101.shtml");  
//    	   String result = htmlFiter(html);  
//    	   System.out.println(result);

        System.out.println("finished");
    	
    }
    
    
    
    /**
     * 爬取大盘指数
     */
    public static void getDapanzs(){
    	Scrab scrab = new Scrab();
//    	String url = "http://quote.eastmoney.com/center/list.html";
    	String encode = "gbk";
//    	String xpath = "//div[@id='qqgscont']/ul[1]/li[1]";
    	
    	
    	String url ="http://quote.eastmoney.com/zs000001.html";
    	String xpath="//span[@class='ar a']/strong[1]";
    	
    	try {
			List<TagNode> tagNodeList =scrab.getTagNodeListByXpath(url, encode, xpath);
			if(tagNodeList!=null && tagNodeList.size()>0){
//				for(TagNode tagNode)
				TagNode node = tagNodeList.get(0);
				System.out.println( node.getText());
				List<TagNode> lilist = node.getChildTagList();
				for(TagNode t: tagNodeList){
					System.out.println( t.getText() );
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    public static void main(String[] args) throws IOException, SQLException {
//		getDapanzs();
		
		getBankuai();
	}

}
