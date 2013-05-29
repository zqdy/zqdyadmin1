package com.zqdy.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.zqdy.core.common.dto.CommonDto;
import com.zqdy.dao.po.TUser;

public interface CommonDao<C> {
	
	public TUser getUserByUserCodeAndPassword(String userCode, String password);
	
	/**
	 *  得到所有地区
	 * @return
	 */
	public List getAllArea();
	
	/**
	 *  得到所有演员
	 * @return
	 */
	public List getAllActor();
	
	/**
	 *  得到所有导演
	 * @return
	 */
	public List getAllDirector();
	
	/**
	 * 得到电影/电视剧类型
	 * @param type 0 电影，1电视视剧
	 * @return
	 */
	public List getAllGenre();
	
	
	/**
	 * 批量创建
	 */
	public List<C> createBatch(List<C> objs) ;
	
	/**
	 * 执行一条HQL语句(DELETE,UPDATE...)
	 * 
	 * @param query HQL 语句
	 * @return 返回影响记录数

	 */
	public int executeQuery(String query);
	
	/**
	 * 通过 SQL 进行删除
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int deleteBySQL(String sql) throws SQLException;
	
	/**
	 * 批量删除
	 * 
	 * @param delSql 删除语句
	 * @return
	 */
	public int deleteBatch(final String delSql);
	
	/**
	 * 批量创建
	 */
	public List<C> createOrUpdateBatch(List<C> objs) ;
	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return
	 */

	public C create(C obj);
	
	/**
	 * 批量创建
	 */
	public Set<C> createBatch(Set<C> objs);
	
	
	/**
	 * 批量更新
	 */
	public List<C> updateBatch(List<C> objs);
	
	
	
	/**
	 * 传入一个对象,保存或更新该对象
	 * 
	 * @param obj
	 * @return
	 */
	public C saveOrUpdate(C obj) ;
	
	
	/**
	 * 更新对象
	 * 
	 * @param obj
	 * @return
	 */
	public C update(C obj) ;
	
	/**
	 * 删除传入的对象
	 * 
	 * @param obj 要删除的对象
	 */
	public void delete(C obj) ;
	
	/**
	 * 批量删除一个List里的全部对象
	 * 
	 * @param objs 要删除的对象列表
	 */
	public void deleteBatch(List<C> objs) ;
	
	/**
	 * 列表所有的对象
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<C> findAll(Class cls) ;
	
	/**
	 * 根据一条HQL语句进行查询,查询出来的结果一般不是一个DTO对象.
	 * 
	 * @param hql HQL语句
	 * @return 对象数组
	 */
	public List<Object[]> findByHQL(String hql);
	
	public List<Object[]> findByHQL(String hql, Object... values) ;
	
	public <T> List<T> find(Class<T> clazz, String query, Object... os) ;
	
	public Object uniqueFind(String hql );
	
	public int executeQuery(String query, Object... os) ;
	
	public List findBySQL(final String sql) ;
	
	/**
	 * 分页得到数据
	 * 
	 * @param pageSize
	 * @param pageNow
	 * @param condition
	 * @return
	 */
	public CommonDto getResult(int pageSize, int pageNow, String poClass,
			String condition);
	/**
	 * 根据HQL和查询条数
	 * @param hql
	 * @param count
	 * @return
	 */
	public List getListByHqlAndCount(String hql,int count);
	/**
	 * 执行储过程
	 * @param PROCEDURE_NAME
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean executePROCEDURE(String PROCEDURE_NAME,String param) throws Exception ;
	
	/**
	 * 调用查询电影存储过程
	 * @param year
	 * @param genre
	 * @param area
	 * @param source
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws Exception
	 */
	public CommonDto executeMovieProcedure(String year,Integer genre,Integer area,Integer source,
			int pageSize, int pageNow)throws Exception;
	
	/**
	 * 调用分页查询电视剧存储过程
	 * @param year
	 * @param genre
	 * @param area
	 * @param source
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws Exception
	 */
	public CommonDto executeTvProcedure(String year,Integer genre,Integer area,Integer source,
			int pageSize, int pageNow)throws Exception;
	
	public void flush();

}
