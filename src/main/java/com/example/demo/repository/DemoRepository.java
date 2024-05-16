/**
 * 
 */
package com.example.demo.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Repository;

/**
 * @author yaong
 *
 */
@Repository
public class DemoRepository {
	/**
	 * 파일에 내용 기록하기
	 * @param fileName
	 * @param content
	 * @return
	 */
	public boolean saveFile(String fileName, String content) throws Exception{
        try {
            // 1. Path 객체 생성
            Path path = Paths.get(fileName);
 
            // 2. 파일에 쓰기
            Files.write(path, content.getBytes()); 
        } catch (IOException e) {
        	e.printStackTrace();
        	throw e;
        }
		
		return true;
	}
}
