package com.cyy.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.cyy.tools.DBUtils;
/**
 * 所有service的父类
 * @author lxm
 *
 */
public class JdbcServicesSupport {
	
	private ThreadLocal<List<PstmBean>>  threadBeanList=new ThreadLocal<>();
	
    protected Map<String,Object> dto=null;
	public JdbcServicesSupport(Map<String,Object> dto) 
	{
		this.dto=dto;
	}

	public JdbcServicesSupport() {
		
	}
	
	/*********************************************************************************
	 *                       辅助方法
	 *********************************************************************************/
	/**
	 * 从DTO获取数组
	 * @param key
	 * @return
	 * @throws Exception
	 */
    protected final String[] getArray(final String key)throws Exception
    {
         //1.获取数据
    	 Object val=this.dto.get(key);
         //2.判断val是否是null
    	 if(val==null)
    	 {
    		 throw new Exception("在当前DTO"+this.dto+"中,不存在名字为"+key+"的键值对......");
    	 }
    	
    	 //3.判断键值对的value对象是否是字符串数组
    	 if(val instanceof String[])
    	 {
    		 return (String[])val;
    	 }
    	 else
    	 {
    		 return new String[]{val.toString()};
    	 }
    }
    /**
     * 从DTO获取单一数据项
     * @param key
     * @return
     */
    protected final Object getVal(final String key)
    {
    	return this.dto.get(key);
    }
    
    /**
     * 校验数据是否为空
     * @param value
     * @return
     */
    protected final boolean checkNull(Object  value)
    {
    	return value!=null && !value.toString().trim().equals("");
    }
	
	
	/*********************************************************************************
	 *                       查询封装方法
	 *********************************************************************************/
    /**
     * 行数统计方法
     * @param tableName   ---  表名
     * @param idname      ---  条件列名称
     * @param idval       ---  条件值
     * @return
     * @throws Exception
     */
    public final int  queryForCount(final String tableName,final String idname,final Object idval)throws Exception
    {
    	StringBuilder sql=new StringBuilder()
    	.append("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE ").append(idname).append("=?");
    	;
    	return this.queryForInt(sql.toString(), idval);
    } 
    
    /**
     * 单一数据查询方法
     * @param sql
     * @param args
     * @return
     * @throws Exception
     */
    protected final int queryForInt(final String sql,final Object...args)throws Exception
    {
    	//1.定义JDBC接口
    	PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//2.编译SQL语句
			pstm=DBUtils.prepareStatement(sql);
			int index=1;
			for(Object element:args)
			{
				pstm.setObject(index++, element);
			}
			//3.查询
			rs=pstm.executeQuery();
			int val=0;
			if(rs.next())
			{
				val=rs.getInt(1);
			}
			return val;
			
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
    }
    
