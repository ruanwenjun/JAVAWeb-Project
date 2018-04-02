package cn.ruanwenjun.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.utils.DataSourceUtils;

public class UserDao {

	public Boolean register(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = runner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
					user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return update>0?true:false;
	}

	public int activeUser(String activeCode) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set state=1 where code=? ";
		int update = runner.update(sql);
		return update;
	}

	public Boolean isExist(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(),username);
		return query>0?true:false;
	}

	public User isRight(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		return runner.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
	}

}
