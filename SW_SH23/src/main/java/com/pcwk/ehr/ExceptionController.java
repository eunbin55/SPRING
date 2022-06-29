package com.pcwk.ehr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.user.domain.UserVO;

@Controller
@RequestMapping("except")
public class ExceptionController {

	@RequestMapping(value = "illegal.do")
	public String illegalArgumentException(UserVO inVO) {
		if(null == inVO.getuId() || "".equals(inVO.getuId())) {
			throw new IllegalArgumentException("아이디 타입을 확인하세요.");
		}
		
		
		return "main/main";
	}
	
	//http://localhost:8081/ehr/except/nullPointer.do?uId=
	@RequestMapping(value = "/nullPointer.do")
	public String nullPointerException(UserVO inVO) {
		if(null == inVO.getuId() || "".equals(inVO.getuId())) {
			throw new NullPointerException("아이디를 입력하세요.");
		}
		
		
		return "main/main";
	}
}