	/**
	 * 数据批量查询
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected final List<Map<String,String>> queryForList(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//编译SQL
			pstm=DBUtils.prepareStatement(sql);
			//参数赋值
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//执行SQL
			rs=pstm.executeQuery();
			//获取rs描述对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//计算查询结果中的列数
			int count=rsmd.getColumnCount();
			//计算初始容量
			int initSize=((int)(count/0.75))+1;
			
			//定义List容器,装载查询结果
			List<Map<String,String>> rows=new ArrayList<>();
			//定义Map容器,装载当前行数据
			Map<String,String> ins=null;
			//循环解析rs
			while(rs.next())
			{
				//实例化装载当前行数据的HashMap
				ins=new HashMap<>(initSize);
				//循环当前行所有列,形成键值对,并放入ins
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//将代表当前行数据的Map对象放入List容器
				rows.add(ins);
			}
			return rows;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	protected final List<Map<String,String>> szhqueryForList(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//编译SQL
			pstm=DBUtils.prepareStatement(sql);
			//参数赋值
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//执行SQL
			rs=pstm.executeQuery();
			//获取rs描述对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//计算查询结果中的列数
			int count=rsmd.getColumnCount();
			//计算初始容量
			int initSize=((int)(count/0.75))+1;
			
			//定义List容器,装载查询结果
			List<Map<String,String>> rows=new ArrayList<>();
			//定义Map容器,装载当前行数据
			Map<String,String> ins=null;
			while(rs.next())
			{
				//实例化装载当前行数据的HashMap
				ins=new LinkedHashMap<>(initSize);
				//循环当前行所有列,形成键值对,并放入ins
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//将代表当前行数据的Map对象放入List容器
				rows.add(ins);
			}
			return rows;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	/**
	 * 单一实例查询
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected final Map<String,String> queryForMap(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//3.编译SQL语句
			pstm=DBUtils.prepareStatement(sql.toString());
			//4.参数赋值
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//5.执行查询
			rs=pstm.executeQuery();
			
			//6.定义Map装载当前行数据
			Map<String,String> ins=null;
			//7.判断是否查询到数据
			if(rs.next())
			{
				//8.获取结果集描述对象
				ResultSetMetaData rsmd=rs.getMetaData();
				//9.计算列数
				int count=rsmd.getColumnCount();
				//10.计算初始容量
				int initSize=((int)(count/0.75))+1;
				//11.实例化容器
				ins=new HashMap<>(initSize);
				//12.循环当前行各列
				for(int i=1;i<=count;i++)
				{
					//13.向容器中放入当前行各列
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
			}
			return ins;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	/*********************************************************************************
	 *                       多表更新事务封装方法
	 *  一个方法中,需要执行的SQL语句,一定多个定义                       
	 *********************************************************************************/
	/**
	 * 多表更新下,非批处理语句的封装
	 * <
	 *    对应如下SQL
	 *    DELETE:
	 *           DELETE FROM TABLE WHERE ID=?
	 *    UPDATE:
	 *           UPDATE TABLE SET C1=?,C2=?,C3=?.....Cn=? WHERE ID=?
	 *    INSERT:
	 *           INSERT INTO TABLE(C1,C2,C3,C4......Cn)
	 *                      VALUES(?,?,?,?,?,?,?,.....)                 
	 * >
	 * @param sql    ---  需要执行的SQL语句   
	 * @param args   ---  参数值数组
	 * @throws Exception
	 */
	protected final void appendSql(final String sql,final Object...args)throws Exception
	{
		//1.编译SQL语句
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.参数赋值
		int index=1;
		for(Object param:args)
		{
			pstm.setObject(index++, param);
		}
		//3.构造PstmBean实例
		PstmBean bean=new PstmBean(pstm);
		//4.将PstmBean放入当前线程容器
		this.getPstmBeanList().add(bean);
	}
	
	/**
	 * 多表更新批处理
	 * <
	 *    适合的SQL格式如下:
	 *    DELETE FROM TABLE WHERE ID=?
	 *    
	 *    对应删除的批处理
	 * >
	 * @param sql         --- SQL语句 
	 * @param args        --- 主键数组
	 * @return
	 * @throws Exception
	 */
	protected  final void appendBatchSql(final String sql,final Object...args)throws Exception
	{
		//1.编译SQL语句
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.完成批处理参数替换
		for(Object param:args)
		{
			pstm.setObject(1, param);
			pstm.addBatch();
		}
		//3.构造PstmBean
		PstmBean bean=new PstmBean(pstm,true);
        //4.将PstmBean放入当前线程容器
		this.getPstmBeanList().add(bean);
	}
	
	/**
	 * 多表更新批处理
	 * <
	 *    适合的SQL语句:
	 *    UPDATE TABLE 
	 *       SET STATE=?
	 *     WHERE ID=?
	 *   
	 *    对应SET列表   单一字段同值  修改语句
	 *    单一状态更新下的批处理  
	 * >
	 * @param sql         --- SQL语句 
	 * @param newState    --- 目标状态  
	 * @param args        --- 主键数组
	 * @return
	 * @throws Exception
	 */
	protected final void appendBatchSql(final String sql,final Object newState,final Object...args)throws Exception
	{
		//1.编译SQL语句
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//替换非主键参数
		pstm.setObject(1, newState);
		//替换主键参数
		for(Object param:args)
		{
			pstm.setObject(2, param);
			pstm.addBatch();
		}
		//3.构造PstmBean
		PstmBean bean=new PstmBean(pstm,true);
        //4.将PstmBean放入当前线程容器
		this.getPstmBeanList().add(bean);
	}
	
