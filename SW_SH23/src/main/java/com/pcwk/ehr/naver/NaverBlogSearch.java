package com.pcwk.ehr.naver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.pcwk.ehr.naver.domain.Channel;
import com.pcwk.ehr.naver.domain.Item;

public class NaverBlogSearch {
	
	final static Logger LOG = LogManager.getLogger(NaverBlogSearch.class);

	public static void main(String[] args) {
		
		String clientId = "qtzKbfAmebbuZA7YM4FC"; //회원 ID
		String clienSecret = "lZWp_wbt7J";        //회원비번
		LOG.debug("=================================");
		
		try {
			String searchText = URLEncoder.encode("신촌", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/blog?query="+searchText;//json
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+searchText;//xml
			
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clienSecret);
			
			int responseCode = con.getResponseCode();
			LOG.debug("responseCode : "+responseCode);
			
			BufferedReader br;
			
			if(200 == responseCode) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine = "";
			StringBuffer responseData = new StringBuffer(2000);
			
			while((inputLine = br.readLine()) != null) {
				//LOG.debug(inputLine);
				responseData.append(inputLine);
			}
			
			br.close();
			
			Gson gson = new Gson();
			Channel channel = gson.fromJson(responseData.toString(), Channel.class);
			
			for(Item item :channel.getItems()) {
				LOG.debug(item);
			}
			
		}catch(Exception e) {
			LOG.debug("=================================");
			LOG.debug("e : "+ e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}

	}

}
