package com.zqdy.core.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.Subcriteria;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.core.common.util.CollectionUtils;





/**
 * 抽象的DAO实现类. <br>
 * 泛型C指的是要实现哪个类的DAO.一般为DTO,PK 代表ID的类型<br>
 * 要实现的方法getPersistentClass(). 返回DTO对象的class <BR>
 * <BR>
 * 用法 : <BR>
 * <code>
 *  // 具体每张表的DAO 接口 <BR>
 * public interface XXIDAO extends BaseIDAO&lt;XXDTO, Long&gt;  <BR>
 *   //具体每张表的DAO实现 <BR>
 * public class XXDAO extends BaseHibernateDaoSupport&lt;XXDTO, Long&gt; implements XXIDAO 
 * </code>
 * 
 * @author 

 */
public abstract class BaseHibernateDaoSupport<C, PK extends Serializable>
		extends HibernateDaoSupport {

	/**
	 * 执行一条HQL语句(DELETE,UPDATE...)
	 * 
	 * @param query HQL 语句
	 * @return 返回影响记录数

	 */
	public int executeQuery(String query) {
		return this.executeQuery(query, (Object[]) null);
	}

	/**
	 * 通过 SQL 进行删除
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int deleteBySQL(String sql) throws SQLException {
		Connection connection = getSession().connection();
		 
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.execute();
		ResultSet reSet = pStatement.getResultSet();
		if (reSet!=null&&reSet.next())
			return reSet.getInt(0);
		return 0;
	}

	

	

	/**
	 * 根据ID删除一个对象,返回影响的记录数.
	 * 
	 * @param id
	 * @return
	 */
	public int delete(PK id,String className) {
		String str = "DELETE FROM {0} o WHERE o.{1} = ?";
		String delSQL = MessageFormat.format(str, className, id);

		return this.executeQuery(delSQL, id);
	}

	/**
	 * 根据ID在列表中的所有记录,返回已删除的记录数.
	 * 
	 * @param ids ID的数组

	 * @return 已删除的记录数

	 */
	public int deleteBatch(final PK[] ids,String className) {
		return this.deleteBatch(ids, null,className);
	}

	/**
	 * 删除ID在列表中加上where判断语句的所有记录

	 * 
	 * @param ids 要删除的ID数组
	 * @param whereQuery where判断语句
	 * @return 已删除的记录数

	 */
	public int deleteBatch(final PK[] ids, String whereQuery,String className) {

//		if (CollectionUtils.empty(ids)) {
//			return 0;
//		}

		String str = "DELETE FROM {0}  WHERE {1} IN (:ids)  {2} ";

		if (whereQuery == null) {
			whereQuery = "";
		} else {
			// 如果WHERE语句不是由AND字符串开始的
			if (!whereQuery.trim().toUpperCase().startsWith("AND")) {
				whereQuery += " AND ";
			}
		}

		final String delSQL = MessageFormat.format(str, className, ids, whereQuery);

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(delSQL);
						query.setParameterList("ids", ids);
						return query.executeUpdate();
					}
				});
	}

	/**
	 * 批量删除
	 * 
	 * @param delSql 删除语句
	 * @return
	 */
	public int deleteBatch(final String delSql) {
		return executeQuery(delSql);
	}

	/**
	 * 批量创建
	 */
	public List<C> createBatch(List<C> objs) {
		List<C> lst = new ArrayList<C>();
		if (CollectionUtils.notEmpty(objs)) {
			C o = null;
			for (C obj : objs) {
				o = this.create(obj);
				if (o != null) {
					lst.add(o);
				}
			}
		}
		return lst;
	}
	/**
	 * 批量创建
	 */
	public List<C> createOrUpdateBatch(List<C> objs) {
		List<C> lst = new ArrayList<C>();
		if (CollectionUtils.notEmpty(objs)) {
			C o = null;
			for (C obj : objs) {
				if(obj!=null){
					o = this.saveOrUpdate(obj);
					if (o != null) {
						lst.add(o);
					}
				}
			}
		}
		return lst;
	}

	/**
	 * 批量创建
	 */
	public Set<C> createBatch(Set<C> objs) {
		Set<C> set = new HashSet<C>();
		if (CollectionUtils.notEmpty(objs)) {
			C o = null;
			for (C obj : objs) {
				o = this.create(obj);
				if (o != null) {
					set.add(o);
				}
			}
		}
		return set;
	}

	/**
	 * 批量更新
	 */
	public List<C> updateBatch(List<C> objs) {
		List<C> lst = new ArrayList<C>();
		if (CollectionUtils.notEmpty(objs)) {
			C o = null;
			for (C obj : objs) {
				o = this.update(obj);
				if (o != null) {
					lst.add(o);
				}
			}
		}
		return lst;
	}

	/**
	 * 传入一个对象,保存或更新该对象
	 * 
	 * @param obj
	 * @return
	 */
	public C saveOrUpdate(C obj) {
		
		getHibernateTemplate().saveOrUpdate(obj);
	 
		return obj;
	}

	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return
	 */

	public C create(C obj) {
		getHibernateTemplate().save(obj);
		 
		return obj;
	}
	
	public void flush(){
		getHibernateTemplate().flush();
	}

	/**
	 * 更新对象
	 * 
	 * @param obj
	 * @return
	 */
	public C update(C obj) {
		//getHibernateTemplate().update(obj);
		getHibernateTemplate().merge(obj);
		return obj;
	}

	/**
	 * 删除传入的对象
	 * 
	 * @param obj 要删除的对象
	 */
	public void delete(C obj) {
		if (obj != null) {
			getHibernateTemplate().delete(obj);
		}
	}

	/**
	 * 批量删除一个List里的全部对象
	 * 
	 * @param objs 要删除的对象列表
	 */
	public void deleteBatch(List<C> objs) {
		if (CollectionUtils.notEmpty(objs)) {
			getHibernateTemplate().deleteAll(objs);
		}
	}

	/**
	 * 列表所有的对象
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<C> findAll(String className) {
		List lst = getHibernateTemplate().find(
				"FROM " + className + " o ");
		return (List<C>) lst;
	}

	/**
	 * 列表所有的对象
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<C> findAll(Class cls) {
		List lst = getHibernateTemplate().find(
				"FROM " + cls.getName() + " o ");
		return (List<C>) lst;
	}
//	/**
//	 * 根据HQL和参数查找 <code>
//	 * 	find("FROM ClassName o WHERE o.name = ? AND o.age = ?","ji" , "11");
//	 * </code>
//	 * 
//	 * @param query
//	 * @param os HQL中的参数,对应HQL中的问号
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<C> find(String query, Object... os) {
//		return this.find(0, query, os);
//	}

//	/**
//	 * 根据HQL和参数查找 <br>
//	 * <code>
//	 * 	find(100,"FROM ClassName o WHERE o.name = ? AND o.age = ?","ji" , "11");
//	 * </code>
//	 * 
//	 * @param maxResult 搜索的最大记录
//
//	 * @param query HQL语句
//	 * @param os HQL中的参数,对应HQL中的问号
//	 * @return
//	 */
//	public List<C> find(int maxResult, String query, Object... os) {
//		return find(maxResult, getPersistentClass(), query, os);
//	}
//
//	/**
//	 * 根据HQL查找
//	 * 
//	 * @param query HQL语句
//	 */
//	@SuppressWarnings("unchecked")
//	public List<C> find(String query) {
//		return this.find(query, (Object[]) null);
//	}

	/**
	 * 根据HQL唯一查找,查找到多个对象只返回第一个,如果查找不到返回null
	 * 
	 * @param class <T> 要返回的对象的类型

	 * @param query
	 * @param os SQL语句中的参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	 
	public <T> T uniqueFind(Class<T> clazz, String query, Object... os) {
		List lst = null;
		HibernateTemplate ht = getHibernateTemplate();
		ht.setMaxResults(1);
		if (os == null) {
			lst = ht.find(query);
		} else {
			lst = ht.find(query, os);
		}
		ht.setMaxResults(0);
		if (CollectionUtils.empty(lst)) {
			return null;
		}

		return (T) lst.get(0);
	}

	public Object uniqueFind(String hql ){
		 
		this.getHibernateTemplate().setAllowCreate(true);
		List list = this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		 
	}

//	/**
//	 * 根据HQL唯一查找,查找到多个对象只返回第一个,如果查找不到返回null
//	 * 
//	 * @param query
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public C uniqueFind(String query) {
//		return this.uniqueFind(query, (Object[]) null);
//	}

	/**
	 * 执行一条HQL语句(DELETE,UPDATE...)
	 * 
	 * @param query HQL语句
	 * @param os
	 * @return 返回影响记录数

	 */
	public int executeQuery(String query, Object... os) {
		if (os == null) {
			return getHibernateTemplate().bulkUpdate(query);
		} else {
			return getHibernateTemplate().bulkUpdate(query, os);
		}
	}

