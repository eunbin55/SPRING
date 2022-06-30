/**
 * 
 */
package com.pcwk.ehr;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author LG
 *
 */
public class RenameFile {
	final static String IMG_PATH = "D:\\upload\\temp\\file//u88";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//동적 폴더 생성
		File imgRootDir = new File(IMG_PATH);
		if(imgRootDir.isDirectory() == false) {
			boolean mkdirFlag = imgRootDir.mkdirs();
			System.out.println("imgRootDir mkdirFlag : "+ mkdirFlag);
		}
		
//		System.out.println(currentDate("YYYYMMDDHHmmss"));
//		System.out.println(getUUID());
//		
//		System.out.println(getRenameFile("YYYYMMDDHHmmss"));
//		System.out.println(getRenameFile("YYYYMMDDHHmmss").length());
	}
	
	/**
	 * File Rename
	 * @param dateFormat
	 * @return 46byte 데이터
	 */
	public static String getRenameFile(String dateFormat) {
		return currentDate(dateFormat)+ getUUID();
	}
	
	/**
	 * 현재 날짜를 format에 따라 추출
	 * @param dateFormat
	 * @return 
	 */
	public static String currentDate(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		return sdf.format(new Date());
	}
	
	/**
	 * uuid는 범용고유식별자(Universal Unique IDentifier)라고 한다
	 * 32bit uuid
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
