package com.cyy.tools;

import java.sql.*;
import java.util.ResourceBundle;
import java.lang.ThreadLocal;


public final class DBUtils 
{
    //定义数据库描述信息  --- 驱动jar中最核心的类,是哪个,及其完整路径
	private static  String driver=null;     //驱动串
	private static  String url=null;        //链接串
	private static  String username=null;   //用户名
	private static  String password=null;   //口令(密码)                                            
	/**
	 * 数据库链接对象的线程副本---作用是为当前线程绑定Connection
	 * final作用于属性,属性禁止被修改,相当于定义常量
	 * 常量是引用不可变     ---  不可以再次给常量赋值
	 *      对象可变      ---  常量引用的对象,内部的数据是可以发生变化的
	 */
	private static final  ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
	

    //构造子(构造器,构造方法)
	private DBUtils() {	}

	/**
	 * 静态块:
	 * 1.解析资源文件
	 * 2.加载驱动
	 */
	static
	{
		try 
		{
			//获取资源文件解析对象
			ResourceBundle bundle=ResourceBundle.getBundle("DBOptions");
			//从资源文件中解析数据
			driver=bundle.getString("DRIVER");
			url=bundle.getString("URL");
			username=bundle.getString("USERNAME");
			password=bundle.getString("PASSWORD");
			
			
			//加载驱动   --- 通过反射技术,将驱动jar核心类,实例化
			Class.forName(driver);
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/************************************************************************************
	 *                       事务相关方法
	 ************************************************************************************/
	 /**
	  * 开启事务
	  * @throws Exception
	  */
     public static void beginTransaction()throws Exception
     {
    	 DBUtils.getConnection().setAutoCommit(false);
     }
     /**
      * 事务提交
      * @throws Exception
      */
     public static void commit()throws Exception
     {
    	 DBUtils.getConnection().commit();
     }
	
     /**
      * 事务回滚
      */
     public static void rollback()
     {
    	 try
    	 {
    		 DBUtils.getConnection().rollback();
    	 }
    	 catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    	 }
     }
     
     /**
      * 结束事务
      * @throws Exception
      */
     public static void endTransaction()throws Exception
     {
    	 try
    	 {
    		 DBUtils.getConnection().setAutoCommit(true);
    	 }
    	 catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    	 }
     }
	
	
	/************************************************************************************
	 *                       SQL编译方法
	 ************************************************************************************/
	
	 /**
	  * SQL编译基础方法
	  * @param sql
	  * @return
	  * @throws Exception
	  */
	 public static PreparedStatement prepareStatement(String sql)throws Exception
	 {
		 return DBUtils.getConnection().prepareStatement(sql);
	 } 
	
	 /**
	  * 编译SQL语句
	  * <
	  *   该方法生成的PreparedStatement,执行完毕后
	  *   可以生成随意滚动的ResultSet
	  * >
	  * @param sql
	  * @return
	  * @throws Exception
	  */
	 public static PreparedStatement prepareStatementCursor(final String sql)throws Exception
	 {
		 return DBUtils.getConnection().prepareStatement(
				                                         sql,
				                                         ResultSet.TYPE_SCROLL_SENSITIVE,    //rs可以双向滚动
				                                         ResultSet.CONCUR_READ_ONLY          //rs结果集查询到的数据,以只读方式并发访问
				                                         );
	 }
	
	
	/************************************************************************************
	 *                       基本接口的创建及销毁方法 
	 ************************************************************************************/
	
	
	public static Connection getConnection()throws Exception
	{
	   //1.获取当前线程绑定的Connection
	   Connection conn=threadLocal.get();
	   //2.判断链接对象是否存在
	   if(conn==null)
	   {
		   //3.创建链接对象
		   conn=DriverManager.getConnection(url, username, password);
		   //4.将链接对象通过ThreadLocal与当前线程建立绑定关系
		   threadLocal.set(conn);
	   }
	   return conn;
	}

	
	public static void close(ResultSet rs)
	{
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	} 

	
	public static void close(PreparedStatement pstm)
	{
		try
		{
			if(pstm!=null)
			{
				pstm.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	} 
	
	public static void close()
	{
		try
		{
			//获取当前线程绑定的链接对象
			Connection conn=threadLocal.get();
			if(conn!=null  && !conn.isClosed())
			{
				//解除当前线程与连接对象的绑定关系
				threadLocal.remove();
				//关闭连接对象
				conn.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	} 
	
	public static void main(String[] args) 
	{
		try 
		{
			System.out.println(DBUtils.getConnection());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
