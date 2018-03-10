package com.younger;


import java.sql.*;
import com.younger.entity.DbConfig;


public class MySqlTest{
	
	private static Connection conn=null;


	public static Connection getConnection(){
		
		if(conn == null){
	        try{
	            //调用Class.forName()方法加载驱动程序
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("成功加载MySQL驱动！");
	        }catch(ClassNotFoundException e1){
	            System.out.println("找不到MySQL驱动!");
	            e1.printStackTrace();
	        }
	        String configFile= "db.properties";
	        DbConfig dbconfig = DbConfig.getInstance(configFile);
	        String username = dbconfig.getValue("username");
	        String password = dbconfig.getValue("password");
	        String hostname = dbconfig.getValue("hostname", "localhost");
	        String port = dbconfig.getValue("port", "3306");
	        String dbname = dbconfig.getValue("dbname","mydata");
	        
	        String url=String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8", hostname, port, dbname);    //JDBC的URL    
	        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
//	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url,    username, password );
	        } catch (SQLException e){
	            e.printStackTrace();
	        }
		}
			return conn;
	    }
	
	
	 public static ResultSet executeQuery(Statement stmt, String sql) {
	        ResultSet rs = null;
	        try {
	            rs = stmt.executeQuery(sql);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rs;
	    }

	 
	 public static void close(Connection conn, Statement stmt,
	            PreparedStatement preStatement, ResultSet rs) {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            conn = null;
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            stmt = null;
	        }
	        if (preStatement != null) {
	            try {
	                preStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            preStatement = null;
	        }

	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            rs = null;
	        }
	    }

	 
	
	private void executeQuery(String sql){
		try{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement(); //创建Statement对象
         ResultSet rs = stmt.executeQuery(sql);//创建数据对象
         int columnCount = rs.getMetaData().getColumnCount();
             while (rs.next()){
            	 for(int i=0;i<columnCount-1;i++){
            		 System.out.print(rs.getString(i+1) + "\t");
            	 }
            	 System.out.print(rs.getString(columnCount));
//                 System.out.print(rs.getString(2) + "\t");
//                 System.out.print(rs.getInt(3) + "\t");
                 System.out.println();
             }
             rs.close();
             stmt.close();
             conn.close();
         }catch(Exception e){
             e.printStackTrace();
         }
	}
	
	public boolean executeUpdate(String sql){
		boolean flag = false;
		try{
			Connection conn = getConnection();
			Statement stmt = conn.createStatement(); //创建Statement对象
	     //修改数据的代码
//        String sql2 = "update stu set name=? where number=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,"8888");
        pst.setInt(2,198);
        int isOk = pst.executeUpdate();
        pst.close();
        if (isOk > 0) {
            return !flag;
        } else {
            return flag;
        }
//        //删除数据的代码
//        String sql3 = "delete from stu where number=?";
//        pst = conn.prepareStatement(sql3);
//        pst.setInt(1,701);
//        pst.executeUpdate();
            
//        ResultSet rs2 = stmt.executeQuery(sql);//创建数据对象
//        System.out.println("编号"+"\t"+"姓名"+"\t"+"年龄");
//        while (rs.next()){
//            System.out.print(rs2.getInt(1) + "\t");
//            System.out.print(rs2.getString(2) + "\t");
//            System.out.print(rs2.getInt(3) + "\t");
//            System.out.println();
//        }
//            
//        rs.close();
//        stmt.close();
//        conn.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
		return flag;
	}
	
	public static void test(){
	        try{
	            //调用Class.forName()方法加载驱动程序
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("成功加载MySQL驱动！");
	        }catch(ClassNotFoundException e1){
	            System.out.println("找不到MySQL驱动!");
	            e1.printStackTrace();
	        }
	        String configFile= "db.properties";
	        DbConfig dbconfig = DbConfig.getInstance(configFile);
	        String username = dbconfig.getValue("username");
	        String password = dbconfig.getValue("password");
	        String hostname = dbconfig.getValue("hostname", "localhost");
	        String port = dbconfig.getValue("port", "3306");
	        String dbname = dbconfig.getValue("dbname","mydata");
	        
	        String url=String.format("jdbc:mysql://%s:%s/%s", hostname, port, dbname);    //JDBC的URL    
	        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
	        Connection conn;
	        try {
	            conn = DriverManager.getConnection(url,    username, password );
	            //创建一个Statement对象
	            Statement stmt = conn.createStatement(); //创建Statement对象
	            System.out.print("成功连接到数据库！");
	            stmt.close();
	            conn.close();
	        } catch (SQLException e){
	            e.printStackTrace();
	        }
	    }
	
	
	public static void main(String[] args){
		
	}
	
	
}