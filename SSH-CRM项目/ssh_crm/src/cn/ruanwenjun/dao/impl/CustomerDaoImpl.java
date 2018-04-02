package cn.ruanwenjun.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import cn.ruanwenjun.dao.CustomerDao;
import cn.ruanwenjun.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	public List<Object[]> countIndustry() {
		//使用原生sql查询
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				String sql = "select COUNT(*),dict_item_name "
							+ " FROM cust_customer,base_dict "
							+ " WHERE cust_industry = dict_id "
							+ " GROUP BY cust_industry ";
				SQLQuery query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				return list;
			}
			
		});
			
		return list;
	}

	
}
