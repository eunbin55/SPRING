package com.pcwk.ehr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JunitUserDao {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	ApplicationContext context;
	UserDao dao;
	UserVO userVO;
	UserVO userVO02;
	
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("========================");
		LOG.debug("=0. setUp()=");
		LOG.debug("========================");
		
		userVO = new UserVO("p22", "이은빈22", "4321");
		userVO02 = new UserVO("p220", "이은빈220", "4321");
		context = new GenericXmlApplicationContext("/applicationContext.xml");
		
		dao = context.getBean("userDao", UserDao.class);
		
		LOG.debug("userVO:"+userVO);
		LOG.debug("context:"+context);
		LOG.debug("dao:"+dao);
		
		assertNotNull(context);
		assertNotNull(dao);
		
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("========================");
		LOG.debug("=9. tearDown()=");
		LOG.debug("========================");
	}

	@SuppressWarnings("deprecation")
	//timeout 1/1000초 단위 2초 이내에 수행이 완료되면 성공
	//@Ignore //test skipped
	@Test(timeout = 100000)
	public void addAndGet() {
		LOG.debug("========================");
		LOG.debug("=2. addAndGet()=");
		LOG.debug("========================");
		
		//add
		try {
			//전체 삭제
			dao.deleteAll();
			
			//1건 추가
			int flag = dao.add(userVO);
			assertThat(dao.getCount(userVO), is(1));
			
			//1건 추가
			dao.add(userVO02);
			assertThat(dao.getCount(userVO), is(2));
			
			//단건조회
			UserVO vsVO = dao.get(userVO);
			
			assertThat(vsVO.getuId(), is(userVO.getuId()));
			assertThat(vsVO.getName(), is(userVO.getName()));
			assertThat(vsVO.getPasswd(), is(userVO.getPasswd()));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void test() {
		LOG.debug("1 test():");
	}

}