//	/**
//	 * 根据对象的属性和值查找
//
//	 * 
//	 * @param propName 属性名
//	 * @param value 属性值
//
//	 * @return
//	 */
//	public List<C> findByProp(String propName, Object value) {
//		String hql = "FROM {0} o WHERE o.{1} = ?";
//		// 把{0},{1}替换掉
//
//
//		hql = MessageFormat.format(hql, getPersistentClass().getName(),
//				propName);
//		return this.find(hql, value);
//	}

//	
//	/**
//	 * 根据ID查找某一个BasePO的对象
//
//	 * 
//	 * @param pk
//	 * @para po 返回的DTO
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public <T extends BasePO> T findById(Class<T> po, Serializable pk) {
//		if (pk == null || po == null) {
//			return null;
//		}
//
//		T o = (T) getHibernateTemplate().get(po, pk);
//		if (o == null) {
//			return null;
//		}
//		return o;
//	}

	/**
	 * 传入一条select count(*)的HQL语句,返回记录数

	 * 
	 * @param query
	 * @param values HQL语句的参数

	 * @return 记录数

	 */
	public int countQuery(String query, Object... values) {
		Object ret = null;
		if (values == null) {
			ret = getHibernateTemplate().find(query).get(0);
		} else {
			ret = getHibernateTemplate().find(query, values).get(0);
		}
		if (ret instanceof Number) {
			return ((Number) ret).intValue();
		}
		return (Integer) ret;
	}

	/**
	 * 传入一条select count(*)的SQL语句,返回记录数

	 * 
	 * @param query
	 * @return 记录数

	 */
	public int countQuery(String query) {
		return this.countQuery(query, (Object[]) null);
	}

	
	/**
	 * 根据HQL和参数查找,参数可以是Collection或数组

	 * 
	 * @param query HQL语句
	 * @param paramNames 参数的名字

	 * @param values 值

	 * @return 结果列表
	 */
	public List findByNamedParam(String query, String paramName, Object value) {
		return getHibernateTemplate().findByNamedParam(query, paramName, value);
	}

	/**
	 * 根据HQL和参数查找,参数可以是Collection或数组<BR>
	 * 参数个数要与值的个数相等
	 * 
	 * @param query HQL语句
	 * @param paramNames 参数的名字(数组)
	 * @param values 值(数组)
	 * @return 结果列表
	 */
	public List findByNamedParam(String query, String[] paramNames,
			Object[] values) {
		return getHibernateTemplate().findByNamedParam(query, paramNames,
				values);
	}

	

	
	

	private Criteria getSubCriteria(Criteria parent, String tableName) {
		Criteria tmpCrit = null;
		if (parent instanceof Subcriteria) {
			tmpCrit = ((Subcriteria) parent).getParent();
		} else {
			tmpCrit = parent;
		}
		Iterator iterateSubcriteria = ((CriteriaImpl) tmpCrit)
				.iterateSubcriteria();
		while (iterateSubcriteria.hasNext()) {
			Subcriteria impl = (Subcriteria) iterateSubcriteria.next();
			String path = impl.getPath();
			if (path.equals(tableName)) {
				return impl;
			}
		}
		return null;
	}

	

	/**
	 * 执行DELETE 和 UDPATE 的HQL语句
	 * 
	 * @param queryString HQL 语句 ,paramNames和values记录数要一样

	 * @param paramNames 传入的参数

	 * @param values 传入的值

	 * @return 影响的记录数
	 */
	public int bulkUpdateByNamedParam(final String queryString,
			final String[] paramNames, final Object[] values) {
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException(
					"paramNames and values length must be same!");
		}

		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					Object v = null;

					public Object doInHibernate(Session s)
							throws HibernateException {
						Query q = s.createQuery(queryString);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								v = values[i];
								if (v instanceof Collection) {
									q.setParameterList(paramNames[i],
											(Collection) v);
								} else if (v instanceof Object[]) {
									q.setParameterList(paramNames[i],
											(Object[]) v);
								} else {
									q.setParameter(paramNames[i], v);
								}
							}
						}
						return new Integer(q.executeUpdate());
					}
				}, true);
		return count.intValue();
	}

	/**
	 * 执行DELETE 和 UDPATE 的HQL语句
	 * 
	 * @param queryString HQL 语句
	 * @param paramNames 传入的参数

	 * @param values 传入的值

	 * @return 影响的记录数
	 */
	public int bulkUpdateByNamedParam(final String queryString,
			final String paramNames, final Object values) {
		return this.bulkUpdateByNamedParam(queryString,
				new String[] { paramNames }, new Object[] { values });
	}

	/**
	 * 根据SQL语句进行查找
	 * 
	 * @param sql SQL语句
	 * @return 数据的列表

	 */
	public List findBySQL(final String sql) {
		if (sql == null) {
			return null;
		}

		List list = (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sql).list();
					}
				});

		return list;
	}

	

	/**
	 * 根据一条HQL语句进行查询,查询出来的结果一般不是一个DTO对象.
	 * 
	 * @param hql HQL语句
	 * @return 对象数组
	 */
	public List<Object[]> findByHQL(String hql) {
		return this.findByHQL(hql, (Object[]) null);
	}
	 
	/**
	 * 根据一条HQL语句进行查询,查询出来的结果一般不是一个DTO对象.
	 * 
	 * @param hql HQL语句
	 * @param values 参数,对应HQL语句中的?
	 * @return 对象数组
	 */
	public List<Object[]> findByHQL(String hql, Object... values) {
		return this.find(Object[].class, hql, values);
	}

	/**
	 * 根据一条HQL语句进行查询,查询出来的结果一般不是一个DTO对象.
	 * 
	 * @param hql HQL语句
	 * @return 对象数组
	 */
	@SuppressWarnings("unchecked")
	public Object[] uniqueFindByHQL(String hql) {
		return uniqueFindByHQL(hql, (Object[]) null);
	}

	/**
	 * 根据一条HQL语句进行查询,查询出来的结果一般不是一个DTO对象.
	 * 
	 * @param hql HQL语句
	 * @param values 参数,对应HQL语句中的?
	 * @return 对象数组
	 */
	@SuppressWarnings("unchecked")
	public Object[] uniqueFindByHQL(String hql, Object... values) {
		return uniqueFind(Object[].class, hql, values);
	}

