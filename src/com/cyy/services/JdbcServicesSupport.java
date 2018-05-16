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
 * ����service�ĸ���
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
	 *                       ��������
	 *********************************************************************************/
	/**
	 * ��DTO��ȡ����
	 * @param key
	 * @return
	 * @throws Exception
	 */
    protected final String[] getArray(final String key)throws Exception
    {
         //1.��ȡ����
    	 Object val=this.dto.get(key);
         //2.�ж�val�Ƿ���null
    	 if(val==null)
    	 {
    		 throw new Exception("�ڵ�ǰDTO"+this.dto+"��,����������Ϊ"+key+"�ļ�ֵ��......");
    	 }
    	
    	 //3.�жϼ�ֵ�Ե�value�����Ƿ����ַ�������
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
     * ��DTO��ȡ��һ������
     * @param key
     * @return
     */
    protected final Object getVal(final String key)
    {
    	return this.dto.get(key);
    }
    
    /**
     * У�������Ƿ�Ϊ��
     * @param value
     * @return
     */
    protected final boolean checkNull(Object  value)
    {
    	return value!=null && !value.toString().trim().equals("");
    }
	
	
	/*********************************************************************************
	 *                       ��ѯ��װ����
	 *********************************************************************************/
    /**
     * ����ͳ�Ʒ���
     * @param tableName   ---  ����
     * @param idname      ---  ����������
     * @param idval       ---  ����ֵ
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
     * ��һ���ݲ�ѯ����
     * @param sql
     * @param args
     * @return
     * @throws Exception
     */
    protected final int queryForInt(final String sql,final Object...args)throws Exception
    {
    	//1.����JDBC�ӿ�
    	PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//2.����SQL���
			pstm=DBUtils.prepareStatement(sql);
			int index=1;
			for(Object element:args)
			{
				pstm.setObject(index++, element);
			}
			//3.��ѯ
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
	 * ����������ѯ
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected final List<Map<String,String>> queryForList(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//����SQL
			pstm=DBUtils.prepareStatement(sql);
			//������ֵ
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//ִ��SQL
			rs=pstm.executeQuery();
			//��ȡrs��������
			ResultSetMetaData rsmd=rs.getMetaData();
			//�����ѯ����е�����
			int count=rsmd.getColumnCount();
			//�����ʼ����
			int initSize=((int)(count/0.75))+1;
			
			//����List����,װ�ز�ѯ���
			List<Map<String,String>> rows=new ArrayList<>();
			//����Map����,װ�ص�ǰ������
			Map<String,String> ins=null;
			//ѭ������rs
			while(rs.next())
			{
				//ʵ����װ�ص�ǰ�����ݵ�HashMap
				ins=new HashMap<>(initSize);
				//ѭ����ǰ��������,�γɼ�ֵ��,������ins
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//������ǰ�����ݵ�Map�������List����
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
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//����SQL
			pstm=DBUtils.prepareStatement(sql);
			//������ֵ
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//ִ��SQL
			rs=pstm.executeQuery();
			//��ȡrs��������
			ResultSetMetaData rsmd=rs.getMetaData();
			//�����ѯ����е�����
			int count=rsmd.getColumnCount();
			//�����ʼ����
			int initSize=((int)(count/0.75))+1;
			
			//����List����,װ�ز�ѯ���
			List<Map<String,String>> rows=new ArrayList<>();
			//����Map����,װ�ص�ǰ������
			Map<String,String> ins=null;
			while(rs.next())
			{
				//ʵ����װ�ص�ǰ�����ݵ�HashMap
				ins=new LinkedHashMap<>(initSize);
				//ѭ����ǰ��������,�γɼ�ֵ��,������ins
				for(int i=1;i<=count;i++)
				{
					ins.put(rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				//������ǰ�����ݵ�Map�������List����
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
	 * ��һʵ����ѯ
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	protected final Map<String,String> queryForMap(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//3.����SQL���
			pstm=DBUtils.prepareStatement(sql.toString());
			//4.������ֵ
			int index=1;
			for(Object param:args)
			{
				pstm.setObject(index++, param);
			}
			//5.ִ�в�ѯ
			rs=pstm.executeQuery();
			
			//6.����Mapװ�ص�ǰ������
			Map<String,String> ins=null;
			//7.�ж��Ƿ��ѯ������
			if(rs.next())
			{
				//8.��ȡ�������������
				ResultSetMetaData rsmd=rs.getMetaData();
				//9.��������
				int count=rsmd.getColumnCount();
				//10.�����ʼ����
				int initSize=((int)(count/0.75))+1;
				//11.ʵ��������
				ins=new HashMap<>(initSize);
				//12.ѭ����ǰ�и���
				for(int i=1;i<=count;i++)
				{
					//13.�������з��뵱ǰ�и���
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
	 *                       �����������װ����
	 *  һ��������,��Ҫִ�е�SQL���,һ���������                       
	 *********************************************************************************/
	/**
	 * ��������,�����������ķ�װ
	 * <
	 *    ��Ӧ����SQL
	 *    DELETE:
	 *           DELETE FROM TABLE WHERE ID=?
	 *    UPDATE:
	 *           UPDATE TABLE SET C1=?,C2=?,C3=?.....Cn=? WHERE ID=?
	 *    INSERT:
	 *           INSERT INTO TABLE(C1,C2,C3,C4......Cn)
	 *                      VALUES(?,?,?,?,?,?,?,.....)                 
	 * >
	 * @param sql    ---  ��Ҫִ�е�SQL���   
	 * @param args   ---  ����ֵ����
	 * @throws Exception
	 */
	protected final void appendSql(final String sql,final Object...args)throws Exception
	{
		//1.����SQL���
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.������ֵ
		int index=1;
		for(Object param:args)
		{
			pstm.setObject(index++, param);
		}
		//3.����PstmBeanʵ��
		PstmBean bean=new PstmBean(pstm);
		//4.��PstmBean���뵱ǰ�߳�����
		this.getPstmBeanList().add(bean);
	}
	
	/**
	 * ������������
	 * <
	 *    �ʺϵ�SQL��ʽ����:
	 *    DELETE FROM TABLE WHERE ID=?
	 *    
	 *    ��Ӧɾ����������
	 * >
	 * @param sql         --- SQL��� 
	 * @param args        --- ��������
	 * @return
	 * @throws Exception
	 */
	protected  final void appendBatchSql(final String sql,final Object...args)throws Exception
	{
		//1.����SQL���
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.�������������滻
		for(Object param:args)
		{
			pstm.setObject(1, param);
			pstm.addBatch();
		}
		//3.����PstmBean
		PstmBean bean=new PstmBean(pstm,true);
        //4.��PstmBean���뵱ǰ�߳�����
		this.getPstmBeanList().add(bean);
	}
	
	/**
	 * ������������
	 * <
	 *    �ʺϵ�SQL���:
	 *    UPDATE TABLE 
	 *       SET STATE=?
	 *     WHERE ID=?
	 *   
	 *    ��ӦSET�б�   ��һ�ֶ�ֵͬ  �޸����
	 *    ��һ״̬�����µ�������  
	 * >
	 * @param sql         --- SQL��� 
	 * @param newState    --- Ŀ��״̬  
	 * @param args        --- ��������
	 * @return
	 * @throws Exception
	 */
	protected final void appendBatchSql(final String sql,final Object newState,final Object...args)throws Exception
	{
		//1.����SQL���
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//�滻����������
		pstm.setObject(1, newState);
		//�滻��������
		for(Object param:args)
		{
			pstm.setObject(2, param);
			pstm.addBatch();
		}
		//3.����PstmBean
		PstmBean bean=new PstmBean(pstm,true);
        //4.��PstmBean���뵱ǰ�߳�����
		this.getPstmBeanList().add(bean);
	}
	
    /**
     * ������������
     * <
     *    UPDATE TABLE 
     *       SET C1=?,C2=?,C3=?.....Cn=?
     *     WHERE ID=?  
     * >
     * @param sql         ---  SQL���
     * @param paramList   ---  set�б�Ĳ�������
     * @param idlist      ---  ��������
     * @return
     * @throws Exception
     */
	protected final void appendBatchSql(final String sql,final Object[] paramList,final Object...idlist)throws Exception
	{
		//1.����SQL���
		PreparedStatement pstm=DBUtils.prepareStatement(sql);
		//2.1�����������ֵ--�滻����������
		int index=1;
		for(Object param:paramList)
		{
			pstm.setObject(index++, param);
		}
        //2.2�����������ֵ--�滻��������
		for(Object id:idlist)
		{
			pstm.setObject(index, id);
			pstm.addBatch();
		}
		//3.����PstmBean
		PstmBean bean=new PstmBean(pstm,true);
        //4.��PstmBean���뵱ǰ�߳�����
		this.getPstmBeanList().add(bean);
	}
	
	/**
	 * ��ȡ��ǰ�̰߳󶨵�װ��PstmBean������
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
	 * ִ������
	 * @return
	 * @throws Exception
	 */
	protected boolean executeTransaction()throws Exception
	{
		//�ռ���Ҫ���ٵ�Pstm����
		List<PreparedStatement> pstmList=null;
		try
		{
			//��ȡ�̰߳󶨵�װ��PstmBean������
			List<PstmBean> pstmBeanList=this.getPstmBeanList();
			
			//�жϵ�ǰ�߳��Ƿ������Ҫִ�е�SQL���
			if(pstmBeanList==null || pstmBeanList.size()==0)
			{
				throw new Exception("��ǰ���񲻴�����Ҫִ��SQL���......");
			}

			//����pstmBeanList�ĳ��ȹ���ȳ���pstmList
			pstmList=new ArrayList<>(pstmBeanList.size());

			//�������񷵻�ֵ
			boolean tag=false;
			//��������
			DBUtils.beginTransaction();
			try
			{
				//������Χ��ִ�е�ǰ�̰߳󶨵�����SQL������
				//ѭ������,��ȡPstmBean
				for(PstmBean bean:pstmBeanList)
				{
					if(bean.isBatch())  //�������Ҫ������
					{
						bean.getPstm().executeBatch();
					}
					else
					{
						bean.getPstm().executeUpdate();
					}	
					
					//��PstmBean�а�PreparedStatement�������pstmList
					pstmList.add(bean.getPstm());
				}
				//�ύ����
				DBUtils.commit();
				//�޸ķ���ֵ
				tag=true;

			}
			catch(Exception ex)
			{
				//����ع�
				DBUtils.rollback();
				ex.printStackTrace();
			}
			finally
			{
				//��������
				DBUtils.endTransaction();
			}
			return tag;
		}
		finally
		{
			//������������е�����������
			if(pstmList!=null)
			{
				for(PreparedStatement pstm:pstmList)
				{
					DBUtils.close(pstm);
				}
			}
			//����̰߳�
			this.threadBeanList.remove();
		}
	}
	
	

	
	
	
	/*********************************************************************************
	 *                       ��һ�������������װ
	 *********************************************************************************/
    /**
     * ��һ��������
     * <
     *    UPDATE TABLE 
     *       SET C1=?,C2=?,C3=?.....Cn=?
     *     WHERE ID=?  
     * >
     * @param sql         ---  SQL���
     * @param paramList   ---  set�б�Ĳ�������
     * @param idlist      ---  ��������
     * @return
     * @throws Exception
     */
	protected final boolean batchUpdate(final String sql,final Object[] paramList,final Object...idlist)throws Exception
	{
	     //1.����JDBC����
		 PreparedStatement pstm=null;
		 try
		 {
			 //2.����SQL���
			 pstm=DBUtils.prepareStatement(sql);
			 //3.������ֵ
			 //�滻����������
			 int index=1;
			 for(Object param:paramList)
			 {
				 pstm.setObject(index++, param);   //3
			 }
			 //�滻����ֵ
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
	 * ��һ��������
	 * <
	 *    �ʺϵ�SQL���:
	 *    UPDATE TABLE 
	 *       SET STATE=?
	 *     WHERE ID=?
	 *   
	 *    ��ӦSET�б�   ��һ�ֶ�ֵͬ  �޸����
	 *    ��һ״̬�����µ�������  
	 * >
	 * @param sql         --- SQL��� 
	 * @param newState    --- Ŀ��״̬  
	 * @param args        --- ��������
	 * @return
	 * @throws Exception
	 */
	protected final boolean batchUpdate(final String sql,final Object newState,final Object...args)throws Exception
	{
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		try
		{
			//2.����SQL
			pstm=DBUtils.prepareStatement(sql);
			//�����������滻
			pstm.setObject(1, newState);
			//�滻��������
			for(Object id:args)
			{
				pstm.setObject(2, id);
				pstm.addBatch();
			}
			//������ʽִ��SQL
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
	 * ��һ��������
	 * <
	 *    �ʺϵ�SQL��ʽ����:
	 *    DELETE FROM TABLE WHERE ID=?
	 *    
	 *    ��Ӧɾ����������
	 * >
	 * @param sql         --- SQL��� 
	 * @param args        --- ��������
	 * @return
	 * @throws Exception
	 */
	protected  final boolean batchUpdate(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		try
		{
            
			//2.����SQL
			pstm=DBUtils.prepareStatement(sql);
			//3.�����������ֵ
			for(Object id:args)
			{
				pstm.setObject(1, id);
				pstm.addBatch();
			}
			
			/**
			 * ������ʽִ��
			 */
			//1.�������񷵻�ֵ
			boolean tag=false;
			//2.��������
			DBUtils.beginTransaction();
			try
			{
				//3.������ʽִ��SQL���
				pstm.executeBatch();
				//4.1.�ύ����
				DBUtils.commit();
				//5.�޸ķ���ֵ
				tag=true;
			}
			catch(Exception ex)
			{
				//4.2.����ع�
				DBUtils.rollback();
				ex.printStackTrace();
			}
			finally
			{
				//��������
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
	 *                       ������һ����·���
	 *********************************************************************************/
	
	/**
	 * ���ݸ��·���
	 * <
	 *   �����������,ִ�и��� ���
	 *   �����յ���SQL�����������,Ȼ��args�еĲ�����ֵ��pstm
	 *   Ȼ��ִ��
	 * >
	 * @param sql    ---  ���յ���SQL���
	 * @param args   ---  ��������
	 * @return
	 * @throws Exception
	 */
	protected  final boolean executeUpdate(final String sql,final Object...args)throws Exception
	{
		//1.����JDBC�ӿ�
		PreparedStatement pstm=null;
		try
		{
			//2.����SQL���
			pstm=DBUtils.prepareStatement(sql);
			//3.��ɲ�����ֵ
			int index=1;
			for(Object element:args)
			{
				pstm.setObject(index++, element);
			}
			//4.ִ��SQL���
			return pstm.executeUpdate()>0;
		}
		finally
		{
			DBUtils.close(pstm);
		}
	}


	/*********************************************************************************
	 *                       PreparedStatement ����������---�ڲ���ʵ��
	 *********************************************************************************/

	private final class  PstmBean 
	{
		//��Ҫִ�е�SQL����Ӧ��������
		private PreparedStatement pstm=null;
		//�������Ժ��ַ�ʽִ��  true---pstm.executeBatch()  false-----pstm.executeUpdate();
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
		 * ��ȡBean�а󶨵�PreparedStatement
		 * @return
		 */
		public PreparedStatement getPstm() {
			return this.pstm;
		}
		
		/**
		 * ��ȡPreparedStatement��ִ�з�ʽ
		 * @return
		 */
		public boolean isBatch() {
			return this.isBatch;
		}
	}

}

