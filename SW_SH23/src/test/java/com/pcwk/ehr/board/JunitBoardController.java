package com.pcwk.ehr.board;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
}) //applicationContext.xml loading
public class JunitBoardController {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대역(Mock)
	MockMvc mockMvc;
	
	@Autowired
	BoardDao dao;

	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	SearchVO search;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("====================");
		LOG.debug("=0.setUp()=");
		LOG.debug("====================");
		search = new SearchVO(10, 1, "", "", "10");
		
		board01 = new BoardVO(12, "제목12", "내용12", 0, "10", "날짜사용안함", "서혜", "날짜사용안함", "서혜");
		board02 = new BoardVO(120, "제목12", "내용12", 0, "10", "날짜사용안함", "서혜", "날짜사용안함", "서혜");
		board03 = new BoardVO(1200, "제목12", "내용12", 0, "10", "날짜사용안함", "서혜", "날짜사용안함", "서혜");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("dao : "+ dao);
		LOG.debug("mockMvc : "+ mockMvc);
		LOG.debug("webApplicationContext : "+ webApplicationContext);
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(dao);

	}

	@Test
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/doRetrieve.do")
				  .param("pageSize", String.valueOf(search.getPageSize()))
				  .param("pageNum", String.valueOf(search.getPageNum()))
				  .param("SearchDiv", search.getSearchDiv())
				  .param("SearchWord", search.getSearchWord())
				  .param("div", search.getDiv());
		
		  ResultActions resultActions = mockMvc.perform(requestBuilder)
				  .andExpect(status().is2xxSuccessful());
		  String result = resultActions.andDo(print())
				  .andReturn().getResponse().getContentAsString();
		  
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");
		  
		  Gson gson = new Gson();
		  List<BoardVO> list = gson.fromJson(result, new TypeToken<List<BoardVO>>() {}.getType());
		  
		  for(BoardVO vo :list) {
			  LOG.debug("vo : "+ vo);
		  }
	}

}
