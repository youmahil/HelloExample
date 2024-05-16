/**
 * 
 */
package com.example.demo.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.DemoFileInfo;
import com.example.demo.domain.WorkResult;
import com.example.demo.service.DemoService;

/**
 * @author yaong
 *
 */
@Controller
public class DemoController {
	@Autowired
	DemoService service;
	
	/**
	 * 임의의 문자열을 입력받아서 처리하고, 성공/실패에 따라 제공 데이터 및 페이지 분기
	 * @param body
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/demo/echo", method = RequestMethod.POST)
	public String getResult(@RequestBody String body, Model model) {

		WorkResult result = new WorkResult();
		String nextPage = null;
		try {
			// 화면에 단 한개만 있는 입력 창의 "키=값" 을 분해하여 값만 추출
			String data = body.split("=")[1];

			DemoFileInfo info = service.save(data);
			if(info != null) {
				// 성공 시
				model.addAttribute("info", info);
			
				result.setCode("SUCCESS");
				result.setDetailMessage("");
			
				nextPage = "info";
			}else {
				// DemoFileInfo가 null인 경우 
				result.setCode("FAIL");
				result.setDetailMessage("OoooooPs!!!! Whts up?");
				
				nextPage = "error";		
			}
		} catch (Exception e) {
			// SpackTrace를 문자열로 변환
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            
			result.setCode("FAIL");
			result.setDetailMessage(exceptionAsString);
			
			nextPage = "error";
		}

		model.addAttribute("RESULT", result);
		
		return nextPage;
	}
}
