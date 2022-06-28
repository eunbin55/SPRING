/**
 * 
 */
package com.pcwk.ehr.board;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
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

/**
 * @author eunbi
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) // applicationContext.xml loading
public class JunitBoardContraoller {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	// 브라우저 대역(Mock)
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
		search = new SearchVO(10, 1, "", "","20");

		board01 = new BoardVO(22, "제목_22", "내용_22", 0, "10", "날짜_사용안함", "이은빈", "날짜_사용안함", "이은빈");
		board02 = new BoardVO(220, "제목_220", "내용_220", 0, "10", "날짜_사용안함", "이은빈", "날짜_사용안함", "이은빈");
		board03 = new BoardVO(2200, "제목_2200", "내용_2200", 0, "10", "날짜_사용안함", "이은빈", "날짜_사용안함", "이은빈");

		
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("dao: "+dao);
		LOG.debug("webApplicationContext:" + webApplicationContext);
		LOG.debug("mockMvc:" + mockMvc);

		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(dao);

	}

	@Test
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder requestBuilder=
				MockMvcRequestBuilders.get("/user/doRetrieve.do")
				.param("pageSize"   ,String.valueOf(search.getPageSize()))
				.param("pageNum"    ,String.valueOf(search.getPageNum()))
				.param("searchDiv"  ,search.getSearchDiv())
				.param("searchWord" ,search.getSearchWord())
				.param("div" ,search.getDiv());
		
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().is2xxSuccessful());
				
		String result = resultActions.andDo(print())
							.andReturn().getResponse().getContentAsString();
	
		LOG.debug("==============================");
		LOG.debug("result"+result);
		LOG.debug("==============================");
		
		Gson gson = new Gson();
		List<BoardVO> list = gson.fromJson(result,new TypeToken<List<BoardVO>>() {}.getType());
		
		for(BoardVO vo:list) {
			LOG.debug("vo="+vo);
		}
	}

}
