package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SamsungTV implements Tv {
	final Logger LOG = LogManager.getLogger(getClass());
	final String brnad = "삼성 TV";
	
	public SamsungTV() {
		LOG.debug(brnad+ " default 생성자 SamsungTV()");
		
	}
	
	//생성자 다음 초기화
	public void initMethod() {
		LOG.debug(brnad+ " initMethod()");
	}

	//자원반납, applicationContext 종료시 수행
	public void destroyMethod() {
		LOG.debug(brnad+ " destroyMethod()");
	}
	
	
	@Override
	public void powerOn() {
		LOG.debug(brnad+" 전원을 켠다.");
	}
	@Override
	public void powerOff() {
		LOG.debug(brnad+" 전원을 끈다.");
	}
	@Override
	public void volumeUp() {
		LOG.debug(brnad+" 볼륨을 높인다.");
	}
	@Override
	public void volumeDown() {
		LOG.debug(brnad+" 볼륨을 낮춘다.");
	}
	
	
}
