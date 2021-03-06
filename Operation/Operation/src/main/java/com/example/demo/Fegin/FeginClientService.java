package com.example.demo.Fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "FeginService", url = "https://api.coindesk.com")
public interface FeginClientService {

	@RequestMapping(value = "/v1/bpi/currentprice.json", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	String currentprice();//當前價位

}
