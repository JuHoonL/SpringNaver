package com.biz.naver.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.naver.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NaverController {

	@Autowired
	NaverService nS;
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value="naver", method=RequestMethod.POST)
	public String naver(@RequestParam String search) {
		// @RequestParam("JSP의 변수명")이 기본문인데 매개변수명과 같으면 괄호 생략가능
		// @RequestParam String search는 @Param("search") String search를 대체한다.
		log.debug("FORM이 보낸 search변수명 : " + search);
		
		logger.debug("네이버 Home 열기");
		log.debug("lombok Log로 메시지 보이기");
		
		nS.getBookInfo(search);
		return "home";
	}
	
}
