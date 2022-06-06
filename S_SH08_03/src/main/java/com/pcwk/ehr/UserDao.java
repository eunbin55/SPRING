package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface UserDao {

	// setter를 통한 의존성 주입
	void setDataSource(DataSource dataSource);

	List<UserVO> getAll();

	int getCount(UserVO inVO) throws SQLException;

	void deleteAll() throws SQLException;

	/**
	 * 회원단건 return
	 * 
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	UserVO get(UserVO inVO) throws SQLException;

	/**
	 * 사용자 등록
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	int add(UserVO inVO) throws SQLException;

}