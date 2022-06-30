package com.pcwk.ehr.naver.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.naver.domain.Item;

public interface NaverBlogService {
	
	/**
	 * 목록조회 
	 * @param dto
	 * @return List<BoardVO>
	 * @throws SQLException
	 */
	List<Item> doRetrieve(SearchVO dto) throws SQLException;

}
