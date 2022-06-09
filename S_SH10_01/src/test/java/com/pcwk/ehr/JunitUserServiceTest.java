package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//테스트 메소드 수행 순서: a-z순으로 작동
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)//JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = "/applicationContext.xml")
public class JunitUserServiceTest {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao dao;
	
	List<UserVO> list;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("===========================");
		LOG.debug("=0.setUp()=");
		LOG.debug("===========================");
		
		/*
		 * 사용자 레벨은 BASIC, SLIVER, GOLD                                                															
			사용자가 처음 가입하면 : BASIC                                                															
			가입 이후 50회 이상 로그인 하면 : SILVER                                                															
			SILVER레벨이면서 30번 이상 추천을 받으면 GOLD로 레벨 UP.                                                															
			사용자 레벨의 변경 작업은 일정한 주기를 가지고 일괄처리.(트랜잭션 관리)															
		 */
		list = Arrays.asList(
				new UserVO("p22", "이은빈22", "4321", Level.BASIC, 49, 0, "eunbin620@naver.com","날짜_사용안함"),
				new UserVO("p220", "이은빈220", "4321", Level.BASIC, 50, 10, "eunbin620@naver.com","날짜_사용안함"),
				new UserVO("p2200", "이은빈2200", "4321", Level.SILVER, 51, 29, "eunbin620@naver.com","날짜_사용안함"),
				new UserVO("p22000", "이은빈22000", "4321", Level.SILVER, 51, 30, "eunbin620@naver.com","날짜_사용안함"),
				new UserVO("p220000", "이은빈220000", "4321", Level.GOLD, 52, 31, "eunbin620@naver.com","날짜_사용안함")
				);
				
		
		LOG.debug("context:"+context);
		LOG.debug("userService:"+userService);
		LOG.debug("dao:"+dao);
		
		
		
		
		assertNotNull(context);
		assertNotNull(userService);
		assertNotNull(dao);
	}
	
	@Test
	public void add() throws SQLException {
		//1. 전체 데이터 삭제
		//2. Level이 있는 경우, Level이 Null인 경우
		//3. 각각추가
		//4. 각각 데이터 조회
		//5. 비교
		LOG.debug("===========================");
		LOG.debug("=2.add()=");
		LOG.debug("===========================");
		
		//1.
		for (UserVO user : list) {
			this.dao.doDelete(user);
		}
		
		//2. Level이 Null인 경우, NULL이 아닌 경우(그대로 통과)
		UserVO userWithOutLevel = list.get(0);
		userWithOutLevel.setLevel(null);//BASIC -> NULL
		
		//2-1. Level이 NULL이 아닌 경우(그대로 통과)
		UserVO userWithLevel = list.get(4);
		
		
		
		//3. 
		this.userService.add(userWithOutLevel);
		assertEquals(1, dao.getCount(list.get(0)));
		
		//3-1.
		this.userService.add(userWithLevel);
		assertEquals(2, dao.getCount(list.get(0)));
		
		//4.데이터 조회
		UserVO  userwithouLevelRead = this.dao.doSelectOne(userWithOutLevel);
		assertEquals(userwithouLevelRead.getLevel(), Level.BASIC);
		
		//4-1. Level이 NULL이 아닌 경우 조회
		UserVO userWithLevelRead =this.dao.doSelectOne(userWithLevel);
		assertEquals(userWithLevelRead.getLevel(), userWithLevel.getLevel());
	}
	
	@Test
	@Ignore
	public void upgradeLevels() throws SQLException {
		LOG.debug("===========================");
		LOG.debug("=1.upgradeLevels()=");
		LOG.debug("===========================");
		
		//1. 전체 데이터 삭제
		//2. 데이터 입력
		//3. 등업
		//4. 등업데이터 비교
		
		//1.
		for(UserVO user :list) {
			this.dao.doDelete(user);
		}
		
		//2.
		for(UserVO user :list) {
			dao.doInsert(user);
		}
		
		assertEquals(5, dao.getCount(list.get(0)));
		
		//3.
		this.userService.upgradeLevels(list.get(0));
	
		//4.
		checkLevel(list.get(0),Level.BASIC);
		checkLevel(list.get(1),Level.SILVER);
		checkLevel(list.get(2),Level.SILVER);
		checkLevel(list.get(3),Level.GOLD);
		checkLevel(list.get(4),Level.GOLD);
		
		
	
	}

	private void checkLevel(UserVO user, Level expectedLevel)throws SQLException{
		//DB에 있는 데이터 조회
		UserVO upUser = dao.doSelectOne(user);
		LOG.debug(upUser.getLevel()+"==="+expectedLevel);
		assertEquals(expectedLevel ,upUser.getLevel() );
	}
	
	
	@After
	public void tearDown() throws Exception {
	}


}
