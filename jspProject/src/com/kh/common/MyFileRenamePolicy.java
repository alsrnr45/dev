package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	// interface에 미완성된 메소드임으로 오버라이딩으로 완성해야 할 메소드 호출 필요
	// 기존의 파일(원본 파일)을 전달 받아서 수정명 작업을 마친 수정된 파일을 반환해주는 메소드
	@Override
	public File rename(File originFile) {
		
		// 원본 파일명("aaa.jpg")
		String originName = originFile.getName();
		
		// 수정 파일명 : 파일업로드한 시간 (년월일시분초) + 5자리 랜덤값(10000~99999) + 확장자(원본파일의 확장자)
		
		// 원본명 => 수정명
		// aaa.jpg => 2021040714253045312.jpg
		
		// 1. 파일업로드 한 시간(년월일시분초) (String currentTime)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(new Date()); // "20210407142530"
		//현재시간을 위 포맷이 적용된 형태로 문자형태로 돌려줄 것임.
		
		// 2. 5자리 랜덤값(int ranNum)
		int ranNum = (int)(Math.random()* 90000 + 10000);
		// 더해지는 수 -> 시작 수, 곱한 수 -> 시작수로부터 몇개의 랜덤값(갯수)
		// 10000~99999까지의 랜덤한 숫자 
		
		// 3. 기존파일의 확장자(String ext)
		String ext = originName.substring(originName.lastIndexOf(".")); // ".jpg"
		// originName의 이름에 마지막 인덱스로부터 .의 위치까지의 String을 추출해온다.
		
		// 자 이제 1,2,3 이 세 가지를 더해주자
		
		String changeName = currentTime + ranNum + ext; // 2021040714253011123.jpg 이런식으로 담겼을것
		
		// 전달받은 원본파일(originFile)을 수정된 파일명으로 파일객체 생성해서 반환
		return new File(originFile.getParent(), changeName);
		// 
	}
	
}
