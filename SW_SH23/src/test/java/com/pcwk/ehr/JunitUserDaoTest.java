package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;
@RunWith(SpringJUnit4ClassRunner.class) //JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
}) //applicationContext.xml loading
public class JunitUserDaoTest {
  final Logger LOG = LogManager.getLogger(this.getClass());
  
  @Autowired  //컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
  ApplicationContext context;
      
    
  @Autowired
  UserDao dao;
  UserVO  user01;
  UserVO  user02;
  UserVO  user03;
  
  SearchVO searchVO;
  
  @Before
  public void setUp()throws Exception{
	  LOG.debug("====================");
	  LOG.debug("=0.setUp()=");
	  LOG.debug("====================");
	  searchVO = new SearchVO(10, 1, "", "");
	  
	  user01 = new UserVO("p12","문서혜12", "4321", Level.BASIC, 1, 0, "anstjgp@naver.com", "날짜_사용안함");
      user02 = new UserVO("p120","문서혜120", "4321", Level.SILVER, 50, 2, "anstjgp@naver.com", "날짜_사용안함");
      user03 = new UserVO("p1200","문서혜1200", "4321", Level.GOLD, 100, 31, "anstjgp@naver.com", "날짜_사용안함");
	  
	  
	  LOG.debug("context:"+context);
	  LOG.debug("dao:"+dao);
	  
	  assertNotNull(context);
	  assertNotNull(dao);
  }
  
  @Test
  public void passCheck() throws SQLException{
	  //1. 기존데이터 삭제
	  //2. 한 건 입력
	  //3. passCheck()
	  
	  //1.
	  dao.doDelete(user01);
	  dao.doDelete(user02);
	  dao.doDelete(user03);
	  assertEquals(0, dao.getCount(user01));
	  
	  //2.  신규 데이터 입력
	  dao.doInsert(user01);
	  assertEquals(1, dao.getCount(user01));
	  
	  //3.
	  int count = dao.passCheck(user01);
	  assertEquals(1, count);
	  
	  //3-1. 비번이 틀린경우
	  user01.setPasswd(user01.getPasswd()+ "false");
	  count = dao.passCheck(user01);
	  assertEquals(0, count);
	  
  }
  
  @Test
  @Ignore
  public void idCheck() throws SQLException {
	  //1. 기존데이터 삭제
	  //2. 한 건 입력
	  //3. idCheck()
	  
	  //1.
	  //dao.deleteAll();
	  dao.doDelete(user01);
	  dao.doDelete(user02);
	  dao.doDelete(user03);
	  
	  //2.  신규 데이터 입력
	  dao.doInsert(user01);
	  assertEquals(1, dao.getCount(user01));
	  
	  //3. 단건 조회
	  //id가 있는 경우
	  int count = dao.idCheck(user01);
	  assertEquals(1, count);
	  
	  //id가 없는 경우
	  assertEquals(0, dao.idCheck(user02));
  }
  
  @Test
  @Ignore
  public void doRetrieve() throws SQLException {
	  searchVO.setSearchDiv("30");
	  searchVO.setSearchWord("tubus1130");
	  List<UserVO> list = dao.doRetrieve(searchVO);
	  for(UserVO vo:list) {
		  LOG.debug("vo="+vo);
	  }
  }
  
