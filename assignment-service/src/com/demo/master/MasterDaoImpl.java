package com.demo.master;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assingment.entity.Deal;
import com.assingment.entity.DealFile;
 

@Repository
public class MasterDaoImpl<E, PK extends Serializable> implements MasterDao<E, PK> {

	 
	
	@Autowired
	private SessionFactory sessionFactory;
	
     
    @SuppressWarnings("unchecked")
	@Override
	public PK saveData(E entity) {
		return (PK) getSessionFactory().getCurrentSession().save(entity);
	}

 
	@Override
	public String getMessageDisp(String messageKey) {
		return messageKey;
	}

	@Override
	public void inserData(E e) {
		  getSessionFactory().getCurrentSession().save(e);
	}

	@Override
	public List<E> selectDataFromDB(String sqlQuery, E e) {
		//List<E> results = (List<E>) mysqlJdbctemplate.queryForList(sqlQuery, e.getClass());
		return null;
		
		}

	@Override
	public List<E> selectAll(String sqlQuery) {
		
		//SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
		// query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		 //return  query.list();
		
		org.hibernate.Query  query =sessionFactory.getCurrentSession().createQuery("From DEAL_FILE");
		return query.list();
	}

	@Override
	public List<Map<String, Object>> getQueryData(String sql) {
		//List<Map<String, Object>> rows = mysqlJdbctemplate.queryForList(sql);
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public void insertBatchCollection(String str,List<Deal> deals) {
		 SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(getQuery(str,deals));
		 query.executeUpdate();
	}
	 
	
	public static String getQuery(String table,final List<Deal> lst) {

		StringBuffer mySql = new StringBuffer("INSERT INTO ").append(table).append(" (DEAL_ID, FROM_CURRENCY_ISO_CODE, TO_CURRENCY_ISO_CODE, TIME_STAMP,AMOUT_ORDERING_CURRENCY,FILE_ID) VALUES ");
		for (int j = 0; j < lst.size(); j++) {
			mySql = mySql.append("(").append(lst.get(j).getDeal_id()).append(",'")
					.append(lst.get(j).getFrom_currency_iso_code()).append("','")
					.append(lst.get(j).getTo_currency_iso_code()).append("',").append(lst.get(j).getTime_stamp())
					.append(",").append(lst.get(j).getAmount_ordering_currency()).append(",'")
					.append(lst.get(j).getfile_id()).append("' ),");
		}

		return mySql.substring(0, mySql.length() - 1);//replaceAll("null", "''");
	}
	
}
