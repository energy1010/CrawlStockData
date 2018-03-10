package com.younger.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.younger.MySqlTest;
import com.younger.entity.Stock;

/**
 * 数据层处理类
 * @author Administrator
 *
 */
public class StockDao {
	
	 public List<Stock> query() throws SQLException
	  {
	    List<Stock> stockList = new ArrayList<Stock>();

	    // 获得数据库连接
	    Connection conn = MySqlTest.getConnection();

	    StringBuilder sb = new StringBuilder();
	    sb.append("select * from stock");

	    // 通过数据库的连接操作数据库，实现增删改查
	    PreparedStatement ptmt = conn.prepareStatement(sb.toString());

	    ResultSet rs = ptmt.executeQuery();

	    Stock stock = null;

	    while (rs.next())
	    {
	      stock = new Stock();
	      stock.setP_id(rs.getString("stackid"));
	      stock.setP_name(rs.getString("stackname"));
	      stockList.add(stock);
	    }
	    return stockList;
	  }

	 
	 /**
	   * 查询单个
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public Stock queryById(Integer id) throws SQLException
	  {
		  Stock g = null;

	    Connection conn = MySqlTest.getConnection();

	    String sql = "select * from stock " + " where stockid=? ";

	    PreparedStatement ptmt = conn.prepareStatement(sql);

	    ptmt.setInt(1, id);

	    ResultSet rs = ptmt.executeQuery();

	    while (rs.next())
	    {
	      g = new Stock();
	      g.setP_id( rs.getString("stockid"));
	      g.setP_name(rs.getString("stockname"));
	    }

	    return g;
	  }
	  
	  
	  /**
	   * 添加女神
	   * 
	   * @throws SQLException
	   */
	  public void addStock(Stock stock) throws SQLException
	  {
	    // 获得数据库连接
	    Connection conn =   MySqlTest.getConnection();

	    String sql = "insert into stock(stockid, stockname, ishuzhi) values(?,?,?)";

	    PreparedStatement ptmt = conn.prepareStatement(sql);

	    ptmt.setString(1, stock.getP_id());
	    ptmt.setString(2, stock.getP_name());
	    ptmt.setInt(3, stock.isP_ishz()==true?1:0);
	    ptmt.execute();
	  }
	  
	  /**
	   * 添加女神
	   * 
	   * @throws SQLException
	   */
	  public void addStockList(List<Stock> stockList) throws SQLException
	  {
	    // 获得数据库连接
	    Connection conn =   MySqlTest.getConnection();

	    //不存在记录则插入，否则更新
//	    BEGIN
//	    #定义一个变量来保存该记录是否存在
//	    declare num int;
//	    #这条sql，就是查询对应的记录有多少条，注意 into num 这两句话，就是把count(*) 查出的值，赋给到num中
//	    select count(*) into num from t_count_view where TO_DAYS(now())=TO_DAYS(day);
//	    #接下来的就是判断了，注意，判断是否等于，只有一个等于号
//	    if(num=0)
//	    #等于号之后，还要写一个Then，代表条件成立后要执行的sql
//	        Then
//	        insert into t_count_view(view_people,view_num,day)values(1,1,now());
//	  #else可以直接用，不需要加then
//	    else
//	        update t_count_view set view_people=view_people+1;
//	    #但是当if使用完之后，一定要写end if，代表着if的条件判断结束了
//	  end if;
//	END
//	    INSERT INTO `his_examine_result` (Mid,His_Examine_Mid, His_File_Mid, ResultType, His_Employee_Mid,
//                His_Employee_Name, ExamineResult, ExamineItemName, ExamineStandardName, 
//                ExamineItemUnit, ExamineHighValue, ExamineLowValue, ExamineDiscription, Discription)
//VALUES ($mid,$examineMid,$fileMid,1,null,null,'',$itemName,$standName,$itemUnit,$highValue,$lowValue,'','') 
//ON DUPLICATE KEY UPDATE ExamineResult=$result,ExamineDiscription=$dis
	    String sql = "INSERT INTO stock(stockid, stockname, ishuzhi) values(?,?,?) ON DUPLICATE KEY UPDATE stockname=?, ishuzhi=?";

	    PreparedStatement ptmt = conn.prepareStatement(sql);

	    for(Stock s: stockList){
		    ptmt.setString(1, s.getP_id());
		    ptmt.setString(2, s.getP_name());
		    ptmt.setInt(3, s.isP_ishz()==true?1:0);
		    ptmt.setString(4, s.getP_name());
		    ptmt.setInt(5, s.isP_ishz()==true?1:0);
		    ptmt.execute();
	    }
	  }


	  public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
		StockDao stockDao = new StockDao();
		Stock stock = new Stock("0", new String("东方国信".getBytes(), "utf-8") );
		stock.setP_ishz(true);
		try {
			stockDao.addStock(stock);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySqlTest.getConnection().close();
	}



}
