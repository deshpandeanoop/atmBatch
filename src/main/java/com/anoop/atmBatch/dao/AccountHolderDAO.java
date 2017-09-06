package com.anoop.atmBatch.dao;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.anoop.atmBatch.bean.AccountHolder;
import com.anoop.atmBatch.idao.IDao;
import com.anoop.atmBatch.util.Constants;

/**
 * 
 * @author 532740
 * Stores the list to the database
 */
public class AccountHolderDAO implements IDao<AccountHolder>{
private JdbcTemplate jdbcTemplate;
private String query;

public AccountHolderDAO(JdbcTemplate jdbcTemplate) {
	super();
	this.jdbcTemplate = jdbcTemplate;
}
/**
 * Call back function, invokes requested Database Operation using reflection
 * 
 */
public  void invoke(final String operationType, final List<AccountHolder> accountHolderList) throws Exception
{
	Method method = AccountHolderDAO.class.getDeclaredMethod(operationType, List.class);
	method.invoke(this, accountHolderList);
}
/**
 * Inserts new account holder information to database
 */
@SuppressWarnings("unused")
private void insert(final List<AccountHolder> accountHolderList) 
{
	if(this.jdbcTemplate!=null)
	{
		for(final AccountHolder accountHolder:accountHolderList)
		{
		this.query = Constants.INSERT_ACCOUNT_HOLDERS;
		jdbcTemplate.execute(this.query, new PreparedStatementCallback<Integer>() 
		{
			public Integer doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
				preparedStatement.setString(1, accountHolder.getFirstName());
				preparedStatement.setString(2, accountHolder.getLastName());
				preparedStatement.setString(3, accountHolder.getAccountNumber());
				preparedStatement.setInt(4, Integer.parseInt(accountHolder.getDebitCardNumber()));
				preparedStatement.setInt(5, 1234);
				return preparedStatement.executeUpdate();
			}
			
		});
		}
	}
}

}
