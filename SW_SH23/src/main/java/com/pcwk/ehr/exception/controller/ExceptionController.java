package com.pcwk.ehr.exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.user.domain.UserVO;

@Controller
@RequestMapping("except")
public class ExceptionController {
	
	//http://localhost:8081/ehr/except/illegal.do?uid=
	@RequestMapping(value="/illegal.do")
	public String IllegalArgumentException(UserVO inVO) {
		
		if(null == inVO.getuId() || "".equals(inVO.getuId())) {
			throw new IllegalArgumentException("아이디를 입력하세요");
		}
		
		return "main/main"; 
	}
	
	//http://localhost:8081/ehr/except/nullPointer.do?uid=
	@RequestMapping(value="/nullPointer.do")
	public String nullPointerException(UserVO inVO) {
		
		if(null == inVO.getuId() || "".equals(inVO.getuId())) {
			throw new NullPointerException("아이디를 입력하세요");
		}
		
		return "main/main";
		
	}

}