    /**
     * 多表更新批处理
     * <
     *    UPDATE TABLE 
     *       SET C1=?,C2=?,C3=?.....Cn=?
     *     WHERE ID=?  
     * >
     * @param sql         ---  SQL语句
     * @param paramList   ---  set列表的参数数组
     * @param idlist      ---  主键数组
     * @return
     * @throws Exception
     */
	protected final void appendBatchSql(final String sql,final Object[] paramList,final Object...idlist)throws Exception
	{
		//1.编译SQL语句
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.1批处理参数赋值--替换非主键参数
		int index=1;
		for(Object param:paramList)
		{
			pstm.setObject(index++, param);
		}
        //2.2批处理参数赋值--替换主键参数
		for(Object id:idlist)
		{
			pstm.setObject(index, id);
			pstm.addBatch();
		}
		//3.构造PstmBean
		PstmBean bean=new PstmBean(pstm,true);
        //4.将PstmBean放入当前线程容器
		this.getPstmBeanList().add(bean);
	}
	
	/**
	 * 获取当前线程绑定的装载PstmBean的容器
	 * @return
	 * @throws Exception
	 */
	private List<PstmBean> getPstmBeanList()throws Exception
	{
		List<PstmBean> pstmBeanList=this.threadBeanList.get();
		if(pstmBeanList==null)
		{
			pstmBeanList=new ArrayList<>();
			this.threadBeanList.set(pstmBeanList);
		}
		return pstmBeanList;
	}
	
