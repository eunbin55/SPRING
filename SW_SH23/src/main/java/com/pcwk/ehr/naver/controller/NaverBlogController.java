package com.pcwk.ehr.naver.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.naver.domain.Item;
import com.pcwk.ehr.naver.service.NaverBlogService;

@Controller("naverBlogController")
@RequestMapping("naverBlog")
public class NaverBlogController {
	
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	NaverBlogService naverBlogService;
	
	public NaverBlogController() {}
	
	@RequestMapping(value="/naverBlogView.do", method=RequestMethod.GET)
	public String naverBlogView() throws SQLException{
		
		return "blog/blog_list";
		
	}
	
	//http://localhost:8081/ehr/naverBlog/doRetrieve.do
	@RequestMapping(value="/doRetrieve.do", method=RequestMethod.GET
			, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchVO inVO) throws SQLException{
		String jsonString = "";
		
		LOG.debug("=================================");
		LOG.debug("doRetrieve");
		LOG.debug("inVO : "+ inVO);
		LOG.debug("=================================");
		
		if(null == inVO.getSearchWord() && inVO.getSearchWord().equals("")) {
			throw new NullPointerException("검색어를 입력하세요");
		}
		
		List<Item> list = naverBlogService.doRetrieve(inVO);
		Gson gson = new Gson();
		jsonString = gson.toJson(list);
		
		LOG.debug("jsonString : "+ jsonString);
		LOG.debug("=================================");
		
		return jsonString;
	}

}
