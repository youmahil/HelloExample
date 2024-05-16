/**
 * 
 */
package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DemoFileInfo;
import com.example.demo.repository.DemoRepository;

/**
 * @author yaong
 *
 */
@Service
public class DemoService {
	@Autowired
	DemoRepository repo;
	
	/**
	 * 입력 내용물을 파일에 저장하고, 저장에 성공하면 DemoFileInfo객체 반환
	 * @param content
	 * @return
	 */
	public DemoFileInfo save(String content) throws Exception{
		// 파일 저장 경로
		final String FILE_VOLUME_PATH = "/nfsdemofiles/";
		//final String FILE_VOLUME_PATH = "/tmp/";
				
		DemoFileInfo info = null;
		
		// 현재 시각을 이용하여 /nfsdemofiles/yyyyMMddHHmmss.text 형태 파일 이름 생성
		LocalDateTime localDateTime = LocalDateTime.now();
		String fileName = FILE_VOLUME_PATH 
						+ localDateTime.format(
									DateTimeFormatter.ofPattern(
											"yyyyMMddHHmmss"
											)
								) 
						+ ".text";
		
		// 파일에 입력내용 저장
		try {
			boolean result = repo.saveFile(fileName, content);
			if(result == true) {
				// 성공하면 객체 반환
				info = new DemoFileInfo();
				info.setFileName(fileName);
				info.setEchoText(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
		// 실패 시 null , 성공 시 입력내용과 파일명이 있는 객체
		return info;
	}
}
