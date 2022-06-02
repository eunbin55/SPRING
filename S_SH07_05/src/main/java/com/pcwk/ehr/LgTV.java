package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LgTV implements Tv {
	final Logger LOG = LogManager.getLogger(getClass());
	final String brnad = "엘지Tv";
	
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
		LOG.debug(brnad+" 볼륨을 내린다.");

	}

}