//	/**
//	 * 创建(增加)的时候判断某一属性是否存在
//
//	 * 
//	 * @param propName 属性名
//	 * @param propValue 属性值
//
//	 * @return 返回true为存在,否则为不存在
//	 */
//	public boolean isExistByPropForCreate(String propName, Object propValue) {
//		return CollectionUtils.notEmpty(this.findByProp(propName, propValue));
//	}

	
	/**
	 * 根据HQL和参数查找 <br>
	 * <code>
	 * find(
	 * 		User.class,
	 * 		"SELECT new com.test.User(o.name,o.age) FROM ClassName o WHERE o.name = ? AND o.age = ?",
	 * 		"chenxingji", "11");
	 * </code>
	 * 
	 * @param <T>
	 *            返回列表对象里放的类别
	 * 
	 * @param query
	 *            HQL语句
	 * @param os
	 *            HQL中的参数,对应HQL中的问号
	 * @return 泛型为T的列表
	 * 
	 */
	public <T> List<T> find(Class<T> clazz, String query, Object... os) {
		return this.find(0, clazz, query, os);
	}

	/**
	 * 根据HQL和参数查找 <br>
	 * <code>
	 * find(
	 * 		User.class,
	 * 		"SELECT new com.test.User(o.name,o.age) FROM ClassName o WHERE o.name = 'chenxingji' AND o.age = 11");
	 * </code>
	 * 
	 * @param <T>
	 *            返回列表对象里放的类别
	 * 
	 * @param maxResult
	 *            搜索的最大记录
	 * 
	 * @param query
	 *            HQL语句
	 * @return 泛型为T的列表
	 * 
	 */
	public <T> List<T> find(Class<T> clazz, String query) {
		return this.find(clazz, query, (Object[]) null);
	}

	/**
	 * 根据HQL和参数查找 <br>
	 * <code>
	 * find(
	 * 		100,
	 * 		User.class,
	 * 		"SELECT new com.test.User(o.name,o.age) FROM ClassName o WHERE o.name = ? AND o.age = ?",
	 * 		"chenxingji", "11");
	 * </code>
	 * 
	 * @param <T>
	 *            返回列表对象里放的类别
	 * 
	 * @param maxResult
	 *            搜索的最大记录
	 * 
	 * @param query
	 *            HQL语句
	 * @param os
	 *            HQL中的参数,对应HQL中的问号
	 * @return 泛型为T的列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(int maxResult, Class<T> clazz, String query,
			Object... os) {
		List lst = null;
		HibernateTemplate ht = getHibernateTemplate();
		ht.setMaxResults(0);
		if (maxResult > 0) {
			ht.setMaxResults(maxResult);
		}

		if (os == null) {
			lst = ht.find(query);
		} else {
			lst = ht.find(query, os);
		}

		ht.setMaxResults(0);
		if (CollectionUtils.empty(lst)) {
			return new ArrayList<T>();
		}

		return (List<T>) lst;
	}
	
	/**
	 * 根据对象的属性和值查找
	 * 
	 * 
	 * @param propName
	 *            属性名
	 * @param value
	 *            属性值
	 * 
	 * @return  true 存在；false 不存在
	 */
	public boolean isExistByPropForCreate(String className,String propName, Object value) {
		String hql = "FROM {0} o WHERE o.{1} = {2}";
		// 把{0},{1}替换掉

		hql = MessageFormat.format(hql, className, propName,value);
		List list =  this.findByHQL(hql);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	 
	/**
	 * 执行储过程
	 * @param PROCEDURE_NAME
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean executePROCEDURE(String PROCEDURE_NAME,String param) throws Exception 
	{
		boolean success = true;
		try
		{
			Session session = this.getSession();			
			Connection con = session.connection();
			String procedure = "{call "+PROCEDURE_NAME+"(?) }"; 
			CallableStatement cstmt = con.prepareCall(procedure);
			cstmt.setString(1, param); //设置参数			
			cstmt.executeUpdate();			
		}
		catch (Exception e) {
			//BaseDBException ex = new BaseDBException(e.toString());
			success = false;
			//throw ex;
		}
		return success;
		
	}
	
	
	
	/**
	 * 分页得到数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition) {

		CommonDto commonDto = new CommonDto();
		int pageCount=0;
		int totalRow=0;
		if(pageNow<0){
			pageNow=0;
		}
		String hql = " from " +poClass+" " +condition;
		String hqlTotalRow = "select count(*) from "+poClass+" " +condition;
        //todo
		List result = this.getSession().createQuery(hql).setFirstResult(pageNow*pageSize).setMaxResults(pageSize).list();
		List totlRowList = this.getHibernateTemplate().find(hqlTotalRow);
		if(totlRowList.size()>0){
			Long total = (Long)totlRowList.get(0);
			totalRow = total.intValue();
			if(totalRow>0){
				pageCount =(totalRow-1)/pageSize+1;

			}else{
				pageCount=0;
			}
		}
		commonDto.setProperty("result", result);      //查询结果
		commonDto.setProperty("pageCount", pageCount);//总页数
		commonDto.setProperty("totalRow", totalRow);//总行数
		return commonDto;
        
    }
	
	/**
	 * 根据HQL和查询条数
	 * @param hql
	 * @param count
	 * @return
	 */
	public List getListByHqlAndCount(String hql,int count){
		List result = this.getSession().createQuery(hql).setFirstResult(0).setMaxResults(count).list();
		return result;
	}
	
}
