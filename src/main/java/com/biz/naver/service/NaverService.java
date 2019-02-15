package com.biz.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.biz.naver.config.NaverClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverService {

	private Log log = LogFactory.getLog(this.getClass());
	
	//도서정보 검색
	public String getBookInfo(String searchText) {
		
		log.debug("반갑소");
		
		//id와 key 세팅
		String clientId = NaverClient.ID;
		String clientKey = NaverClient.KEY;
		
		//검색문자열을 하나 선언
//		String text = "자바";
		
		
		try {
			//검색문자열을 NAVER로 보내기전에 ENCODING을 실시
			String text = URLEncoder.encode(searchText,"UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book.json";
			apiURL += "?query=" + text;
			
			//URL 객체로 생성
			URL url = new URL(apiURL);
			
			//HttpRequest로 변환
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//접속정보를 Setting
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", clientId);
			conn.setRequestProperty("X-Naver-Client-Secret", clientKey);
			
			//네이버에게 요청을 보내서 내 요청에 응답할 수 있는지의 여부 결과
			int resCode = conn.getResponseCode();
			
			BufferedReader br;
			if(resCode == 200) {
				//데이터를 수신할 준비
				InputStreamReader is = new InputStreamReader(conn.getInputStream());
				br = new BufferedReader(is);
			} else {
				//오류가 무엇인지 분석
				InputStreamReader is = new InputStreamReader(conn.getErrorStream());
				br = new BufferedReader(is);
			}
			
			String reader = "";
			String readStrings = "";
			while(true) {
				reader = br.readLine();
				if(reader == null) break;
				log.debug(reader);
				
				readStrings += reader;
			}
			
			br.close();
			return readStrings ; //Controller로 리턴
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
