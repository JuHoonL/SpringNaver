package com.biz.naver.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.naver.service.NaverService;

import lombok.extern.slf4j.Slf4j;
/*
 *  컨트롤러의 역할
  	1. web browser가 보내는 request 정보를 수신
  	   (<ex> URI, form의 입력데이터 등)
  	2. service를 호출하여 연산, DB작업등을 수행
  	3. 생성된 Data와 views(*.jsp)파일을 rendering하여
  	   생성된 Data는 service로 부터 return받은 데이터이고
  	   rendering 파일은 *.jsp + vo의 결합 파일이다.
  	4. HTML 코드로 생성한 후 web browser로 response 송신
  	
  	>> 사소한 문자열 1개를 보내려고 해도 반드시 *.jsp파일이
  	있어야 하고, Rendering 과정을 거쳐야 한다.
  	
  	그래서 spring 3.5 이후에서는 @ResponseBody라는 Annotation을
  	도입해서 단순 문자열을 Rendering 하지않고 browser로 보낼 수 있다.
 */


@Slf4j
@Controller
public class NaverController {

	@Autowired
	NaverService nS;
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value="naver", method=RequestMethod.POST)
	public String naver(@RequestParam String search, Model model) {
		
		log.debug("Form에서 받은 search : " + search);
		
		String bookJasonString = nS.getBookString(search);
		
		JSONArray ja = nS.getBookObject(bookJasonString);
		
		model.addAttribute("NAVER",ja);
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="naver.json", method=RequestMethod.POST, produces="application/json; charset=utf8")
	public String naver_json(@RequestParam String search) {
		// @RequestParam("JSP의 변수명")이 기본문인데 매개변수명과 같으면 괄호 생략가능
		// @RequestParam String search는 @Param("search") String search를 대체한다.
		log.debug("FORM이 보낸 search변수명 : " + search);
		
		logger.debug("네이버 Home 열기");
		log.debug("lombok Log로 메시지 보이기");
		
		String bookString = nS.getBookString(search);
//		return "home";
		
//		return "WELCOME TO KOREA";
		return bookString;
	}
	
}
