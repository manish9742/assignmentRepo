package com.demo.master;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assingment.entity.Deal;

@Repository
public class MasterDaoTemplateImpl<E, PK extends Serializable> implements MasterDaoTemplate<E, PK> {

	@Autowired
	JdbcTemplate mysqlJdbctemplate;

	public void setDataSource(DataSource dataSource) {
		this.mysqlJdbctemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	@Override
	public void insertBatchCollection(final List<Deal> lst) {

		mysqlJdbctemplate.execute(getQuery(lst));

	}

	public static String getQuery(final List<Deal> lst) {

		StringBuffer mySql = new StringBuffer(
				"INSERT INTO  deal (DEAL_ID, FROM_CURRENCY_ISO_CODE, TO_CURRENCY_ISO_CODE, TIME_STAMP,AMOUT_ORDERING_CURRENCY,FILE_ID) VALUES ");
		for (int j = 0; j < lst.size(); j++) {
			mySql = mySql.append("(").append(lst.get(j).getDeal_id()).append(",'")
					.append(lst.get(j).getFrom_currency_iso_code()).append("','")
					.append(lst.get(j).getTo_currency_iso_code()).append("',").append(lst.get(j).getTime_stamp())
					.append(",").append(lst.get(j).getAmount_ordering_currency()).append(",'")
					.append(lst.get(j).getfile_id()).append("' ),");
		}

		return mySql.substring(0, mySql.length() - 1);
	}

	@Override
	public boolean checkExistData(String query) {
		return mysqlJdbctemplate.queryForObject(query, Integer.class) == 1 ? true : false;

	}

	@Transactional
	@Override
	public void deleteByQuery(String query) {
		mysqlJdbctemplate.execute(query);

	}

	@Override
	public List<Map<String, Object>> selectAll(String query) {
		return mysqlJdbctemplate.queryForList(query);
	}

	@Override
	public void insterByQuery(String sql) {
		mysqlJdbctemplate.execute(sql);

	}

	@Override
	public Map<String, Long> getAccumulativeCountList() {
		Map<String, Long> returnMap = new HashMap<>();
		List<Map<String, Object>> dbList = mysqlJdbctemplate
				.queryForList("select Ordering_Currency,Ordering_Currency_COUNT from deal_accumulative_count");
		for (Map map : dbList) {
			returnMap.put((String) map.get("Ordering_Currency"), (Long) map.get("Ordering_Currency_COUNT"));
		}
		return returnMap;
	}

	@Override
	public List<String> getListForCount() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void insertAccumulativeCount(List<String> cumulavtivCountList) {
		System.out.println("***");

		List<String> validList = mysqlJdbctemplate.queryForList("select FROM_CURRENCY_ISO_CODE from valid_deal",
				String.class);
		List<String> invalid_lst = mysqlJdbctemplate.queryForList("select FROM_CURRENCY_ISO_CODE from invalid_deal",
				String.class);
		cumulavtivCountList.addAll(validList);
		cumulavtivCountList.addAll(invalid_lst);

		deletAccumulativeCountData();

		String querys = AccumulativeCountgetQuery(cumulavtivCountList);
		mysqlJdbctemplate.execute(querys);

	}

	public static String AccumulativeCountgetQuery(List<String> cumulavtivCountList) {

		Map<String, Long> countMap = cumulavtivCountList.stream()
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		StringBuffer mySql = new StringBuffer(
				"INSERT INTO  deal_accumulative_count (Ordering_Currency, Ordering_Currency_COUNT) VALUES ");
		for (Map.Entry<String, Long> map : countMap.entrySet()) {

			mySql = mySql.append("('").append(map.getKey()).append("',").append(map.getValue()).append("),");
		}

		return mySql.substring(0, mySql.length() - 1);
	}

	@Override
	public boolean checkExistFiel(String fileName) {
		StringBuffer mySql = new StringBuffer("select COUNT(FILE_NAME) from deal_file where FILE_NAME=").append("'")
				.append(fileName).append("'");
		return mysqlJdbctemplate.queryForObject(mySql.toString(), Integer.class) == 1 ? true : false;
	}

	@Override
	public void deletAccumulativeCountData() {
		mysqlJdbctemplate.execute("DELETE FROM  deal_accumulative_count");

	}

	@Override
	public List<E> selectAllData(String query) {
		return (List<E>) mysqlJdbctemplate.queryForList(query);

	}

	@Override
	public int selectCount(String query) {
		return mysqlJdbctemplate.queryForObject(query.toString(), Integer.class);
	}

	 

}