  @Test
  @Ignore
  public void doUpdate() throws SQLException {
	  //1. 전체삭제
	  //2. 신규등록 :user01
	  //3. 한건조회 :user01
	  //4. user01 데이터 수정:
	  //5. 수정
	  //6. 한건조회 비교
	  
	  //1.
	  //dao.deleteAll();
	  dao.doDelete(user01);
	  dao.doDelete(user02);
	  dao.doDelete(user03);
	  
	  
	  assertEquals(0, dao.getCount(user01));
	  
	  //2.
	  dao.doInsert(user01);
	  assertEquals(1, dao.getCount(user01));
	  
	  //3.
	  UserVO vsVO = dao.doSelectOne(user01);
	  isSameUser(vsVO, user01);
	  
	  //4.
	  String upStr = "_U";
	  int upInt    = 10;
	  
	  user01.setName(user01.getName()+upStr);
	  user01.setPasswd(user01.getPasswd()+upStr);
	  user01.setEmail(user01.getEmail()+upStr);
	  
	  user01.setLogin(user01.getLogin()+upInt);
	  user01.setRecommend(user01.getRecommend()+upInt);
	  user01.setLevel(Level.SILVER);
	  
	  //5.
	  dao.doUpdate(user01);
	  
	  //6.
	  vsVO = dao.doSelectOne(user01);
	  isSameUser(vsVO, user01);
	  
  }
  
  @Test
  @Ignore
  public void getAll() throws SQLException{
	  //1. 전체삭제
	  //2. 데이터 3건 각각 입력
	  //3. 전체건수 조회 = 3건
	  
	  //1.
	  //dao.deleteAll();
	  dao.doDelete(user01);
	  dao.doDelete(user02);
	  dao.doDelete(user03);
	  
	  assertEquals(0, dao.getCount(user01));
	  //2.
	  dao.doInsert(user01);
	  assertEquals(1, dao.getCount(user01));
	  
	  dao.doInsert(user02);
	  assertEquals(2, dao.getCount(user01));
	  
	  dao.doInsert(user03);
	  assertEquals(3, dao.getCount(user01));	 
	  
	  //3.
	  List<UserVO> list = dao.getAll(user01);
	  assertEquals(3, list.size());
  }
  
  @Test
  @Ignore
  public void addAndGet() {
	  LOG.debug("====================");
	  LOG.debug("=1.addAndGet()=");
	  LOG.debug("====================");	
	    
	  //전체 삭제
	  try {
		//dao.deleteAll();
		dao.doDelete(user01);
		dao.doDelete(user02);
		dao.doDelete(user03);
		  
		assertEquals(0, dao.getCount(user01));
		
		//1건 추가
		dao.doInsert(user01);
		assertEquals(1, dao.getCount(user01));
		 
		//1건 추가
		dao.doInsert(user02);
		assertEquals(2, dao.getCount(user01));
		
		//단건조회
		UserVO vsUser01 = dao.doSelectOne(user01);
		isSameUser(vsUser01,user01);
		
		//단건조회
		UserVO vsUser02 = dao.doSelectOne(user02);
		isSameUser(vsUser02, user02);
		
		 //dao.doRetrieve(user01);
	} catch (SQLException e) {
		LOG.debug("------------------");
		LOG.debug("-SQLException-"+e.getMessage());
		LOG.debug("------------------");
		e.printStackTrace();
	} 
	  
  }
  
  private void isSameUser(UserVO vsVO, UserVO orgVO) {
	  assertEquals(vsVO.getuId(), orgVO.getuId());
	  assertEquals(vsVO.getName(), orgVO.getName());
	  assertEquals(vsVO.getPasswd(), orgVO.getPasswd());
	  
	  assertEquals(vsVO.getLevel(),orgVO.getLevel());
	  assertEquals(vsVO.getLogin(),orgVO.getLogin());
	  assertEquals(vsVO.getRecommend(),orgVO.getRecommend());
	  assertEquals(vsVO.getEmail(),orgVO.getEmail());
	  
  }
  
  @Test(expected = NullPointerException.class)
  @Ignore
  public void getFailure()throws SQLException{
	  LOG.debug("====================");
	  LOG.debug("=2.getFailure()=");
	  LOG.debug("====================");	  
	  
	  //dao.deleteAll();
	  
	  dao.doSelectOne(user01);
  }
  
  @After
  public void tearDown()throws Exception{
	  LOG.debug("====================");
	  LOG.debug("=9.tearDown()=");
	  LOG.debug("====================");	  
  }

}