	/**
	 * 执行事务
	 * @return
	 * @throws Exception
	 */
	protected boolean executeTransaction()throws Exception
	{
		//收集需要销毁的Pstm对象
		List<PreparedStatement> pstmList=null;
		try
		{
			//获取线程绑定的装载PstmBean的容器
			List<PstmBean> pstmBeanList=this.getPstmBeanList();
			
			//判断当前线程是否绑定了需要执行的SQL语句
			if(pstmBeanList==null || pstmBeanList.size()==0)
			{
				throw new Exception("当前事务不存在需要执行SQL语句......");
			}

			//参照pstmBeanList的长度构造等长的pstmList
			pstmList=new ArrayList<>(pstmBeanList.size());

			//定义事务返回值
			boolean tag=false;
			//开启事务
			DBUtils.beginTransaction();
			try
			{
				//在事务范围内执行当前线程绑定的所有SQL语句对象
				//循环容器,获取PstmBean
				for(PstmBean bean:pstmBeanList)
				{
					if(bean.isBatch())  //该语句需要批处理
					{
						bean.getPstm().executeBatch();
					}
					else
					{
						bean.getPstm().executeUpdate();
					}	
					
					//将PstmBean中绑定PreparedStatement对象放入pstmList
					pstmList.add(bean.getPstm());
				}
				//提交事务
				DBUtils.commit();
				//修改返回值
				tag=true;

			}
			catch(Exception ex)
			{
				//事务回滚
				DBUtils.rollback();
				ex.printStackTrace();
			}
			finally
			{
				//结束事务
				DBUtils.endTransaction();
			}
			return tag;
		}
		finally
		{
			//销毁事务过程中的所有语句对象
			if(pstmList!=null)
			{
				for(PreparedStatement pstm:pstmList)
				{
					DBUtils.close(pstm);
				}
			}
			//解除线程绑定
			this.threadBeanList.remove();
		}
	}
	
	

	
	
	
	/*********************************************************************************
	 *                       单一表批处理事务封装
	 *********************************************************************************/
    /**
     * 单一表批处理
     * <
     *    UPDATE TABLE 
     *       SET C1=?,C2=?,C3=?.....Cn=?
     *     WHERE ID=?  
     * >
     * @param sql         ---  SQL语句
     * @param paramList   ---  set列表的参数数组
     * @param idlist      ---  主键数组
     * @return
     * @throws Exception
     */
	protected final boolean batchUpdate(final String sql,final Object[] paramList,final Object...idlist)throws Exception
	{
	     //1.定义JDBC变量
		 PreparedStatement pstm=null;
		 try
		 {
			 //2.编译SQL语句
			 pstm=DBUtils.prepareStatement(sql);
			 //3.参数赋值
			 //替换非主键参数
			 int index=1;
			 for(Object param:paramList)
			 {
				 pstm.setObject(index++, param);   //3
			 }
			 //替换主键值
			 for(Object id:idlist)   //50
			 {
				 pstm.setObject(index, id);
				 pstm.addBatch();
			 }
			 
			 boolean tag=false;
			 DBUtils.beginTransaction();
			 try
			 {
				 pstm.executeBatch();
				 DBUtils.commit();
				 tag=true;
			 }
			 catch(Exception ex)
			 {
				 DBUtils.rollback();
				 ex.printStackTrace();
			 }
			 finally
			 {
				 DBUtils.endTransaction();
			 }
			 return tag;
		 }
		 finally
		 {
			 DBUtils.close(pstm);
		 }
	}

    
	/**
	 * 单一表批处理
	 * <
	 *    适合的SQL语句:
	 *    UPDATE TABLE 
	 *       SET STATE=?
	 *     WHERE ID=?
	 *   
	 *    对应SET列表   单一字段同值  修改语句
	 *    单一状态更新下的批处理  
	 * >
	 * @param sql         --- SQL语句 
	 * @param newState    --- 目标状态  
	 * @param args        --- 主键数组
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,final Object newState,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		try
		{
			//2.编译SQL
			pstm=DBUtils.prepareStatement(sql);
			//非主键参数替换
			pstm.setObject(1, newState);
			//替换主键参数
			for(Object id:args)
			{
				pstm.setObject(2, id);
				pstm.addBatch();
			}
			//以事务方式执行SQL
			boolean tag=false;
			DBUtils.beginTransaction();
			try
			{
				pstm.executeBatch();
				DBUtils.commit();
				tag=true;
			}
			catch(Exception ex)
			{
				DBUtils.rollback();
				ex.printStackTrace();
			}
			finally
			{
				DBUtils.endTransaction();
			}
			return tag;
			
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}  
	
	/**
	 * 单一表批处理
	 * <
	 *    适合的SQL格式如下:
	 *    DELETE FROM TABLE WHERE ID=?
	 *    
	 *    对应删除的批处理
	 * >
	 * @param sql         --- SQL语句 
	 * @param args        --- 主键数组
	 * @return
	 * @throws Exception
	 */
	protected  final boolean batchUpdate(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		try
		{
            
			//2.编译SQL
			pstm=DBUtils.prepareStatement(sql);
			//3.批处理参数赋值
			for(Object id:args)
			{
				pstm.setObject(1, id);
				pstm.addBatch();
			}
			
			/**
			 * 以事务方式执行
			 */
			//1.定义事务返回值
			boolean tag=false;
			//2.开启事务
			DBUtils.beginTransaction();
			try
			{
				//3.以事务方式执行SQL语句
				pstm.executeBatch();
				//4.1.提交事务
				DBUtils.commit();
				//5.修改返回值
				tag=true;
			}
			catch(Exception ex)
			{
				//4.2.事务回滚
				DBUtils.rollback();
				ex.printStackTrace();
			}
			finally
			{
				//结束事务
				DBUtils.endTransaction();
			}
			return tag;
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}
	
	/*********************************************************************************
	 *                       非事务单一表更新方法
	 *********************************************************************************/
	
	/**
	 * 数据更新方法
	 * <
	 *   非事务情况下,执行更新 语句
	 *   将接收到的SQL编译成语句对象,然后将args中的参数赋值给pstm
	 *   然后执行
	 * >
	 * @param sql    ---  接收到的SQL语句
	 * @param args   ---  参数数组
	 * @return
	 * @throws Exception
	 */
	protected  final boolean executeUpdate(final String sql,final Object...args)throws Exception
	{
		//1.定义JDBC接口
		PreparedStatement pstm=null;
		try
		{
			//2.编译SQL语句
			pstm=DBUtils.prepareStatement(sql);
			//3.完成参数赋值
			int index=1;
			for(Object element:args)
			{
				pstm.setObject(index++, element);
			}
			//4.执行SQL语句
			return pstm.executeUpdate()>0;
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}


	/*********************************************************************************
	 *                       PreparedStatement 辅助管理类---内部类实现
	 *********************************************************************************/

	private final class  PstmBean 
	{
		//需要执行的SQL语句对应的语句对象
		private PreparedStatement pstm=null;
		//语句对象以何种方式执行  true---pstm.executeBatch()  false-----pstm.executeUpdate();
		private boolean isBatch=false;
		public PstmBean(final PreparedStatement pstm,final boolean isBatch) 
		{
			this.pstm=pstm;
			this.isBatch=isBatch;
		}
		
		public PstmBean(final PreparedStatement pstm) 
		{
			this.pstm=pstm;
			this.isBatch=false;
		}
		
		/**
		 * 获取Bean中绑定的PreparedStatement
		 * @return
		 */
		public PreparedStatement getPstm() {
			return this.pstm;
		}
		
		/**
		 * 获取PreparedStatement的执行方式
		 * @return
		 */
		public boolean isBatch() {
			return this.isBatch;
		}
	}

}

