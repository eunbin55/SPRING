package com.pcwk.ehr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	
	/**
	 * 회원 단건 조회
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserVO get(UserVO inVO) throws SQLException,ClassNotFoundException{
		UserVO outVO = null;
		//1.db연결
		//2.sql Statement(속도느려서 사용x), PreparedStatement
			//		SELECT u_id,
			//        name,
			//        passwd
			//		FROM hr_member
			//		WHERE u_id = :USER_ID
		//3.PreparedStatement수행
		//4.ResultSet으로 정보를 받아와 처리
		//5.자원반납
		
		//1.db연결
		Connection connection = null;
		connection = connectionMaker.makeConnection();
		
		//2.sql
		StringBuilder sb = new StringBuilder(100);
		sb.append(" SELECT u_id,         \n");
		sb.append("       name,          \n");
		sb.append("       passwd         \n");
		sb.append(" FROM hr_member       \n");
		sb.append(" WHERE u_id = ?		 \n");
		LOG.debug("===================================");
		LOG.debug("param:"+inVO.toString());
		LOG.debug("sql:\n"+sb.toString());
		LOG.debug("===================================");
		
		//3.
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());

		//4.
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			outVO = new UserVO();
			
			outVO.setuId(rs.getString("u_id"));
			outVO.setName(rs.getString("name"));
			outVO.setPasswd(rs.getString("passwd"));
		}
		LOG.debug("==================================");
		LOG.debug("=outVO="+outVO.toString());
		LOG.debug("==================================");
		
		
		//5. 객체 생성의 역순으로 자원반납
		rs.close();
		pstmt.close();
		connection.close();
		
		return outVO;
	}
	
	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public int add(UserVO inVO)throws SQLException, ClassNotFoundException{
		int flag = 0;
		//1.db연결
		//2.sql Statement(속도느려서 사용x), PreparedStatement
		//3.PreparedStatement수행
		//4.ResultSet으로 정보를 받아와 처리
		//5.자원반납
		
		//1.db연결
		Connection connection = null;
		connection = connectionMaker.makeConnection();
		
		//2.PreparedStatement
		StringBuilder sb = new StringBuilder(100);
		sb.append(" INSERT INTO hr_member (  \n");
		sb.append(" 	    u_id,            \n");
		sb.append(" 	    name,            \n");
		sb.append(" 	    passwd           \n");
		sb.append(" ) VALUES (        		 \n");
		sb.append(" 	    ?,               \n");
		sb.append(" 	    ?,               \n");
		sb.append(" 	    ?                \n");
		sb.append(" )                    	 \n");
		LOG.debug("===================================");
		LOG.debug("param:"+inVO.toString());
		LOG.debug("sql:\n"+sb.toString());
		LOG.debug("===================================");
		
		//3.PreparedStatement수행
		PreparedStatement pstmt = connection.prepareStatement(sb.toString());
		pstmt.setString(1, inVO.getuId());
		pstmt.setString(2, inVO.getName());
		pstmt.setString(3, inVO.getPasswd());
		
		//4.
		flag = pstmt.executeUpdate();
		LOG.debug("flag:"+flag);
		
		//5.자원반납
		pstmt.close();
		connection.close();
		
		return flag;
	}
	
}
